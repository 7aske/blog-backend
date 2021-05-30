package xyz.todooc4.blogbackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.todooc4.blogbackend.data.dto.LoginRequest;
import xyz.todooc4.blogbackend.data.dto.RegisterRequest;
import xyz.todooc4.blogbackend.entity.User;
import xyz.todooc4.blogbackend.repository.UserRepository;
import xyz.todooc4.blogbackend.security.JwtProvider;

@Service
public class AuthSecurity {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;

    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setFullName(registerRequest.getFullName());
        user.setUsername(registerRequest.getUsername());
        user.setDisplayName(registerRequest.getDisplayName());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setAbout(registerRequest.getAbout());

        userRepository.save(user);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public String login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return jwtProvider.generateToken(authenticate);
    }
}
