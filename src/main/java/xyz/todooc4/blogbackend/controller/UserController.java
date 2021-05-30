package xyz.todooc4.blogbackend.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.todooc4.blogbackend.entity.*;
import xyz.todooc4.blogbackend.service.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllUsers")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(name = "q", required = false) Specification<User> specification, @RequestParam(name = "sort", required = false) Sort sort) {
		return ResponseEntity.ok(userService.findAll(specification, sort));
	}

	@GetMapping("/{userId}")
	@ApiOperation(value = "", nickname = "getUserById")
	public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
		return ResponseEntity.ok(userService.findById(userId));
	}

	@PostMapping
	@ApiOperation(value = "", nickname = "saveUser")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}

	@PutMapping
	@ApiOperation(value = "", nickname = "updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.update(user));
	}

	@DeleteMapping("/{userId}")
	@ApiOperation(value = "", nickname = "deleteUserById")
	public void deleteUserById(@PathVariable Integer userId) {
		userService.deleteById(userId);
	}

	@GetMapping("/{userId}/roles")
	@ApiOperation(value = "", nickname = "getUserRoles")
	public ResponseEntity<List<Role>> getUserRoles(@PathVariable Integer userId) {
		return ResponseEntity.ok(userService.findAllRolesById(userId));
	}

	@PostMapping("/{userId}/roles")
	@ApiOperation(value = "", nickname = "setUserRoles")
	public ResponseEntity<List<Role>> setUserRoles(@PathVariable Integer userId, @RequestBody List<Role> roles) {
		return ResponseEntity.ok(userService.setRolesById(userId, roles));
	}

	@PutMapping("/{userId}/roles")
	@ApiOperation(value = "", nickname = "addUserRoles")
	public ResponseEntity<List<Role>> addUserRoles(@PathVariable Integer userId, @RequestBody List<Role> roles) {
		return ResponseEntity.ok(userService.addRolesById(userId, roles));
	}

	@DeleteMapping("/{userId}/roles")
	@ApiOperation(value = "", nickname = "deleteUserRoles")
	public ResponseEntity<List<Role>> deleteUserRoles(@PathVariable Integer userId, @RequestBody List<Role> roles) {
		return ResponseEntity.ok(userService.deleteRolesById(userId, roles));
	}

}

