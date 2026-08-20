package com.proyecto2.gestion_pedidos.infrastructure.persistence.jpa;

import com.proyecto2.gestion_pedidos.core.entity.enums.ShipmentStatus;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<ShipmentEntity, Long> {
    List<ShipmentEntity> findByStatus(ShipmentStatus status);
}
