package com.proyecto2.gestion_pedidos.core.usecase.service;

import com.proyecto2.gestion_pedidos.core.entity.Order;
import com.proyecto2.gestion_pedidos.core.entity.Payment;
import com.proyecto2.gestion_pedidos.core.entity.enums.PaymentStatus;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.payment.GetAllPaymentCase;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.payment.GetPaymentCase;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.payment.ProcessPaymentCase;
import com.proyecto2.gestion_pedidos.core.usecase.port.out.order.OrderRepositoryPort;
import com.proyecto2.gestion_pedidos.core.usecase.port.out.payment.PaymentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService implements ProcessPaymentCase, GetPaymentCase, GetAllPaymentCase{
    private final PaymentRepositoryPort paymentRepositoryPort;
    private final OrderRepositoryPort orderRepositoryPort;

    @Override
    public Payment createPendingPayment(Long orderId, String paymentGateway){
        Order order = orderRepositoryPort.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada."));

        if (paymentRepositoryPort.findByOrderId(orderId).isPresent()){
            throw new RuntimeException("Esta orden ya tiene un pago en proceso.");
        }
        Payment pendingPayment = Payment.builder()
                .orderId(orderId)
                .paymentGateway(paymentGateway)
                .amount(order.getTotalAmount())
                .gatewayTransaction("PENDIENTE")
                .paymentDate(LocalDateTime.now())
                .status(PaymentStatus.PENDING)
                .build();
        return paymentRepositoryPort.save(pendingPayment);
    }

    @Override
    public Payment processPaymentResult(Long paymentId, boolean isSuccess){
        Payment payment = getPayment(paymentId);
        PaymentStatus statusResult;
        String operationCode;
        if (isSuccess){
            statusResult = PaymentStatus.SUCCESS;
            int randomCode = (int)(Math.random() * 900000) + 100000;
            operationCode = "OPE-" + randomCode;
        } else{
            statusResult = PaymentStatus.FAILED;
            operationCode = "TARJETA_RECHAZADA";
        }
        Payment updatedPayment = Payment.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .paymentGateway(payment.getPaymentGateway())
                .amount(payment.getAmount())
                .gatewayTransaction(operationCode)
                .paymentDate(LocalDateTime.now())
                .status(statusResult)
                .build();
        return paymentRepositoryPort.save(updatedPayment);
    }

    @Override
    public Payment getPayment(Long id){
        return paymentRepositoryPort.findById(id)
                .orElseThrow(()-> new RuntimeException("Pago no encontrado"));
    }

    @Override
    public Payment getPaymentByOrderId(Long orderId){
        return paymentRepositoryPort.findByOrderId(orderId)
                .orElseThrow(()-> new RuntimeException("No se encontro un pago asociado a esta orden"));
    }

    @Override
    public List<Payment> getAllPayments(){
        return paymentRepositoryPort.findAll();
    }

    @Override
    public List<Payment> getPaymentsByStatus(PaymentStatus status){
        return paymentRepositoryPort.findByStatus(status);
    }
}
