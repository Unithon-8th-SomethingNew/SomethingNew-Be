package unithon8th.somethingnew.dto.user.response;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class UserCallTimeResponseDto {
    private Long userId;
    private LocalTime toTime;
    private LocalTime fromTime;
}
