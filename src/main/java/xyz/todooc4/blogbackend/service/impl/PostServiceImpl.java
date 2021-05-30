package xyz.todooc4.blogbackend.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import xyz.todooc4.blogbackend.entity.*;
import xyz.todooc4.blogbackend.repository.PostRepository;
import xyz.todooc4.blogbackend.service.PostService;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class PostServiceImpl implements PostService {
	private final PostRepository postRepository;

	@Override
	public List<Post> findAll(Specification<Post> specification, Sort sort) {
		return postRepository.findAll(specification, sort == null ? Sort.unsorted() : sort);
	}

	@Override
	public Post findById(Integer postId) {
		return postRepository.findById(postId)
				.orElseThrow(() -> new NoSuchElementException("PostService.notFound"));
	}

	@Override
	public Post save(Post post) {
		return postRepository.save(post);
	}

	@Override
	public Post update(Post post) {
		return postRepository.save(post);
	}

	@Override
	public void deleteById(Integer postId) {
		postRepository.deleteById(postId);
	}


}