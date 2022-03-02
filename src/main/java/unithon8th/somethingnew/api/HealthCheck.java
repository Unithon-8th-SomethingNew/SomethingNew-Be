package unithon8th.somethingnew.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import unithon8th.somethingnew.domain.Member;
import unithon8th.somethingnew.domain.MemberRepository;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class HealthCheck {
    private final MemberRepository memberRepository;

    @GetMapping("/ping")
    public String pong(){
        return "pong";
    }

    @GetMapping("/find")
    public String findMember(){
        Member member = new Member("awd", 23);
        memberRepository.save(member);

        Optional<Member> byId = memberRepository.findById(1L);
        System.out.println("member.getName() = " + byId.get().getName());
        return byId.get().getName();
    }
}
