package com.proyecto2.gestion_pedidos.infrastructure.persistence.adapter;

import com.proyecto2.gestion_pedidos.core.entity.Payment;
import com.proyecto2.gestion_pedidos.core.entity.enums.PaymentStatus;
import com.proyecto2.gestion_pedidos.core.usecase.port.out.payment.PaymentRepositoryPort;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.entity.PaymentEntity;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.jpa.OrderRepository;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.jpa.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Override
    public Payment save(Payment payment){
        PaymentEntity entity = toEntity(payment);
        PaymentEntity saved = paymentRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Payment> findById(Long id){
        return paymentRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Payment> findByOrderId(Long orderId){
        return paymentRepository.findByOrderId(orderId).map(this::toDomain);
    }

    @Override
    public List<Payment> findAll(){
        return paymentRepository.findAll().stream()
                .map(this::toDomain).toList();
    }

    @Override
    public List<Payment> findByStatus(PaymentStatus status){
        return paymentRepository.findByStatus(status).stream()
                .map(this::toDomain).toList();
    }

    //
    private PaymentEntity toEntity(Payment payment){
        PaymentEntity entity = new PaymentEntity();
        entity.setId(payment.getId());
        entity.setOrderId(payment.getOrderId());
        entity.setPaymentGateway(payment.getPaymentGateway());
        entity.setGatewayTransaction(payment.getGatewayTransaction());
        entity.setAmount(payment.getAmount());
        entity.setPaymentDate(payment.getPaymentDate());
        entity.setStatus(payment.getStatus());
        return entity;
    }
    private Payment toDomain(PaymentEntity entity){
        return Payment.builder()
                .id(entity.getId())
                .orderId(entity.getOrderId())
                .paymentGateway(entity.getPaymentGateway())
                .gatewayTransaction(entity.getGatewayTransaction())
                .amount(entity.getAmount())
                .paymentDate(entity.getPaymentDate())
                .status(entity.getStatus()).build();
    }

}
