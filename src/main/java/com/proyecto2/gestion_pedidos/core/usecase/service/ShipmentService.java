package com.proyecto2.gestion_pedidos.core.usecase.service;

import com.proyecto2.gestion_pedidos.core.entity.Shipment;
import com.proyecto2.gestion_pedidos.core.entity.enums.ShipmentStatus;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.shipment.*;
import com.proyecto2.gestion_pedidos.core.usecase.port.out.order.OrderRepositoryPort;
import com.proyecto2.gestion_pedidos.core.usecase.port.out.shipment.ShipmentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentService implements RegisterShipmentCase, GetShipmentCase, GetAllShipmentCase, UpdateShipmentCase {
    private final ShipmentRepositoryPort shipmentRepositoryPort;
    private final OrderRepositoryPort orderRepositoryPort;

    @Override
    public Shipment registerShipment(Shipment request){
        orderRepositoryPort.findById(request.getOrderId())
                .orElseThrow(()-> new RuntimeException("Orden no encontrada"));

        Shipment newShipment = Shipment.builder()
                .orderId(request.getOrderId())
                .shippingAddress(request.getShippingAddress())
                .carrier(request.getCarrier())
                .trackingNumber(request.getTrackingNumber())
                .estimateDeliveryDate(LocalDate.now().plusDays(3))
                .status(ShipmentStatus.PREPARING).build();
        return shipmentRepositoryPort.save(newShipment);
    }

    @Override
    public Shipment getShipment(Long id){
        return shipmentRepositoryPort.findById(id)
                .orElseThrow(()-> new RuntimeException("Envio no encontrado"));
    }

    @Override
    public List<Shipment> getAllShipments(){
        return shipmentRepositoryPort.findAll();
    }

    @Override
    public List<Shipment> getShipmentsByStatus(ShipmentStatus status){
        return shipmentRepositoryPort.findByStatus(status);
    }

    @Override
    public Shipment updateShipment(Long id, Shipment shipment){
        Shipment existingShipment = getShipment(id);
        Shipment updatedShipment = Shipment.builder()
                .id(existingShipment.getId())
                .orderId(existingShipment.getOrderId())
                .shippingAddress(shipment.getShippingAddress())
                .carrier(shipment.getCarrier())
                .trackingNumber(existingShipment.getTrackingNumber())
                .estimateDeliveryDate(existingShipment.getEstimateDeliveryDate())
                .status(existingShipment.getStatus()).build();
        return shipmentRepositoryPort.save(updatedShipment);
    }

    @Override
    public Shipment updateShipmentStatus(Long id, ShipmentStatus status){
        Shipment existing = getShipment(id);
        Shipment updated = Shipment.builder()
                .id(existing.getId())
                .orderId(existing.getOrderId())
                .shippingAddress(existing.getShippingAddress())
                .carrier(existing.getCarrier())
                .trackingNumber(existing.getTrackingNumber())
                .estimateDeliveryDate(existing.getEstimateDeliveryDate())
                .status(status)
                .build();
        return shipmentRepositoryPort.save(updated);
    }

    @Override
    public Shipment updateTrackingNumber(Long id, String trackingNumber){
        Shipment existing = getShipment(id);

        Shipment updated = Shipment.builder()
                .id(existing.getId())
                .orderId(existing.getOrderId())
                .shippingAddress(existing.getShippingAddress())
                .carrier(existing.getCarrier())
                .trackingNumber(trackingNumber)
                .estimateDeliveryDate(existing.getEstimateDeliveryDate())
                .status(existing.getStatus())
                .build();
        return shipmentRepositoryPort.save(updated);
    }


}
