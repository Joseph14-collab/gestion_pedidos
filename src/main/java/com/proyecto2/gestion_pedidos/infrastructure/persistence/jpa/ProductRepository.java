package com.proyecto2.gestion_pedidos.infrastructure.persistence.jpa;

import com.proyecto2.gestion_pedidos.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByActive(boolean active);
}
