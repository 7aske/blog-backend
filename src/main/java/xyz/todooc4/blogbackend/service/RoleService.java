package xyz.todooc4.blogbackend.service;

import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import xyz.todooc4.blogbackend.entity.*;

public interface RoleService {

	List<Role> findAll(Specification<Role> specification, Sort sort);

	Role save(Role role);

	Role update(Role role);

	Role findById(Integer roleId);

	void deleteById(Integer roleId);

	List<User> findAllUsersById(Integer roleId);

	List<User> addUsersById(Integer roleId, List<User> users);

	List<User> setUsersById(Integer roleId, List<User> users);

	List<User> deleteUsersById(Integer roleId, List<User> users);

}