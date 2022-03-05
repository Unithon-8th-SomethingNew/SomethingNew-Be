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
    private final FriendService friendService;

    @PostMapping("/request")
    public ResponseEntity friendRequest(@RequestParam("toUserEmail") String toUserEmail, @RequestParam("fromUserId") Long fromUserId){
        Friend check = friendService.saveFriend(toUserEmail, fromUserId);
        if(check != null)
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find")
    public ResponseEntity<List<UserFriendResponseDto>> findFriends(@RequestParam("userId") Long userId){
        List<UserFriendResponseDto> userList = friendService.getUserFriendResponseDtos(userId);
        return ResponseEntity.ok(userList);
    }


}
