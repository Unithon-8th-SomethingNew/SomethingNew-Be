package unithon8th.somethingnew.domain.friend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    Friend save(Friend friend);

    Optional<Friend> findByFromUserIdAndToUserId(Long fromUserId, Long toUserId);

    List<Friend> findByFromUserIdOrToUserId(Long toUserId, Long fromUserId);//둘다 같은 Id

}
