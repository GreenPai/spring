package kr.co.ch08.security;

import kr.co.ch08.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
public class MyUserDetails implements UserDetails {

    private User user;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한 목록 생성
        List<GrantedAuthority> authorities = new ArrayList<>();
        /*
        * 계정 권한 앞에 ROLE_ 이라는 접두어를 맞춰 user의 Role을 정한다.
        * */
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPass();
    }

    @Override
    public String getUsername() {
        return user.getUid();
    }

    /* 계정 만료 여부*/
    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 여부(true : 만료안됨, false : 만료됨)
        return true;
    }

    /* 계정 잠김 여부 */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /* 비밀번호 만료 여부*/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /* 계정 활성화 여부*/
    @Override
    public boolean isEnabled() {
        return true;
    }

}
