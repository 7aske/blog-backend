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
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllComments")
	public ResponseEntity<List<Comment>> getAllComments(@RequestParam(name = "q", required = false) Specification<Comment> specification, @RequestParam(name = "sort", required = false) Sort sort) {
		return ResponseEntity.ok(commentService.findAll(specification, sort));
	}

	@GetMapping("/{commentId}")
	@ApiOperation(value = "", nickname = "getCommentById")
	public ResponseEntity<Comment> getCommentById(@PathVariable Integer commentId) {
		return ResponseEntity.ok(commentService.findById(commentId));
	}

	@PostMapping
	@ApiOperation(value = "", nickname = "saveComment")
	public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
		return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(comment));
	}

	@PutMapping
	@ApiOperation(value = "", nickname = "updateComment")
	public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
		return ResponseEntity.ok(commentService.update(comment));
	}

	@DeleteMapping("/{commentId}")
	@ApiOperation(value = "", nickname = "deleteCommentById")
	public void deleteCommentById(@PathVariable Integer commentId) {
		commentService.deleteById(commentId);
	}

}

