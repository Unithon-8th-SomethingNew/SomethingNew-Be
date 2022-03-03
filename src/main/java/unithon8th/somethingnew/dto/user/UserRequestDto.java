package unithon8th.somethingnew.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import unithon8th.somethingnew.domain.user.Role;
import unithon8th.somethingnew.domain.user.User;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class UserRequestDto {
    private String username;
    private String email;
    private String kakaoId;
    private String imgURL;
    private String refreshToken;

    public UserRequestDto(String username, String email, String kakaoId, String imgURL, String refreshToken) {
        this.username = username;
        this.email = email;
        this.kakaoId = kakaoId;
        this.imgURL = imgURL;
        this.refreshToken = refreshToken;
    }


    public UserRequestDto(String username, String email, String kakaoId, String imgURL) {
        this.username = username;
        this.email = email;
        this.kakaoId = kakaoId;
        this.imgURL = imgURL;
    }

    //UserRequestDto를 User Entity로 변환하여 return
    public User toEntity(){
        User user = new User(this.username, this.email, this.kakaoId, Role.USER, this.imgURL, this.refreshToken);
        return user;
    }

}
