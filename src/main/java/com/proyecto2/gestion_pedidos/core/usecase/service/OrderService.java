package com.proyecto2.gestion_pedidos.core.usecase.service;

import com.proyecto2.gestion_pedidos.core.entity.Order;
import com.proyecto2.gestion_pedidos.core.entity.OrderItem;
import com.proyecto2.gestion_pedidos.core.entity.Product;
import com.proyecto2.gestion_pedidos.core.entity.enums.OrderStatus;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.order.*;
import com.proyecto2.gestion_pedidos.core.usecase.port.out.order.OrderRepositoryPort;
import com.proyecto2.gestion_pedidos.core.usecase.port.out.product.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements RegisterOrderCase, GetOrderCase, GetAllOrderCase, UpdateOrderStatusCase, CancelOrderCase, AddOrderItemCase {
    private final OrderRepositoryPort orderRepositoryPort;
    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public Order registerOrder(Order request){
        Order newOrder= Order.builder()
                .customerId(request.getCustomerId())
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .totalAmount(BigDecimal.ZERO)
                .items(new ArrayList<>()).build();
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
                .totalAmount(existingOrder.getTotalAmount())
                .items(existingOrder.getItems())
                .build();
        return orderRepositoryPort.save(updateOrder);
    }

    @Override
    public Order cancelOrder(Long id){
        return updateOrderStatus(id, OrderStatus.CANCELLED);
    }

    @Override
    public Order addOrderItem(Long orderId, Long productId, Integer quantity) {
        Order order = orderRepositoryPort.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        Product product = productRepositoryPort.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        BigDecimal subTotal = product.getPrice().multiply(new BigDecimal(quantity));
        OrderItem newItem = OrderItem.builder()
                .orderId(order.getId())
                .productId(product.getId())
                .quantity(quantity)
                .unitPrice(product.getPrice())
                .subTotal(subTotal)
                .build();

        List<OrderItem> items = new ArrayList<>(order.getItems());
        items.add(newItem);

        Order updatedOrder = Order.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .items(items)
                .totalAmount(order.getTotalAmount().add(subTotal))
                .build();
        return orderRepositoryPort.save(updatedOrder);
    }
}
