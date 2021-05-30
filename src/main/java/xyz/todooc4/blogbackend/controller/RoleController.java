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
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
	private final RoleService roleService;

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllRoles")
	public ResponseEntity<List<Role>> getAllRoles(@RequestParam(name = "q", required = false) Specification<Role> specification, @RequestParam(name = "sort", required = false) Sort sort) {
		return ResponseEntity.ok(roleService.findAll(specification, sort));
	}

	@GetMapping("/{roleId}")
	@ApiOperation(value = "", nickname = "getRoleById")
	public ResponseEntity<Role> getRoleById(@PathVariable Integer roleId) {
		return ResponseEntity.ok(roleService.findById(roleId));
	}

	@PostMapping
	@ApiOperation(value = "", nickname = "saveRole")
	public ResponseEntity<Role> saveRole(@RequestBody Role role) {
		return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(role));
	}

	@PutMapping
	@ApiOperation(value = "", nickname = "updateRole")
	public ResponseEntity<Role> updateRole(@RequestBody Role role) {
		return ResponseEntity.ok(roleService.update(role));
	}

	@DeleteMapping("/{roleId}")
	@ApiOperation(value = "", nickname = "deleteRoleById")
	public void deleteRoleById(@PathVariable Integer roleId) {
		roleService.deleteById(roleId);
	}

	@GetMapping("/{roleId}/users")
	@ApiOperation(value = "", nickname = "getRoleUsers")
	public ResponseEntity<List<User>> getRoleUsers(@PathVariable Integer roleId) {
		return ResponseEntity.ok(roleService.findAllUsersById(roleId));
	}

	@PostMapping("/{roleId}/users")
	@ApiOperation(value = "", nickname = "setRoleUsers")
	public ResponseEntity<List<User>> setRoleUsers(@PathVariable Integer roleId, @RequestBody List<User> users) {
		return ResponseEntity.ok(roleService.setUsersById(roleId, users));
	}

	@PutMapping("/{roleId}/users")
	@ApiOperation(value = "", nickname = "addRoleUsers")
	public ResponseEntity<List<User>> addRoleUsers(@PathVariable Integer roleId, @RequestBody List<User> users) {
		return ResponseEntity.ok(roleService.addUsersById(roleId, users));
	}

	@DeleteMapping("/{roleId}/users")
	@ApiOperation(value = "", nickname = "deleteRoleUsers")
	public ResponseEntity<List<User>> deleteRoleUsers(@PathVariable Integer roleId, @RequestBody List<User> users) {
		return ResponseEntity.ok(roleService.deleteUsersById(roleId, users));
	}

}

