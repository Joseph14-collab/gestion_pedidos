package com.proyecto2.gestion_pedidos.core.usecase.dto.mapper;

import com.proyecto2.gestion_pedidos.core.entity.Payment;
import com.proyecto2.gestion_pedidos.core.usecase.dto.request.PaymentRequest;
import com.proyecto2.gestion_pedidos.core.usecase.dto.response.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public Payment toDomain(PaymentRequest request){
        return Payment.builder()
                .orderId(request.getOrderId())
                .paymentGateway(request.getPaymentGateway())
                .amount(request.getAmount()).build();
    }
    public PaymentResponse toResponse(Payment payment){
        return PaymentResponse.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .paymentGateway(payment.getPaymentGateway())
                .gatewayTransaction(payment.getGatewayTransaction())
                .amount(payment.getAmount())
                .paymentDate(payment.getPaymentDate())
                .status(payment.getStatus()).build();
    }

}
