package com.proyecto2.gestion_pedidos.infrastructure.persistence.adapter;

import com.proyecto2.gestion_pedidos.core.entity.Shipment;
import com.proyecto2.gestion_pedidos.core.entity.enums.ShipmentStatus;
import com.proyecto2.gestion_pedidos.core.usecase.port.out.shipment.ShipmentRepositoryPort;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.entity.OrderEntity;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.entity.ShipmentEntity;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.jpa.OrderRepository;
import com.proyecto2.gestion_pedidos.infrastructure.persistence.jpa.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ShipmentAdapter implements ShipmentRepositoryPort {
    private final ShipmentRepository shipmentRepository;
    private final OrderRepository orderRepository;

    @Override
    public Shipment save(Shipment shipment){
        ShipmentEntity entity = toEntity(shipment);
        ShipmentEntity saved = shipmentRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Shipment> findById(Long id){
        return shipmentRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Shipment> findAll(){
        return shipmentRepository.findAll().stream()
                .map(this::toDomain).toList();
    }

    @Override
    public List<Shipment> findByStatus(ShipmentStatus status){
        return shipmentRepository.findByStatus(status).stream()
                .map(this::toDomain).toList();
    }

    private ShipmentEntity toEntity(Shipment shipment){
        ShipmentEntity entity = new ShipmentEntity();
        entity.setId(shipment.getId());

        OrderEntity order = orderRepository.findById(shipment.getOrderId())
                        .orElseThrow(()-> new RuntimeException("Orden no encontrada"));
        entity.setOrder(order);
        entity.setTrackingNumber(shipment.getTrackingNumber());
        entity.setShippingAddress(shipment.getShippingAddress());
        entity.setCarrier(shipment.getCarrier());
        entity.setEstimatedDeliveryDate(shipment.getEstimateDeliveryDate());
        entity.setStatus(shipment.getStatus());
        return entity;
    }
    private Shipment toDomain(ShipmentEntity entity){
        return Shipment.builder()
                .id(entity.getId())
                .orderId(entity.getOrder().getId())
                .trackingNumber(entity.getTrackingNumber())
                .shippingAddress(entity.getShippingAddress())
                .carrier(entity.getCarrier())
                .estimateDeliveryDate(entity.getEstimatedDeliveryDate())
                .status(entity.getStatus()).build();
    }

}
