package kr.co.ch10.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /*
    * {
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjbHNybnRrZGdoQGdtYWlsLmNvbSIsImlhdCI6MTc0MjUyNTE2MywiZXhwIjoxNzQyNjExNTYzLCJ1c2VybmFtZSI6InN0YWZmIiwicm9sZSI6IlNUQUZGIn0.OPQZsC3Wus5DYzquAgK-9bB9TglTygRR4WKIh8fuX80",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjbHNybnRrZGdoQGdtYWlsLmNvbSIsImlhdCI6MTc0MjUyNDY4NywiZXhwIjoxNzQzMTI5NDg3LCJ1c2VybmFtZSI6InN0YWZmIiwicm9sZSI6IlNUQUZGIn0.HnodQGASNqEwz1NtZAqFV5Kwv1vYnkSCNijP8Zf5Kck"
}
    * */

    private final JwtProvider jwtProvider;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 토큰 추출
        String header = request.getHeader(AUTHORIZATION_HEADER);
        log.info("Authorization header received: {}", header);

        // JWT 앞에 Bearer을 붙일 것이기에 검사
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {

            // 다음 필터로 넘어가기(컨트롤러(DispatcherServlet)로 들어가기)
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(TOKEN_PREFIX.length());
        log.info("JWT token: {}", token);

        // 토큰 검사
        try {
            jwtProvider.validateToken(token);

            // 토큰 검사 성공하면 시큐리티 인증처리
            Authentication authentication = jwtProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            // 토큰 검사 실패
            log.error("JWT token validation failed: {}", e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());
            return;
        }

        filterChain.doFilter(request, response);


    }
}
