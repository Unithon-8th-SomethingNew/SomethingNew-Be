package unithon8th.somethingnew.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import unithon8th.somethingnew.domain.friend.Friend;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@NoArgsConstructor
@Table(name = "USER")
@Entity
public class User {//커밋용주석

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = true, unique = true)
    private String email;

    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "socialType")
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name = "socialId",nullable = false)
    private String socialId;

    @Column(name = "imgUrl")
    private String imgUrl;

    @Column(name = "fcmToken")
    private String fcmToken;

    @Column(name = "canCall")
    private boolean canCall = false;

    @Column(name = "street")
    private String street;

    private String x;

    private String y;


    public User(String username, String email, String socialId, Role role, String imgUrl, SocialType socialType, String fcmToken,String street,String x,String y) {
        this.username = username;
        this.email = email;
        this.socialId = socialId;
        this.role = role;
        this.imgUrl = imgUrl;
        this.socialType = socialType;
        this.fcmToken = fcmToken;
        this.x=x;
        this.y=y;
        this.street=street;
    }

    public void toUpdateUser(String username,String email,String imgUrl){
        this.username=username;
        this.email=email;
        this.imgUrl=imgUrl;
    }
}