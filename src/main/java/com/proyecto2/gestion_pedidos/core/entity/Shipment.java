package com.proyecto2.gestion_pedidos.core.entity;

import com.proyecto2.gestion_pedidos.core.entity.enums.ShipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class Shipment {
    private Long id;
    private Long orderId;
    private String trackingNumber;
    private String shippingAddress;
    private String carrier;
    private LocalDate estimateDeliveryDate;
    private ShipmentStatus status;
}
