package com.proyecto2.gestion_pedidos.infrastructure.persistence.jpa;

import com.proyecto2.gestion_pedidos.infrastructure.persistence.entity.CategoryEntity;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    List<CustomerEntity> findByActive(boolean active);
}
