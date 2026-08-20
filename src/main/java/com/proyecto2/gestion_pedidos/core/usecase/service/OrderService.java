package com.proyecto2.gestion_pedidos.core.usecase.service;

import com.proyecto2.gestion_pedidos.core.entity.Order;
import com.proyecto2.gestion_pedidos.core.entity.enums.OrderStatus;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.order.*;
import com.proyecto2.gestion_pedidos.core.usecase.port.out.order.OrderRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements RegisterOrderCase, GetOrderCase, GetAllOrderCase, UpdateOrderStatusCase, CancelOrderCase {
    private final OrderRepositoryPort orderRepositoryPort;

    @Override
    public Order registerOrder(Order request){
        Order newOrder= Order.builder()
                .customerId(request.getCustomerId())
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .totalAmount(BigDecimal.ZERO).build();
        return orderRepositoryPort.save(newOrder);
    }

    @Override
    public Order getOrder(Long id){
        return orderRepositoryPort.findById(id)
                .orElseThrow(()-> new RuntimeException("Orden no encontrada"));
    }

    @Override
    public List<Order> getAllOrders(){
        return orderRepositoryPort.findAll();
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus status){
        return orderRepositoryPort.findByStatus(status);
    }

    @Override
    public Order updateOrderStatus(Long id, OrderStatus newStatus){
        Order existingOrder = getOrder(id);
        Order updateOrder = Order.builder()
                .id(existingOrder.getId())
                .customerId(existingOrder.getCustomerId())
                .orderDate(existingOrder.getOrderDate())
                .status(newStatus)
                .totalAmount(existingOrder.getTotalAmount()).build();
        return orderRepositoryPort.save(updateOrder);
    }

    @Override
    public Order cancelOrder(Long id){
        return updateOrderStatus(id, OrderStatus.CANCELLED);
    }


}
