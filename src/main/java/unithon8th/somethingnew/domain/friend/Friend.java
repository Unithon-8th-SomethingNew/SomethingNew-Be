package unithon8th.somethingnew.domain.friend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import unithon8th.somethingnew.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Friend {

    @Id @GeneratedValue
    private Long friendId;

    @Column(name = "fromUserId")
    private Long fromUserId;

    @Column(name = "toUserId")
    private Long toUserId;

    public Friend(Long fromUserId, Long toUserId) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }
}
