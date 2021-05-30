package xyz.todooc4.blogbackend.security;

import com.google.common.collect.ImmutableList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import xyz.todooc4.blogbackend.repository.UserRepository;
import xyz.todooc4.blogbackend.service.UserService;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final JwtProvider jwtProvider;
	private final UserRepository userRepository;
	private final Environment env;
	private final UserService userService;


	public SecurityConfig(JwtProvider jwtProvider,
	                      UserRepository userRepository,
	                      Environment env,
	                      @Lazy UserService userService) {
		this.jwtProvider = jwtProvider;
		this.userRepository = userRepository;
		this.env = env;
		this.userService = userService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http.httpBasic().disable()
					.cors().and()
					.csrf().disable()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and()
					.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/login").permitAll()
					.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
					.anyRequest().permitAll()
					.and()
					.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtProvider ))
					.addFilterBefore(new JwtAuthorizationFilter(jwtProvider, userService), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManager() {
		return new AuthenticationManagerImpl(userRepository, env, passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		var configuration = new CorsConfiguration();
		List<String> allowedAndExposedHeaders = List.of(
				HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,
				HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,
				HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,
				HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS,
				HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,
				HttpHeaders.AUTHORIZATION,
				HttpHeaders.CONTENT_TYPE);
		List<String> allowedMethods = List.of(
				HttpMethod.GET.name(),
				HttpMethod.DELETE.name(),
				HttpMethod.PUT.name(),
				HttpMethod.POST.name(),
				HttpMethod.OPTIONS.name());

		configuration.setAllowedOrigins(List.of("*"));
		configuration.setAllowedMethods(allowedMethods);
		configuration.setAllowedHeaders(allowedAndExposedHeaders);
		configuration.setExposedHeaders(allowedAndExposedHeaders);

		var source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}