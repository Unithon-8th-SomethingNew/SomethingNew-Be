//package unithon8th.somethingnew.domain.bell;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import unithon8th.somethingnew.domain.user.SocialType;
//import unithon8th.somethingnew.domain.user.User;
//
//import java.util.Optional;
//
//public interface BellRepository extends JpaRepository<Bell, Long> {
//
//    Bell save(Bell bell);
//
//    Optional<Bell> findByBellId(Long bellId);
//
//    Optional<Bell> findByFromUserAndToUserId(User user,Long userId);
//
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE Bell b SET b.count = ?3 WHERE b.fromUser = ?1 AND b.toUserId = ?2")
//    void upperCount(User fromUser, Long toUserId, int upperCnt);
//
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE Bell b SET b.canPush = ?3 WHERE b.fromUser = ?1 AND b.toUserId = ?2")
//    void turnCanPush(User fromUser, Long toUserId, boolean check);
//}
