package unithon8th.somethingnew.dto.user.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Data
public class UserCallTimeRequestDto {

    private Long userId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime toTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime fromTime;
}
