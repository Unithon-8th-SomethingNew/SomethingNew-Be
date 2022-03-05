package unithon8th.somethingnew.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unithon8th.somethingnew.domain.user.SocialType;
import unithon8th.somethingnew.domain.user.User;
import unithon8th.somethingnew.domain.user.UserRepository;
import unithon8th.somethingnew.dto.user.UserCallableRequestDto;
import unithon8th.somethingnew.dto.user.UserLocationResponseDto;
import unithon8th.somethingnew.dto.user.UserRequestDto;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //커밋용주석
    public void insertOrUpdateUser(UserRequestDto userInfo) {
        String socialId = userInfo.getSocialId();
        SocialType socialType = userInfo.getSocialType();
        //처음 로그인 하는 유저면 DB에 insert
        if (!findUserBySocial(socialId, socialType).isPresent()) {
            User user = userInfo.toEntity(); //기본 Role = ROLE.USER
            userRepository.save(user);
        } else { //이미 로그인 했던 유저라면 DB update
            updateUserBySocial(userInfo);
        }
    }

    public Optional<User> findUserByUserId(Long userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        return user;
    }

    public Optional<User> findUserBySocial(String socialId, SocialType socialType) {
        Optional<User> user = userRepository.findBySocialIdAndSocialType(socialId, socialType);
        return user;
    }

    public void updateUserBySocial(UserRequestDto userInfo) {
        userRepository.updateUserBySocialIdAndSocialType(userInfo.getUsername(), userInfo.getEmail(), userInfo.getImgURL(), userInfo.getStreet(), userInfo.getX(), userInfo.getY(), userInfo.getSocialId(), userInfo.getSocialType());
    }

    public void updateUserCanCall(UserCallableRequestDto userCallableRequestDto) {
        userRepository.updateUserCanCall(userCallableRequestDto.getUserId(), userCallableRequestDto.isCanCall());
    }

    public UserLocationResponseDto findUserLocation(Long userId){
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if(optionalUser.isPresent() && optionalUser.get().isCanCall() == true){
            User user = optionalUser.get();
            UserLocationResponseDto locationResponseDto = new UserLocationResponseDto(user.getUserId(), user.getUsername(), user.getImgUrl(), Double.parseDouble(user.getX()), Double.parseDouble(user.getY()), user.getFcmToken());
            return locationResponseDto;
        } else
            return null;
    }

}