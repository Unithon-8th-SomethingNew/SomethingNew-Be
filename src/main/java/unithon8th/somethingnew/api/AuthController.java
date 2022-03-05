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
import unithon8th.somethingnew.service.map.NaverMapService;
import unithon8th.somethingnew.service.social.KakaoService;
import unithon8th.somethingnew.service.UserService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Optional;


@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
@Log4j2
public class AuthController {
    private final KakaoService kakaoService;

    private final NaverMapService naverMapService;

    private final UserService userService;
    //커밋용주석

    @PostMapping(value = "/kakao")
    public ResponseEntity<UserResponseDto> giveToken(@RequestParam("token") String accessToken, @RequestParam("fcmToken") String fcmToken, @RequestParam("street") String street) {
        System.out.println("accessToken = " + accessToken);
        System.out.println("fcmToken = " + fcmToken);

        UserRequestDto userInfo = kakaoService.getUserInfo(accessToken);   //accessToken으로 유저정보 받아오기
        HashMap<String, String> userLocation = naverMapService.getUserLocation(street);
        userInfo.setStreet(street);
        userInfo.setX(userLocation.get("x"));
        userInfo.setY(userLocation.get("y"));
        userInfo.setToTime(LocalTime.parse("00:00:00",DateTimeFormatter.ISO_LOCAL_TIME));
        userInfo.setFromTime(LocalTime.parse("24:00:00",DateTimeFormatter.ISO_LOCAL_TIME));
        userInfo.setFcmToken(fcmToken);
        if (userInfo.getSocialId() != null) {
            //kakaoId 기준으로 DB select하여 User 데이터가 없으면 Insert, 있으면 Update
            userService.insertOrUpdateUser(userInfo);
            Optional<User> optionalUser = userService.findUserBySocial(userInfo.getSocialId(), userInfo.getSocialType());
            log.info("user FromTime={}",optionalUser.get().getFromTime());
            //UserResponseDto에 userId 추가
            UserResponseDto userResponseDto = new UserResponseDto(optionalUser.get().getUserId(), userInfo.getUsername(), userInfo.getImgURL());

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

