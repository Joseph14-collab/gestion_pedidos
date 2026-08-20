package com.proyecto2.gestion_pedidos.core.usecase.dto.mapper;

import com.proyecto2.gestion_pedidos.core.entity.OrderItem;
import com.proyecto2.gestion_pedidos.core.usecase.dto.request.OrderItemRequest;
import com.proyecto2.gestion_pedidos.core.usecase.dto.response.OrderItemResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    private OrderItem toDomain(OrderItemRequest request){
        return OrderItem.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity()).build();
    }

    public OrderItemResponse toResponse(OrderItem item) {
        return OrderItemResponse.builder()
                .id(item.getId())
                .orderId(item.getOrderId())
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .subTotal(item.getSubTotal())
                .build();
    }

}

