package com.proyecto2.gestion_pedidos.infrastructure.persistence.adapter;

import com.proyecto2.gestion_pedidos.core.entity.Category;
import com.proyecto2.gestion_pedidos.core.usecase.port.out.category.CategoryRepositoryPort;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.entity.CategoryEntity;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.jpa.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryRepositoryAdapter implements CategoryRepositoryPort {
    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category){
        CategoryEntity entity = toEntity(category);
        CategoryEntity saved =categoryRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Category> findAll(){
        return categoryRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public List<Category> findByActive(boolean active){
        return categoryRepository.findByActive(active).stream().map(this::toDomain).toList();
    }

    @Override
    public Category update(Category category){
        CategoryEntity entity = toEntity(category);
        CategoryEntity updated = categoryRepository.save(entity);
        return toDomain(updated);
    }

    @Override
    public Category delete(Category category){
        CategoryEntity entity = toEntity(category);
        CategoryEntity deleted = categoryRepository.save(entity);
        return toDomain(deleted);
    }


    //
    private CategoryEntity toEntity(Category category){
        CategoryEntity entity = new CategoryEntity();
        entity.setId(category.getId());
        entity.setName(category.getName());
        entity.setDescription(category.getDescription());
        entity.setActive(category.getActive());
        return entity;
    }
    private Category toDomain(CategoryEntity entity) {
        return Category.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .active(entity.getActive()).build();
    }

}
