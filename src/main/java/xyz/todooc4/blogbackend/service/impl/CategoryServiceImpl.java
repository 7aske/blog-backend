package xyz.todooc4.blogbackend.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import xyz.todooc4.blogbackend.entity.*;
import xyz.todooc4.blogbackend.repository.CategoryRepository;
import xyz.todooc4.blogbackend.service.CategoryService;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll(Specification<Category> specification, Sort sort) {
		return categoryRepository.findAll(specification, sort == null ? Sort.unsorted() : sort);
	}

	@Override
	public Category findById(Integer categoryId) {
		return categoryRepository.findById(categoryId)
				.orElseThrow(() -> new NoSuchElementException("CategoryService.notFound"));
	}

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category update(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public void deleteById(Integer categoryId) {
		categoryRepository.deleteById(categoryId);
	}


}