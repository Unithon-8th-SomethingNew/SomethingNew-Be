package unithon8th.somethingnew.dto.user.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@Setter
public class UserCallTimeRequestDto {

    private Long userId;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime toTime;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime fromTime;
}
