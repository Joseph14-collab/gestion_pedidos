package com.proyecto2.gestion_pedidos.core.usecase.port.in.order;

import com.proyecto2.gestion_pedidos.core.entity.Order;

public interface CancelOrderCase {
    Order cancelOrder(Long id);
}
