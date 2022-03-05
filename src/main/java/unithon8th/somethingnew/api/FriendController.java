package unithon8th.somethingnew.api;

import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unithon8th.somethingnew.domain.friend.Friend;
import unithon8th.somethingnew.dto.friend.FriendLocationResponseDto;
import unithon8th.somethingnew.dto.friend.FriendResponseDto;
import unithon8th.somethingnew.service.FriendService;

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
    public ResponseEntity<List<FriendResponseDto>> findFriends(@RequestParam("userId") Long userId){
        List<FriendResponseDto> userList = friendService.getUserFriendResponseDtos(userId);
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/location/{userId}")
    public ResponseEntity<List<FriendLocationResponseDto>> findFriendsLocation(@PathVariable("userId") Long userId) {
        try {
            List<FriendLocationResponseDto> friendLocationList = friendService.getFriendLocation(userId);
            Assert.notNull(friendLocationList);

            return ResponseEntity.ok(friendLocationList);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
