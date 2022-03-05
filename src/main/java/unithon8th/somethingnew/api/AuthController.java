package unithon8th.somethingnew.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unithon8th.somethingnew.dto.user.UserLoginRequestDto;
import unithon8th.somethingnew.dto.user.UserRequestDto;
import unithon8th.somethingnew.dto.user.UserResponseDto;
import unithon8th.somethingnew.service.map.NaverMapService;
import unithon8th.somethingnew.service.social.KakaoService;
import unithon8th.somethingnew.service.social.NaverService;
import unithon8th.somethingnew.service.user.UserService;

import java.util.HashMap;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
@Log4j2
public class AuthController {
    private final KakaoService kakaoService;

    private final NaverService naverService;

    private final UserService userService;

    private final NaverMapService naverMapService;
    //커밋용주석

    @PostMapping(value = "/kakao")
    public ResponseEntity<UserResponseDto> giveToken(@RequestParam("token") String accessToken,@RequestParam("fcmToken")String fcmToken,@RequestParam("street")String street) {
        System.out.println("accessToken = " + accessToken);
        System.out.println("fcmToken = " + fcmToken);

        UserRequestDto userInfo = kakaoService.getUserInfo(accessToken);   //accessToken으로 유저정보 받아오기
        HashMap<String, String> userLocation = naverMapService.getUserLocation(street);
        log.info("user-Location={}",userLocation);
        userInfo.setStreet(street);
        userInfo.setX(userLocation.get("x"));
        userInfo.setY(userLocation.get("y"));
        userInfo.setFcmToken(userInfo.getFcmToken());
        if (userInfo.getSocialId() != null) {
            //kakaoId 기준으로 DB select하여 User 데이터가 없으면 Insert, 있으면 Update
            userService.insertOrUpdateUser(userInfo);
            //UserResponseDto에 userId 추가

            UserResponseDto userResponseDto = new UserResponseDto(userInfo.toEntity().getUserId(),userInfo.getUsername(),userInfo.getImgURL());
            return ResponseEntity.ok(userResponseDto);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /*@PostMapping("/naver")
    public ResponseEntity<UserResponseDto> getToken(@RequestParam("token") String accessToken, @RequestParam("fcmToken") String fcmToken){
        System.out.println("accessToken = " + accessToken);
        System.out.println("fcmToken = " + fcmToken);

        UserRequestDto userInfo = naverService.getUserInfo(accessToken);
        userInfo.setFcmToken(fcmToken);
        if(userInfo.getSocialId() != null){
            userService.insertOrUpdateUser(userInfo);

            UserResponseDto userResponseDto = new UserResponseDto(userInfo.getUsername());

            return ResponseEntity.ok(userResponseDto);
        }else {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }*/
}

