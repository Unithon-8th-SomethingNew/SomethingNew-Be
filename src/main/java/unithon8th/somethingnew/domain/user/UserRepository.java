package unithon8th.somethingnew.domain.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
//커밋용주석
@SuppressWarnings("unchecked")  //검증되지 않은 연산자 관련 경고를 무시
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

    Optional<User> findByUserId(Long userId);

    Optional<User> findByKakaoId(String kakaoId);

    Optional<User> findByNaverId(String naverId);


    //update 부분 jpa 변경감지로 변환
    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.username = ?1, u.email = ?2, u.imgUrl = ?3, u.refreshToken = ?4 WHERE u.socialId = ?5")
    void updateUserByKakaoId(String username, String email, String imgURL, String refreshToken, String socialId);

    @Query("SELECT u.refreshToken FROM User u WHERE u.socialId = ?1")
    String findRefreshTokenByKakaoId(String kakaoId);

    void delete(User user);

}

