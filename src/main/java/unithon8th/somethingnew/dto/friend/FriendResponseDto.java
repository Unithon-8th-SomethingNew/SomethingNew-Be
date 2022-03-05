package unithon8th.somethingnew.dto.friend;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FriendResponseDto {
    private Long userId;
    private String username;
    private String email;
    private String imgUrl;
    private boolean canCall;
    private String street;

    @Builder
    public FriendResponseDto(Long userId, String username, String email, String imgUrl, boolean canCall, String street) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.imgUrl = imgUrl;
        this.canCall = canCall;
        this.street = street;
    }
}
