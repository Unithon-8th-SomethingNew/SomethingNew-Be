package unithon8th.somethingnew.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import unithon8th.somethingnew.domain.user.Role;
import unithon8th.somethingnew.domain.user.SocialType;
import unithon8th.somethingnew.domain.user.User;

@Data
@ToString
@NoArgsConstructor//커밋용주석
public class NaverRequestDto {
    private String socialId;
    private String username;
    private String email;
    private String age;
    private String profileImage;


    public NaverRequestDto(String naverId, String username, String email, String age, String profileImage) {
        this.socialId=naverId;
        this.username = username;
        this.email = email;
        this.age = age;
        this.profileImage = profileImage;
    }

    public User toEntity(){
        User user=new User(this.username,this.email,this.socialId, Role.USER,this.profileImage,null, SocialType.NAVER);
        return user;
    }



}
