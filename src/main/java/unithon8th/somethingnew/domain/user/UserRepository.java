package unithon8th.somethingnew.domain.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.Optional;
//커밋용주석
@SuppressWarnings("unchecked")  //검증되지 않은 연산자 관련 경고를 무시
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

    Optional<User> findByUserId(Long userId);

    Optional<User> findBySocialIdAndSocialType(String socialId, SocialType socialType);

    Optional<User> findByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.username = ?1, u.email = ?2, u.imgUrl = ?3, u.street = ?4, u.x = ?5, u.y = ?6 WHERE u.socialId = ?7 AND u.socialType = ?8")
    void updateUserBySocialIdAndSocialType(String username, String email, String imgURL, String Street, String x, String y, String socialId, SocialType socialType);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.canCall = ?2 WHERE u.userId = ?1")
    void updateUserCanCall(Long userId, boolean cancall);

    void delete(User user);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.street=?2 WHERE u.userId = ?1")
    void updateUserCallStreet(Long userId, String street);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.toTime=?2,u.fromTime=?3 WHERE u.userId = ?1")
    void updateUserCallTime(Long userId, LocalTime toTime, LocalTime fromTime);
}

