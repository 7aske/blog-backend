package xyz.todooc4.blogbackend.service;

import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import xyz.todooc4.blogbackend.entity.*;

public interface CommentService {

	List<Comment> findAll(Specification<Comment> specification, Sort sort);

	Comment save(Comment comment);

	Comment update(Comment comment);

	Comment findById(Integer commentId);

	void deleteById(Integer commentId);

}