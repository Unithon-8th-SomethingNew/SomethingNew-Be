package unithon8th.somethingnew.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/ping")
    public String pong(){
        return "pong";
    }
}
