package com.proyecto2.gestion_pedidos.infrastructure.controller;

import com.proyecto2.gestion_pedidos.core.entity.Category;
import com.proyecto2.gestion_pedidos.core.usecase.dto.mapper.CategoryMapper;
import com.proyecto2.gestion_pedidos.core.usecase.dto.request.CategoryRequest;
import com.proyecto2.gestion_pedidos.core.usecase.dto.response.CategoryResponse;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.category.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final RegisterCategoryCase registerCategoryCase;
    private final GetCategoryCase getCategoryCase;
    private final GetAllCategoryCase getAllCategoryCase;
    private final UpdateCategoryCase updateCategoryCase;
    private final DeleteCategoryCase deleteCategoryCase;
    private final RestoreCategoryCase restoreCategoryCase;
    private final CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request) {
        Category created = registerCategoryCase.registerCategory(categoryMapper.toDomain(request));
        return new ResponseEntity<>(categoryMapper.toResponse(created), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long id) {
        Category category = getCategoryCase.getCategory(id);
        return ResponseEntity.ok(categoryMapper.toResponse(category));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getActivesCategory(){
        List<CategoryResponse> responses = getAllCategoryCase.getActiveCategories().stream()
                .map(categoryMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponse>> getAllCategory() {
        List<CategoryResponse> responses = getAllCategoryCase.getAllCategories().stream()
                .map(categoryMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<CategoryResponse>> getInactivesCategory(){
        List<CategoryResponse> responses = getAllCategoryCase.getInactiveCategories().stream()
                .map(categoryMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        Category updated = updateCategoryCase.updateCategory(id, categoryMapper.toDomain(request));
        return ResponseEntity.ok(categoryMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        deleteCategoryCase.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/restore")
    public ResponseEntity<Void> restoreCategory(@PathVariable Long id) {
        restoreCategoryCase.restoreCategory(id);
        return ResponseEntity.ok().build();
    }

}
