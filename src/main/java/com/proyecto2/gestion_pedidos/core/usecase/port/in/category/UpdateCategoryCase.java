package com.proyecto2.gestion_pedidos.core.usecase.port.in.category;

import com.proyecto2.gestion_pedidos.core.entity.Category;

public interface UpdateCategoryCase {
    Category updateCategory(Long id, Category category);
}
