package unithon8th.somethingnew.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class UserSettingResponseDto {
    private Long userId;
    private boolean canCall;
    private String street;
    private LocalTime toTime;
    private LocalTime fromTime;

    @Builder
    public UserSettingResponseDto(Long userId, boolean canCall, String street, LocalTime toTime, LocalTime fromTime) {
        this.userId = userId;
        this.canCall = canCall;
        this.street = street;
        this.toTime = toTime;
        this.fromTime = fromTime;
    }
}
