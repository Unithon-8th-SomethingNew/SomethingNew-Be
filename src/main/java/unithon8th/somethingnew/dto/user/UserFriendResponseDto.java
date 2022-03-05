package unithon8th.somethingnew.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserFriendResponseDto {
    private Long userId;
    private String username;
    private String email;
    private String imgUrl;
    private boolean canCall;

    @Builder
    public UserFriendResponseDto(Long userId, String username, String email, String imgUrl, boolean canCall) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.imgUrl = imgUrl;
        this.canCall = canCall;
    }
}
