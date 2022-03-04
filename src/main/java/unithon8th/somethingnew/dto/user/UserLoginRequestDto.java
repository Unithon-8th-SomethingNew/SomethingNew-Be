package unithon8th.somethingnew.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class UserLoginRequestDto {
    private String token;
    private String fcmToken;
    private String street;
}
