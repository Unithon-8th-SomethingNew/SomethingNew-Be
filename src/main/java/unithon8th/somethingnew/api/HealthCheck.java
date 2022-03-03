package unithon8th.somethingnew.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class HealthCheck {

    @GetMapping("/ping")
    public String pong(){
        return "pong";
    }

}
