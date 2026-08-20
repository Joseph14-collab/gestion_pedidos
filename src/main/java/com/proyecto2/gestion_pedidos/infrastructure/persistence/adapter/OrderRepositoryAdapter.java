package com.proyecto2.gestion_pedidos.infrastructure.persistence.adapter;

import com.proyecto2.gestion_pedidos.core.entity.Order;
import com.proyecto2.gestion_pedidos.core.entity.enums.OrderStatus;
import com.proyecto2.gestion_pedidos.core.usecase.port.out.order.OrderRepositoryPort;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.entity.OrderEntity;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.jpa.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepositoryPort {
    private final OrderRepository orderRepository;


    @Override
    public Order save(Order order){
        OrderEntity entity = toEntity(order);
        OrderEntity saved = orderRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Order> findById(Long id){
        return orderRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Order> findAll(){
        return orderRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public List<Order> findByStatus(OrderStatus status){
        return orderRepository.findByStatus(status).stream()
                .map(this::toDomain).toList();
    }

    //
    private OrderEntity toEntity(Order order){
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setCustomerId(order.getCustomerId());
        entity.setOrderDate(order.getOrderDate());
        entity.setStatus(order.getStatus());
        entity.setTotalAmount(order.getTotalAmount());
        return entity;
    }
    private Order toDomain(OrderEntity entity){
        return Order.builder()
                .id(entity.getId())
                .customerId(entity.getCustomerId())
                .orderDate(entity.getOrderDate())
                .status(entity.getStatus())
                .totalAmount(entity.getTotalAmount()).build();
    }

}
