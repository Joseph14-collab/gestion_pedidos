package com.proyecto2.gestion_pedidos.core.usecase.dto.mapper;

import com.proyecto2.gestion_pedidos.core.entity.Shipment;
import com.proyecto2.gestion_pedidos.core.usecase.dto.request.ShipmentRequest;
import com.proyecto2.gestion_pedidos.core.usecase.dto.response.ShipmentResponse;
import org.springframework.stereotype.Component;

@Component
public class ShipmentMapper {
    public Shipment toDomain(ShipmentRequest request){
        return Shipment.builder()
                .orderId(request.getOrderId())
                .shippingAddress(request.getShippingAddress())
                .carrier(request.getCarrier()).build();
    }
    public ShipmentResponse toResponse(Shipment shipment){
        return ShipmentResponse.builder()
                .id(shipment.getId())
                .orderId(shipment.getOrderId())
                .trackingNumber(shipment.getTrackingNumber())
                .shippingAddress(shipment.getShippingAddress())
                .carrier(shipment.getCarrier())
                .estimatedDeliveryDate(shipment.getEstimateDeliveryDate())
                .status(shipment.getStatus()).build();
    }
}