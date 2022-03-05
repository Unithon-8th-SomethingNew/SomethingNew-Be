package unithon8th.somethingnew.dto.friend;

import lombok.Builder;
import lombok.Getter;


@Getter
public class FriendLocationResponseDto {
    private Long userId;
    private String username;
    private String email;
    private String imgUrl;
    private boolean canCall;
    private String street;
    private String x;
    private String y;

    @Builder
    public FriendLocationResponseDto(Long userId, String username, String email, String imgUrl, boolean canCall, String street, String x, String y) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.imgUrl = imgUrl;
        this.canCall = canCall;
        this.street = street;
        this.x = x;
        this.y = y;
    }
}
