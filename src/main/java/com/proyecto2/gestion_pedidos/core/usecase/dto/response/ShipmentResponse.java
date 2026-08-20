package com.proyecto2.gestion_pedidos.core.usecase.dto.response;


import com.proyecto2.gestion_pedidos.core.entity.enums.ShipmentStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentResponse {

    private Long id;
    private Long orderId;
    private String trackingNumber;
    private String shippingAddress;
    private String carrier;
    private LocalDate estimatedDeliveryDate;
    private ShipmentStatus status;
}
