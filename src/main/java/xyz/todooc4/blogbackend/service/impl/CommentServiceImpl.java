package xyz.todooc4.blogbackend.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import xyz.todooc4.blogbackend.entity.*;
import xyz.todooc4.blogbackend.repository.CommentRepository;
import xyz.todooc4.blogbackend.service.CommentService;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class CommentServiceImpl implements CommentService {
	private final CommentRepository commentRepository;

	@Override
	public List<Comment> findAll(Specification<Comment> specification, Sort sort) {
		return commentRepository.findAll(specification, sort == null ? Sort.unsorted() : sort);
	}

	@Override
	public Comment findById(Integer commentId) {
		return commentRepository.findById(commentId)
				.orElseThrow(() -> new NoSuchElementException("CommentService.notFound"));
	}

	@Override
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public Comment update(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public void deleteById(Integer commentId) {
		commentRepository.deleteById(commentId);
	}


}