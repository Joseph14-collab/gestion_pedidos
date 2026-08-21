package com.proyecto2.gestion_pedidos.infrastructure.controller;

import com.proyecto2.gestion_pedidos.core.entity.Payment;
import com.proyecto2.gestion_pedidos.core.entity.enums.PaymentStatus;
import com.proyecto2.gestion_pedidos.core.usecase.dto.mapper.PaymentMapper;
import com.proyecto2.gestion_pedidos.core.usecase.dto.request.PaymentRequest;
import com.proyecto2.gestion_pedidos.core.usecase.dto.response.PaymentResponse;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.payment.GetAllPaymentCase;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.payment.GetPaymentCase;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.payment.ProcessPaymentCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final ProcessPaymentCase processPaymentCase;
    private final GetPaymentCase getPaymentCase;
    private final GetAllPaymentCase getAllPaymentCase;
    private final PaymentMapper paymentMapper;

    @PostMapping
    public ResponseEntity<PaymentResponse> createPendingPayment(@Valid @RequestBody PaymentRequest request){
        Payment created = processPaymentCase.createPendingPayment(request.getOrderId(), request.getPaymentGateway());
        return new ResponseEntity<>(paymentMapper.toResponse(created), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/result")
    public ResponseEntity<PaymentResponse> processResult(@PathVariable Long id, @RequestParam boolean isSuccess){
        Payment updated = processPaymentCase.processPaymentResult(id, isSuccess);
        return ResponseEntity.ok(paymentMapper.toResponse(updated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long id) {
        Payment payment = getPaymentCase.getPayment(id);
        return ResponseEntity.ok(paymentMapper.toResponse(payment));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentByOrderId(@PathVariable Long orderId) {
        Payment payment = getPaymentCase.getPaymentByOrderId(orderId);
        return ResponseEntity.ok(paymentMapper.toResponse(payment));
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {
        List<PaymentResponse> responses = getAllPaymentCase.getAllPayments().stream()
                .map(paymentMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PaymentResponse>> getPaymentsByStatus(@PathVariable PaymentStatus status) {
        List<PaymentResponse> responses = getAllPaymentCase.getPaymentsByStatus(status).stream()
                .map(paymentMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

}
