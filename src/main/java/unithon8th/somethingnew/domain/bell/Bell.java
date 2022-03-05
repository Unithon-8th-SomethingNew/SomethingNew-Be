//package unithon8th.somethingnew.domain.bell;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import unithon8th.somethingnew.domain.user.User;
//
//import javax.persistence.*;
//
//@Getter
//@Entity
//@Table(name = "BELL")
//@NoArgsConstructor
//public class Bell {
//    @Id @GeneratedValue
//    private Long bellId;
//
//    @ManyToOne
//    @JoinColumn(name = "userId")
//    private User fromUser;
//
//    @Column
//    private Long toUserId;
//
//    @Column
//    private boolean canPush;
//
//    @Column
//    private int count;
//
//    public Bell(User fromUser, Long toUserId, boolean canPush, int count) {
//        this.fromUser = fromUser;
//        this.toUserId = toUserId;
//        this.canPush = canPush;
//        this.count = count;
//    }
//}
