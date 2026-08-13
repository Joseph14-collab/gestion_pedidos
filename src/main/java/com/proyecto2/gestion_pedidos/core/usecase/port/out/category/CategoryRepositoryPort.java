package com.proyecto2.gestion_pedidos.core.usecase.port.out.category;

import com.proyecto2.gestion_pedidos.core.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryPort {
    Category save(Category category);
    Optional<Category> findById(Long id);
    List<Category> findAll();
    List<Category> findByActive(boolean active);
    Category update(Category category);
    Category delete(Category category);
}
