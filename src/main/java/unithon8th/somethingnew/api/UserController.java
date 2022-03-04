package unithon8th.somethingnew.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unithon8th.somethingnew.dto.user.UserCallableRequestDto;
import unithon8th.somethingnew.service.user.UserService;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    @PutMapping("/callable")
    public ResponseEntity changeCallable(@RequestBody UserCallableRequestDto userCallableRequestDto){
        userService.updateUserCanCall(userCallableRequestDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
