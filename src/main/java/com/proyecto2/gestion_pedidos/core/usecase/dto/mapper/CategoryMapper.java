package com.proyecto2.gestion_pedidos.core.usecase.dto.mapper;

import com.proyecto2.gestion_pedidos.core.entity.Category;
import com.proyecto2.gestion_pedidos.core.usecase.dto.request.CategoryRequest;
import com.proyecto2.gestion_pedidos.core.usecase.dto.response.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toDomain(CategoryRequest request){
        return Category.builder()
                .name(request.getName())
                .description(request.getDescription()).build();
    }
    public CategoryResponse toResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .active(category.getActive()).build();
    }
}
