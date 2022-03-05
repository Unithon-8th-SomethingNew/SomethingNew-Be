package unithon8th.somethingnew.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unithon8th.somethingnew.domain.friend.Friend;
import unithon8th.somethingnew.domain.friend.FriendRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FriendService {
    private final FriendRepository friendRepository;

    public boolean saveFriend(Long toUserId, Long fromUserId) {

        Optional<Friend> find1 = friendRepository.findByFromUserIdAndToUserId(toUserId, fromUserId);
        Optional<Friend> find2 = friendRepository.findByFromUserIdAndToUserId(fromUserId, toUserId);

        if (!find1.isPresent() && !find2.isPresent()) {
            Friend friend = new Friend(toUserId, fromUserId);
            friendRepository.save(friend);
            return true;
        } else return false;
    }
}
