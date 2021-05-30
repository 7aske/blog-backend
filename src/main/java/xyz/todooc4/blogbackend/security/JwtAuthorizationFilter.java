package xyz.todooc4.blogbackend.security;

import xyz.todooc4.blogbackend.entity.User;
import xyz.todooc4.blogbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends GenericFilterBean {
    private final JwtProvider jwtProvider;
    private final UserService userService;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtProvider.resolveToken((HttpServletRequest) req);
        boolean isTokenValid = token != null && jwtProvider.validateToken(token);
        if (isTokenValid) {
            Authentication auth = authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(req, res);
    }

    private Authentication authenticate(String token) {
            String username = jwtProvider.getUsername(token);
            User user = this.userService.findByUsername(username);
            return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
    }
}