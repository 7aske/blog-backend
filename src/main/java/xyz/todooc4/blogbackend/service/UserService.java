package xyz.todooc4.blogbackend.service;

import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import xyz.todooc4.blogbackend.entity.*;

public interface UserService {

	List<User> findAll(Specification<User> specification, Sort sort);

	User save(User user);

	User update(User user);

	User findById(Integer userId);

	void deleteById(Integer userId);

	List<Role> findAllRolesById(Integer userId);

	List<Role> addRolesById(Integer userId, List<Role> roles);

	List<Role> setRolesById(Integer userId, List<Role> roles);

	List<Role> deleteRolesById(Integer userId, List<Role> roles);

}