package xyz.todooc4.blogbackend.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.todooc4.blogbackend.entity.User;
import xyz.todooc4.blogbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
@Primary
public class AuthenticationManagerImpl implements AuthenticationManager {
	private final UserRepository userRepository;
	private final Environment env;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		Optional<User> userOptional = userRepository.findByUsername(username);

		if (userOptional.isEmpty())
			throw new UsernameNotFoundException("User does not exist");

		User user = userOptional.get();


		if (!user.isCredentialsNonExpired()) {
			return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
		}

		if (Arrays.asList(env.getActiveProfiles()).contains("dev")){
			return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
		}

		if (!passwordEncoder.matches(password, user.getPassword()))
			throw new BadCredentialsException("Invalid username or password");

		return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
	}
}
