package unithon8th.somethingnew.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unithon8th.somethingnew.domain.user.User;
import unithon8th.somethingnew.domain.user.UserRepository;
import unithon8th.somethingnew.dto.user.NaverRequestDto;
import unithon8th.somethingnew.dto.user.UserRequestDto;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void insertOrUpdateUser(UserRequestDto userRequestDto) {
        String kakaoId = userRequestDto.getSocialId();
        //처음 로그인 하는 유저면 DB에 insert
        if(!findUserByKakaoId(kakaoId).isPresent()){
            User user = userRequestDto.toEntity(); //기본 Role = ROLE.USER
            userRepository.save(user);
        }else{ //이미 로그인 했던 유저라면 DB update
            updateUserByKakaoId(userRequestDto);
        }
    }

    public void naverInsertOrUpdate(NaverRequestDto naverRequestDto){
        String naverId = naverRequestDto.getSocialId();

        if(!findUserByNaverId(naverId).isPresent()){
            User user = naverRequestDto.toEntity();
            userRepository.save(user);
        }else {
            User user = naverRequestDto.toEntity();
            user.toUpdateUser(naverRequestDto.getUsername(),naverRequestDto.getEmail(),naverRequestDto.getProfileImage());
        }
    }

    public Optional<User> findUserByUserId(Long userId){
        Optional<User> user = userRepository.findByUserId(userId);
        return user;
    }

    public Optional<User> findUserByKakaoId(String kakaoId){
        Optional<User> user = userRepository.findByKakaoId(kakaoId);
        return user;
    }

    public Optional<User> findUserByNaverId(String naverId){
        Optional<User> user = userRepository.findByNaverId(naverId);
        return user;
    }

    public void updateUserByKakaoId(UserRequestDto userInfo){
        userRepository.updateUserByKakaoId(userInfo.getUsername(), userInfo.getEmail(), userInfo.getImgURL(), userInfo.getRefreshToken(), userInfo.getSocialId());
    }

    public String findRefreshTokenByKakaoId(String kakaoId){
        return userRepository.findRefreshTokenByKakaoId(kakaoId);
    }

    public void deleteUserByKakaoId(String kakaoId){
        Optional<User> user = userRepository.findByKakaoId(kakaoId);
        if(user.isPresent())
            userRepository.delete(user.get());
        else
            throw new NullPointerException();
    }



}