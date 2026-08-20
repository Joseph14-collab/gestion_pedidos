package com.proyecto2.gestion_pedidos.core.usecase.port.in.order;

import com.proyecto2.gestion_pedidos.core.entity.Order;
import com.proyecto2.gestion_pedidos.core.entity.enums.OrderStatus;

import java.util.List;

public interface GetAllOrderCase {
    List<Order> getAllOrders();
    List<Order> getOrdersByStatus(OrderStatus status);
}
