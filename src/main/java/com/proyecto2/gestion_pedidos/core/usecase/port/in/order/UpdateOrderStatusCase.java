package com.proyecto2.gestion_pedidos.core.usecase.port.in.order;

import com.proyecto2.gestion_pedidos.core.entity.Order;
import com.proyecto2.gestion_pedidos.core.entity.enums.OrderStatus;

public interface UpdateOrderStatusCase {
    Order updateOrderStatus(Long id, OrderStatus newStatus);
}
