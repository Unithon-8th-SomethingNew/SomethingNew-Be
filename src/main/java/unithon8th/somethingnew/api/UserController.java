/*
package unithon8th.somethingnew.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unithon8th.somethingnew.domain.user.User;
import unithon8th.somethingnew.domain.user.UserRepository;
import unithon8th.somethingnew.dto.user.UserLocationResponseDto;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/location/{id}")
    public ResponseEntity<UserLocationResponseDto> returnUserLocation(@PathVariable("id") Long id){
        User user = userRepository.findById(id).get();

        if (user.canCall()==true) {
            UserLocationResponseDto locationResponseDto = new UserLocationResponseDto(user.getUserId(), user.getUsername(), user.getImgUrl(), Double.parseDouble(user.getX()), Double.parseDouble(user.getY()), user.getFcmToken());
            return ResponseEntity.ok(locationResponseDto);
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}*/
