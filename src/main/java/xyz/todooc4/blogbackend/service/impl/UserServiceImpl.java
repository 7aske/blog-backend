package xyz.todooc4.blogbackend.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.todooc4.blogbackend.data.dto.RegisterRequest;
import xyz.todooc4.blogbackend.entity.*;
import xyz.todooc4.blogbackend.repository.UserRepository;
import xyz.todooc4.blogbackend.service.UserService;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public List<User> findAll(Specification<User> specification, Sort sort) {
		return userRepository.findAll(specification, sort == null ? Sort.unsorted() : sort);
	}

	@Override
	public User findById(Integer userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new NoSuchElementException("UserService.notFound"));
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new NoSuchElementException("UserService.notFound"));
	}

	@Override
	public User signup(RegisterRequest registerRequest) {
		User user = new User();
		user.setFullName(registerRequest.getFullName());
		user.setUsername(registerRequest.getUsername());
		user.setDisplayName(registerRequest.getDisplayName());
		user.setPassword(encodePassword(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		user.setAbout(registerRequest.getAbout());

		return userRepository.save(user);
	}

	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteById(Integer userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public List<Role> findAllRolesById(Integer userId) {
		return findById(userId).getRoles();
	}

	@Override
	public List<Role> addRolesById(Integer userId, List<Role> roles) {
		User user = findById(userId);
		user.getRoles().addAll(roles);
		return userRepository.save(user).getRoles();
	}

	@Override
	public List<Role> setRolesById(Integer userId, List<Role> roles) {
		User user = findById(userId);
		user.setRoles(roles);
		return userRepository.save(user).getRoles();
	}

	@Override
	public List<Role> deleteRolesById(Integer userId, List<Role> roles) {
		User user = findById(userId);
		user.getRoles().removeAll(roles);
		return userRepository.save(user).getRoles();
	}


}