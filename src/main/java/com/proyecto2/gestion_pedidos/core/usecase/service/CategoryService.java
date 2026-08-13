package com.proyecto2.gestion_pedidos.core.usecase.service;

import com.proyecto2.gestion_pedidos.core.entity.Category;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.category.*;
import com.proyecto2.gestion_pedidos.core.usecase.port.out.category.CategoryRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements RegisterCategoryCase, GetCategoryCase, GetAllCategoryCase, UpdateCategoryCase, DeleteCategoryCase , RestoreCategoryCase{

    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public Category registerCategory(Category request){
        Category newCategory = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .active(true).build();
        return categoryRepositoryPort.save(newCategory);
    }

    @Override
    public Category getCategory (Long id){
        return categoryRepositoryPort.findById(id)
                .orElseThrow(()-> new RuntimeException("Categoria no encontrada"));
    }

    @Override
    public List<Category> getAllCategories(){
        return categoryRepositoryPort.findAll();
    }

    @Override
    public List<Category> getActiveCategories(){
        return categoryRepositoryPort.findByActive(true);
    }

    @Override
    public List<Category> getInactiveCategories(){
        return categoryRepositoryPort.findByActive(false);
    }

    @Override
    public Category updateCategory(Long id, Category category){
        Category existingCategory = getCategory(id);
        Category updatedCategory = Category.builder()
                .id(existingCategory.getId())
                .name(category.getName())
                .description(category.getDescription())
                .active(existingCategory.getActive()).build();
        return categoryRepositoryPort.update(updatedCategory);
    }

    @Override
    public boolean deleteCategory(Long id){
        Category existingCategory = getCategory(id);
        Category deletedCategory = Category.builder()
                .id(existingCategory.getId())
                .name(existingCategory.getName())
                .description(existingCategory.getDescription())
                .active(false).build();
        categoryRepositoryPort.delete(deletedCategory);
        return true;
    }

    @Override
    public boolean restoreCategory(Long id){
        Category existingCategory = getCategory(id);
        Category deletedCategory = Category.builder()
                .id(existingCategory.getId())
                .name(existingCategory.getName())
                .description(existingCategory.getDescription())
                .active(true).build();
        categoryRepositoryPort.delete(deletedCategory);
        return true;
    }
}
