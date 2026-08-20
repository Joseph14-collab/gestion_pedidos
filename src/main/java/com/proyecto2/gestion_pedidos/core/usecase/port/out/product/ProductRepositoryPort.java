package com.proyecto2.gestion_pedidos.core.usecase.port.out.product;

import com.proyecto2.gestion_pedidos.core.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    List<Product> findByActive(boolean active);
    Product update(Product product);
    Product delete(Product product);
}
