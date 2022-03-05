package unithon8th.somethingnew.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class UserCallStreetResponseDto {
    private Long userId;
    private String street;


}
