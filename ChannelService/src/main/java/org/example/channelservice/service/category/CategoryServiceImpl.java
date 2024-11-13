package org.example.channelservice.service.category;

import lombok.RequiredArgsConstructor;
import org.example.channelservice.domain.dto.request.CategoryRequest;
import org.example.channelservice.domain.dto.response.CategoryResponse;
import org.example.channelservice.entity.CategoryEntity;
import org.example.channelservice.exception.BaseException;
import org.example.channelservice.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public CategoryResponse create(CategoryRequest categoryRequest) {
        if (categoryRepository.existsByName(categoryRequest.getName())) {
            throw new BaseException("This category already exists", HttpStatus.CONFLICT.value());
        }
        CategoryEntity category = new CategoryEntity();
        category.setName(categoryRequest.getName());
        categoryRepository.save(category);

        return CategoryResponse.builder()
                .name(category.getName())
                .build();
    }

    @Override
    public CategoryEntity findById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new BaseException("Category not found", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public List<CategoryResponse> findAll() {
      return categoryRepository.findAllBy();
    }


    @Override
    public void update(UUID id, CategoryRequest categoryRequest) {

    }


}
