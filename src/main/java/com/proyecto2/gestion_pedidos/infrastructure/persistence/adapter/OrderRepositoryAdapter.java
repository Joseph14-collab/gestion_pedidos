package com.proyecto2.gestion_pedidos.infrastructure.persistence.adapter;

import com.proyecto2.gestion_pedidos.core.entity.Order;
import com.proyecto2.gestion_pedidos.core.entity.OrderItem;
import com.proyecto2.gestion_pedidos.core.entity.enums.OrderStatus;
import com.proyecto2.gestion_pedidos.core.usecase.port.out.order.OrderRepositoryPort;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.entity.OrderEntity;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.entity.OrderItemEntity;
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
        List<OrderItemEntity> itemEntities = order.getItems().stream()
                .map(item -> toItemEntity(item, entity))
                .toList();
        entity.setItems(itemEntities);
        return entity;
    }

    private Order toDomain(OrderEntity entity){
        return Order.builder()
                .id(entity.getId())
                .customerId(entity.getCustomerId())
                .orderDate(entity.getOrderDate())
                .status(entity.getStatus())
                .totalAmount(entity.getTotalAmount())
                .items(entity.getItems().stream()
                        .map(this::toItemDomain)
                        .toList())
                .build();
    }

    private OrderItemEntity toItemEntity(OrderItem item, OrderEntity orderEntity) {
        OrderItemEntity entity = new OrderItemEntity();
        entity.setId(item.getId());
        entity.setProductId(item.getProductId());
        entity.setQuantity(item.getQuantity());
        entity.setUnitPrice(item.getUnitPrice());
        entity.setSubTotal(item.getSubTotal());
        entity.setOrder(orderEntity);

        return entity;
    }

    private OrderItem toItemDomain(OrderItemEntity entity) {
        return OrderItem.builder()
                .id(entity.getId())
                .orderId(entity.getOrder().getId())
                .productId(entity.getProductId())
                .quantity(entity.getQuantity())
                .unitPrice(entity.getUnitPrice())
                .subTotal(entity.getSubTotal())
                .build();
    }
}
