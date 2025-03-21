package kr.co.ch08.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity(securedEnabled = true)
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

        /*
        *   인가 설정
        *       - MyUserDetails 권한 목록 생성하는 메서드(getAuthorities)에서 접두어로 ROLE_ 입력해야
        *         hasRole, hasAnyRole 권한 처리됨
        *       - Spring Security는 기본적으로 인가 페이지에 대해 기본적으로 로그인 페이지로 redirect 수행
        *           예) 매니저 페이지에서 로그인 창 이동 -> 로그인 후 매니저 페이지로 이동
        *
        *
        * */

        http.authorizeHttpRequests(authorize-> authorize
                .requestMatchers("/").permitAll() // ("/") 요청에 대해서 모든 요청을 허락해준다.
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/manager/**").hasAnyRole("ADMIN","MANAGER") // hasAnyRole : 2가지 이상 권한을 설정할 때
                .requestMatchers("/staff/**").hasAnyRole("ADMIN","MANAGER","STAFF")
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
