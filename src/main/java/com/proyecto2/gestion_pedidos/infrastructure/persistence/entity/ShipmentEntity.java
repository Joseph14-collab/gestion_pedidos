package com.proyecto2.gestion_pedidos.infrastructure.persistence.entity;

import com.proyecto2.gestion_pedidos.core.entity.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "shipments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "shipping_address")
    private String shippingAddress;

    private String carrier;

    @Column(name = "estimated_delivery_date")
    private LocalDate estimatedDeliveryDate;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;
}
