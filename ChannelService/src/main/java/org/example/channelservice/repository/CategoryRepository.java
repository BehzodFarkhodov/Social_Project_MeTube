package org.example.channelservice.repository;

import org.example.channelservice.domain.dto.response.CategoryResponse;
import org.example.channelservice.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    Boolean existsByName(String name);
    List<CategoryResponse> findAllBy();
}
