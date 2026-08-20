package com.proyecto2.gestion_pedidos.infrastructure.controller;

import com.proyecto2.gestion_pedidos.core.entity.Order;
import com.proyecto2.gestion_pedidos.core.entity.enums.OrderStatus;
import com.proyecto2.gestion_pedidos.core.usecase.dto.mapper.OrderMapper;
import com.proyecto2.gestion_pedidos.core.usecase.dto.request.OrderItemRequest;
import com.proyecto2.gestion_pedidos.core.usecase.dto.request.OrderRequest;
import com.proyecto2.gestion_pedidos.core.usecase.dto.response.OrderResponse;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.order.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final RegisterOrderCase registerOrderCase;
    private final GetOrderCase getOrderCase;
    private final GetAllOrderCase getAllOrderCase;
    private final UpdateOrderStatusCase updateOrderStatusCase;
    private final CancelOrderCase cancelOrderCase;
    private final OrderMapper orderMapper;

    private final AddOrderItemCase addOrderItemCase;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request){
        Order created = registerOrderCase.registerOrder(orderMapper.toDomain(request));
        return new ResponseEntity<>(orderMapper.toResponse(created), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id){
        Order order = getOrderCase.getOrder(id);
        return ResponseEntity.ok(orderMapper.toResponse(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        List<OrderResponse> responses = getAllOrderCase.getAllOrders().stream()
                .map(orderMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderResponse>> getOrdersByStatus(@PathVariable OrderStatus status){
        List<OrderResponse> responses = getAllOrderCase.getOrdersByStatus(status).stream()
                .map(orderMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponse> updateStatus(@PathVariable Long id, @RequestParam OrderStatus status){
        Order updateOrder = updateOrderStatusCase.updateOrderStatus(id, status);
        return ResponseEntity.ok(orderMapper.toResponse(updateOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> cancelOder(@PathVariable Long id){
        cancelOrderCase.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }

    //OrderItem
    @PostMapping("/{orderId}/items")
    public ResponseEntity<OrderResponse> addItem(@PathVariable Long orderId, @Valid @RequestBody OrderItemRequest request) {
        Order updatedOrder = addOrderItemCase.addOrderItem(orderId, request.getProductId(), request.getQuantity());
        return ResponseEntity.ok(orderMapper.toResponse(updatedOrder));
    }


}

