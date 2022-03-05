package unithon8th.somethingnew.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import unithon8th.somethingnew.domain.friend.Friend;
import unithon8th.somethingnew.domain.user.Role;
import unithon8th.somethingnew.domain.user.SocialType;
import unithon8th.somethingnew.domain.user.User;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class UserRequestDto {
    private String username;
    private String email;
    private String socialId;
    private SocialType socialType;
    private String imgURL;
    private String fcmToken;
    private String street;
    private String x;
    private String y;

    //커밋용주석
    //UserRequestDto를 User Entity로 변환하여 return
    public User toEntity(){
        User user = new User(this.username, this.email, this.socialId, Role.USER, this.imgURL, this.socialType, this.fcmToken, this.street, this.x, this.y);
        return user;
    }

}
