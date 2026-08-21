package com.proyecto2.gestion_pedidos.infrastructure.persistence.jpa;

import com.proyecto2.gestion_pedidos.core.entity.enums.PaymentStatus;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    Optional<PaymentEntity> findByOrderId(Long orderId);
    List<PaymentEntity> findByStatus(PaymentStatus status);
}
