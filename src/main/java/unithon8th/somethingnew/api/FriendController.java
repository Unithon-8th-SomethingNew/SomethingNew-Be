package unithon8th.somethingnew.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unithon8th.somethingnew.domain.friend.Friend;
import unithon8th.somethingnew.domain.friend.FriendRepository;
import unithon8th.somethingnew.domain.user.User;
import unithon8th.somethingnew.dto.user.UserFriendResponseDto;
import unithon8th.somethingnew.service.FriendService;
import unithon8th.somethingnew.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/friend")
@RestController
public class FriendController {
    private final FriendRepository friendRepository;
    private final FriendService friendService;
    private final UserService userService;

    @PostMapping("/request")
    public ResponseEntity friendRequest(@RequestParam("toUserId") Long toUserId, @RequestParam("fromUserId") Long fromUserId){
        boolean check = friendService.saveFriend(toUserId, fromUserId);
        if(check == true)
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find")
    public ResponseEntity<List<UserFriendResponseDto>> findFriends(@RequestParam("userId") Long userId){
        List<Friend> friendList = friendRepository.findByFromUserIdOrToUserId(userId, userId);
        List<Long> friendsIdList= new ArrayList<>();
        for (Friend friend : friendList) {
            if(friend.getFromUserId() == userId)
                friendsIdList.add(friend.getToUserId());
            else
                friendsIdList.add(friend.getFromUserId());
        }
        List<UserFriendResponseDto> userList = new ArrayList<>();
        for (Long id : friendsIdList) {
            User user = userService.findUserByUserId(id).get();
            UserFriendResponseDto userDto = UserFriendResponseDto.builder()
                    .email(user.getEmail())
                    .imgUrl(user.getImgUrl())
                    .canCall(user.isCanCall())
                    .username(user.getUsername())
                    .userId(id)
                    .street(user.getStreet())
                    .build();
            userList.add(userDto);
        }
        return ResponseEntity.ok(userList);
    }
}
