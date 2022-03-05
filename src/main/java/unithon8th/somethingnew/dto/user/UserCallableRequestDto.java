package unithon8th.somethingnew.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCallableRequestDto {
    private Long userId;
    private boolean canCall;
}
