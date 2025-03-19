package kr.co.ch08.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 로그인 설정
        http.formLogin(login->login
                                    .loginPage("/user/login")
                                    .defaultSuccessUrl("/")   //성공 후 이동(리다이렉트)
                                    .failureUrl("/user/login?code=100") //실패 후 이동
                                    .usernameParameter("uid")      // id="uid"
                                    .passwordParameter("pass"));   // id="pass"

        // 로그아웃 설정
        http.logout(logout->logout
                .logoutUrl("/user/logout")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/user/login?code=101")); //세션 무효화 후 이동

        // 인가 설정
        http.authorizeHttpRequests(authorize-> authorize
                .requestMatchers("/").permitAll() // ("/") 요청에 대해서 모든 요청을 허락해준다.
                .anyRequest().permitAll());

        // 기타 보안 설정
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    /*
    * 기존의 비밀번호을 암호화하는 방식의 경우
    * 비밀번호를 암호화햇을 때 값이 같다.
    * 같은 비밀번호 1234라도 암호화 비밀번호를 제공하는 것이 좋다.
    * Spring Security 에서는 이러한 기능을 제공한다.
    * */

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Security 암호화 인코더 설정
        return new BCryptPasswordEncoder();
    }
}
