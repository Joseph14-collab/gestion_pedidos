package com.proyecto2.gestion_pedidos.core.usecase.dto.mapper;

import com.proyecto2.gestion_pedidos.core.entity.Order;
import com.proyecto2.gestion_pedidos.core.usecase.dto.request.OrderRequest;
import com.proyecto2.gestion_pedidos.core.usecase.dto.response.OrderResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toDomain(OrderRequest request){
        return Order.builder()
                .customerId(request.getCustomerId()).build();
    }
    public OrderResponse toResponse(Order order){
        return OrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount()).build();
    }
}
