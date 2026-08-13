package com.proyecto2.gestion_pedidos.core.usecase.port.in.category;

import com.proyecto2.gestion_pedidos.core.entity.Category;

import java.util.List;

public interface GetAllCategoryCase {
    List<Category> getAllCategories();
    List<Category> getActiveCategories();
    List<Category> getInactiveCategories();
}
