package unithon8th.somethingnew.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor//커밋용주석
public class UserResponseDto {
    private Long userId;
    private String username;
    private String imgUrl;
}
