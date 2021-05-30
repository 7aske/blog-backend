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
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryService categoryService;

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllCategories")
	public ResponseEntity<List<Category>> getAllCategories(@RequestParam(name = "q", required = false) Specification<Category> specification, @RequestParam(name = "sort", required = false) Sort sort) {
		return ResponseEntity.ok(categoryService.findAll(specification, sort));
	}

	@GetMapping("/{categoryId}")
	@ApiOperation(value = "", nickname = "getCategoryById")
	public ResponseEntity<Category> getCategoryById(@PathVariable Integer categoryId) {
		return ResponseEntity.ok(categoryService.findById(categoryId));
	}

	@PostMapping
	@ApiOperation(value = "", nickname = "saveCategory")
	public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
	}

	@PutMapping
	@ApiOperation(value = "", nickname = "updateCategory")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
		return ResponseEntity.ok(categoryService.update(category));
	}

	@DeleteMapping("/{categoryId}")
	@ApiOperation(value = "", nickname = "deleteCategoryById")
	public void deleteCategoryById(@PathVariable Integer categoryId) {
		categoryService.deleteById(categoryId);
	}

}

