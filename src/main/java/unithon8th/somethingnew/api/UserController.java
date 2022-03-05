package unithon8th.somethingnew.api;

import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unithon8th.somethingnew.domain.user.User;
import unithon8th.somethingnew.domain.user.UserRepository;
import unithon8th.somethingnew.dto.user.UserCallableRequestDto;
import unithon8th.somethingnew.dto.user.UserLocationResponseDto;
import unithon8th.somethingnew.service.UserService;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    //cancall 변경 api
    @PutMapping("/callable")
    public ResponseEntity changeCallable(@RequestBody UserCallableRequestDto userCallableRequestDto) {
        userService.updateUserCanCall(userCallableRequestDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/location/{userId}")
    public ResponseEntity<UserLocationResponseDto> returnUserLocation(@PathVariable("userId") Long userId) {
        try {
            UserLocationResponseDto userLocationDto = userService.findUserLocation(userId);
            Assert.notNull(userLocationDto);

            return ResponseEntity.ok(userLocationDto);
        }catch (Exception e){
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
}
