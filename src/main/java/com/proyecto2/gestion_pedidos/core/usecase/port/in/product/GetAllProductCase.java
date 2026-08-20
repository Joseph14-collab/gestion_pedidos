package com.proyecto2.gestion_pedidos.core.usecase.port.in.product;

import com.proyecto2.gestion_pedidos.core.entity.Product;

import java.util.List;

public interface GetAllProductCase {
    List<Product> getAllProducts();
    List<Product> getActiveProducts();
    List<Product> getInactiveProducts();
}
