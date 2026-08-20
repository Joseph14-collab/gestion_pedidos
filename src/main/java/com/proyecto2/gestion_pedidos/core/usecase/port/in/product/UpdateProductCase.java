package com.proyecto2.gestion_pedidos.core.usecase.port.in.product;

import com.proyecto2.gestion_pedidos.core.entity.Product;

public interface UpdateProductCase {
    Product updateProduct(Long id, Product product);
}
