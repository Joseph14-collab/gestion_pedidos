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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private OrderEntity order;

    @Column(name = "tracking_number", unique = true, length = 100)
    private String trackingNumber;

    @Column(name = "shipping_address", nullable = false)
    private String shippingAddress;

    @Column(nullable = false, length = 100)
    private String carrier;

    @Column(name = "estimated_delivery_date")
    private LocalDate estimatedDeliveryDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ShipmentStatus status;
}
