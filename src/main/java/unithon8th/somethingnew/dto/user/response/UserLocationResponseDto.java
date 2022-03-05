package unithon8th.somethingnew.dto.user.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class UserLocationResponseDto {
    private Long uid;
    private String username;
    private String profile_url;
    private double x;
    private double y;
    private String fcmToken;
}
