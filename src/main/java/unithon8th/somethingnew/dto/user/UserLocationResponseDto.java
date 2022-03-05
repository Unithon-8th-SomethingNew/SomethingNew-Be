package unithon8th.somethingnew.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class UserLocationResponseDto {
    private Long uid;
    private String username;
    private String profile_url;
    private double x;
    private double y;
    private String fcmToken;
}
