package xyz.todooc4.blogbackend.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import xyz.todooc4.blogbackend.entity.*;
import xyz.todooc4.blogbackend.repository.RoleRepository;
import xyz.todooc4.blogbackend.service.RoleService;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RoleServiceImpl implements RoleService {
	private final RoleRepository roleRepository;

	@Override
	public List<Role> findAll(Specification<Role> specification, Sort sort) {
		return roleRepository.findAll(specification, sort == null ? Sort.unsorted() : sort);
	}

	@Override
	public Role findById(Integer roleId) {
		return roleRepository.findById(roleId)
				.orElseThrow(() -> new NoSuchElementException("RoleService.notFound"));
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role update(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void deleteById(Integer roleId) {
		roleRepository.deleteById(roleId);
	}

	@Override
	public List<User> findAllUsersById(Integer roleId) {
		return findById(roleId).getUsers();
	}

	@Override
	public List<User> addUsersById(Integer roleId, List<User> users) {
		Role role = findById(roleId);
		role.getUsers().addAll(users);
		return roleRepository.save(role).getUsers();
	}

	@Override
	public List<User> setUsersById(Integer roleId, List<User> users) {
		Role role = findById(roleId);
		role.setUsers(users);
		return roleRepository.save(role).getUsers();
	}

	@Override
	public List<User> deleteUsersById(Integer roleId, List<User> users) {
		Role role = findById(roleId);
		role.getUsers().removeAll(users);
		return roleRepository.save(role).getUsers();
	}


}