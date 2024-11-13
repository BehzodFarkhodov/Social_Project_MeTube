package org.example.channelservice.service.category;

import org.example.channelservice.domain.dto.request.CategoryRequest;
import org.example.channelservice.domain.dto.response.CategoryResponse;
import org.example.channelservice.entity.CategoryEntity;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryResponse create(CategoryRequest categoryRequest);
    CategoryEntity findById(UUID id);
    List<CategoryResponse> findAll();
    void update(UUID id, CategoryRequest categoryRequest);
}
