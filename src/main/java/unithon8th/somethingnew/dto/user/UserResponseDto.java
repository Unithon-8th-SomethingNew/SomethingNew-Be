package unithon8th.somethingnew.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor//커밋용주석
public class UserResponseDto {
    private Long uid;
    private String name;
    private String profile_url;
}
