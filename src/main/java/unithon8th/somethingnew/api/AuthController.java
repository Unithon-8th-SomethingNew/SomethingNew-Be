package unithon8th.somethingnew.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
import unithon8th.somethingnew.service.NaverService;
import unithon8th.somethingnew.service.UserService;

import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
@Log4j2
public class AuthController {
    private final KakaoService kakaoService;

    private final NaverService naverService;

    private final UserService userService;
    //커밋용주석
    @PostMapping(value = "/kakao")
    public ResponseEntity<UserResponseDto> giveToken(@RequestParam("token") String accessToken,@RequestParam("fcmToken") String fcmToken) {
        System.out.println("accessToken = " + accessToken);
        System.out.println("fcmToken = " + fcmToken);

        UserRequestDto userInfo = kakaoService.getUserInfo(accessToken);   //accessToken으로 유저정보 받아오기
        if (userInfo.getSocialId() != null) {

            //kakaoId 기준으로 DB select하여 User 데이터가 없으면 Insert, 있으면 Update
            userService.insertOrUpdateUser(userInfo,fcmToken);

            Optional<User> userByKakaoId = userService.findUserByKakaoId(userInfo.getSocialId());

            //UserResponseDto에 userId 추가
            UserResponseDto userResponseDto = new UserResponseDto(userInfo.getUsername());

            return ResponseEntity.ok(userResponseDto);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/naver")
    public ResponseEntity<UserResponseDto> getToken(@RequestParam("token")String access_token,@RequestParam("fcmToken") String fcmToken){
        System.out.println("access_token = " + access_token);
        System.out.println("fcmToken = " + fcmToken);
        UserRequestDto userInfo = naverService.getUserInfo(access_token);
        if(userInfo.getSocialId()==null){
            userService.naverInsertOrUpdate(userInfo,fcmToken);
            Optional<User> findNaverUser = userService.findUserByNaverId(userInfo.getSocialId());
            UserResponseDto userResponseDto=new UserResponseDto(userInfo.getUsername());
            log.info(findNaverUser.get().getFcmToken());
            return ResponseEntity.ok(userResponseDto);
        }else {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
}

