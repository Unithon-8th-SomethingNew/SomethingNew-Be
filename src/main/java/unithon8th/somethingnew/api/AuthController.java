package unithon8th.somethingnew.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import unithon8th.somethingnew.domain.user.User;
import unithon8th.somethingnew.dto.user.UserRequestDto;
import unithon8th.somethingnew.dto.user.UserResponseDto;
import unithon8th.somethingnew.service.KakaoService;
import unithon8th.somethingnew.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final KakaoService kakaoService;
    private final UserService userService;


    @PostMapping(value = "/kakao")
    public ResponseEntity<UserResponseDto> giveToken(@RequestParam("token") String accessToken) {
        System.out.println("accessToken = " + accessToken);
        UserRequestDto userInfo = kakaoService.getUserInfo(accessToken);   //accessToken으로 유저정보 받아오기
        if (userInfo.getKakaoId() != null) {

            //kakaoId 기준으로 DB select하여 User 데이터가 없으면 Insert, 있으면 Update
            userService.insertOrUpdateUser(userInfo);

            Optional<User> userByKakaoId = userService.findUserByKakaoId(userInfo.getKakaoId());

            //UserResponseDto에 userId 추가
            UserResponseDto userResponseDto = new UserResponseDto(userInfo.getUsername());

            return ResponseEntity.ok(userResponseDto);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}

