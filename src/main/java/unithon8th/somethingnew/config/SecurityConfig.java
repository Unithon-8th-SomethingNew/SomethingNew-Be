package unithon8th.somethingnew.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
//커밋용주석
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        // swagger-ui.html의 경우 인증된 사용자가 아니어도 접근가능하도록 설정(dev환경에 대해서만 swagger 설정을 하였기 때문에 인증된 사용자가 아니어도 됨)
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/**").permitAll() //로그인 화면 접근 가능
                .antMatchers("/knock/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/friend/**").permitAll()
                .antMatchers("/").permitAll() //메인 화면 접근 가능
                .anyRequest().hasRole("USER")
                .and()
                .csrf()
                .disable();
    }
}
