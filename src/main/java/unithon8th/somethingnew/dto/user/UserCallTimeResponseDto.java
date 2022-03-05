package unithon8th.somethingnew.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalTime;

@Data
@ToString
@AllArgsConstructor
public class UserCallTimeResponseDto {
    private Long userId;
    private LocalTime toTime;
    private LocalTime fromTime;
}
