package unithon8th.somethingnew.api;

import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unithon8th.somethingnew.domain.user.User;
import unithon8th.somethingnew.domain.user.UserRepository;
import unithon8th.somethingnew.dto.friend.FriendLocationResponseDto;
import unithon8th.somethingnew.dto.user.*;
import unithon8th.somethingnew.service.FriendService;
import unithon8th.somethingnew.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;
    private final FriendService friendService;

    //cancall 변경 api
    @PutMapping("/callable")
    public ResponseEntity changeCallable(@RequestBody UserCallableRequestDto userCallableRequestDto) {
        userService.updateUserCanCall(userCallableRequestDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/callStreet")
    public ResponseEntity<UserCallStreetResponseDto> changeStreet(@RequestBody UserCallStreetRequestDto userCallStreetRequestDto){
        UserCallStreetResponseDto userCallStreetResponseDto = userService.updateUserCallStreet(userCallStreetRequestDto);
        return new ResponseEntity(userCallStreetResponseDto,HttpStatus.OK);
    }

    @PutMapping("/callTime")
    public ResponseEntity<UserCallTimeResponseDto> changeTime(@RequestBody UserCallTimeRequestDto userCallTimeRequestDto){
        UserCallTimeResponseDto userCallTimeResponseDto = userService.updateUserCallTime(userCallTimeRequestDto);
        return new ResponseEntity(userCallTimeResponseDto,HttpStatus.OK);
    }



}
