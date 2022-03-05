package unithon8th.somethingnew.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unithon8th.somethingnew.dto.user.request.UserCallStreetRequestDto;
import unithon8th.somethingnew.dto.user.request.UserCallTimeRequestDto;
import unithon8th.somethingnew.dto.user.request.UserCallableRequestDto;
import unithon8th.somethingnew.dto.user.response.UserCallStreetResponseDto;
import unithon8th.somethingnew.dto.user.response.UserCallTimeResponseDto;
import unithon8th.somethingnew.service.FriendService;
import unithon8th.somethingnew.service.UserService;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    //cancall 변경 api
    @PutMapping("/callable")
    public ResponseEntity changeCallable(@RequestBody UserCallableRequestDto userCallableRequestDto) {
        userService.updateUserCanCall(userCallableRequestDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    //Street 값 변경 api
    @PutMapping("/street")
    public ResponseEntity<UserCallStreetResponseDto> changeStreet(@RequestBody UserCallStreetRequestDto userCallStreetRequestDto){
        UserCallStreetResponseDto userCallStreetResponseDto = userService.updateUserCallStreet(userCallStreetRequestDto);
        return new ResponseEntity(userCallStreetResponseDto, HttpStatus.OK);
    }

    //time 값 변경 api
    @PutMapping("/time")
    public ResponseEntity<UserCallTimeResponseDto> changeTime(@RequestBody UserCallTimeRequestDto userCallTimeRequestDto){
        UserCallTimeResponseDto userCallTimeResponseDto = userService.updateUserCallTime(userCallTimeRequestDto);
        return new ResponseEntity(userCallTimeResponseDto, HttpStatus.OK);
    }

}
