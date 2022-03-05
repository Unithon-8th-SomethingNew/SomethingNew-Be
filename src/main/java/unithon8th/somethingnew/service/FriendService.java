package unithon8th.somethingnew.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unithon8th.somethingnew.domain.friend.Friend;
import unithon8th.somethingnew.domain.friend.FriendRepository;
import unithon8th.somethingnew.domain.user.User;
import unithon8th.somethingnew.domain.user.UserRepository;
import unithon8th.somethingnew.dto.user.UserFriendResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FriendService {
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public Friend saveFriend(String toUserEmail, Long fromUserId) {
        Optional<User> optionalUser = userRepository.findByEmail(toUserEmail);

        if (optionalUser.isPresent()) {
            Long toUserId = optionalUser.get().getUserId();
            Optional<Friend> find1 = friendRepository.findByFromUserIdAndToUserId(toUserId, fromUserId);
            Optional<Friend> find2 = friendRepository.findByFromUserIdAndToUserId(fromUserId, toUserId);
            Friend result;

            if (!find1.isPresent() && !find2.isPresent()) {
                Friend friend = new Friend(toUserId, fromUserId);
                result = friendRepository.save(friend);
            } else result = null;
            return result;
        } else return null;
    }

    public List<UserFriendResponseDto> getUserFriendResponseDtos(Long userId) {
        List<Friend> friendList = friendRepository.findByFromUserIdOrToUserId(userId, userId);
        List<Long> friendsIdList= new ArrayList<>();
        for (Friend friend : friendList) {
            if(friend.getFromUserId() == userId)
                friendsIdList.add(friend.getToUserId());
            else
                friendsIdList.add(friend.getFromUserId());
        }
        List<UserFriendResponseDto> userList = new ArrayList<>();
        for (Long id : friendsIdList) {
            User user = userService.findUserByUserId(id).get();
            UserFriendResponseDto userDto = UserFriendResponseDto.builder()
                    .email(user.getEmail())
                    .imgUrl(user.getImgUrl())
                    .canCall(user.isCanCall())
                    .username(user.getUsername())
                    .userId(id)
                    .street(user.getStreet())
                    .build();
            userList.add(userDto);
        }
        return userList;
    }
}
