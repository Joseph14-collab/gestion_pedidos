package com.proyecto2.gestion_pedidos.core.usecase.port.out.order;

import com.proyecto2.gestion_pedidos.core.entity.Order;
import com.proyecto2.gestion_pedidos.core.entity.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {
    Order save(Order order);
    Optional<Order> findById(Long id);
    List<Order> findAll();
    List<Order> findByStatus(OrderStatus status);
}
