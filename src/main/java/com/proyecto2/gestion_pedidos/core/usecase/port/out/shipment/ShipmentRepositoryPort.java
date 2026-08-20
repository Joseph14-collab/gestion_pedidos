package com.proyecto2.gestion_pedidos.core.usecase.port.out.shipment;

import com.proyecto2.gestion_pedidos.core.entity.Shipment;
import com.proyecto2.gestion_pedidos.core.entity.enums.ShipmentStatus;

import java.util.List;
import java.util.Optional;

public interface ShipmentRepositoryPort {
    Shipment save(Shipment shipment);
    Optional<Shipment> findById(Long id);
    List<Shipment> findAll();
    List<Shipment> findByStatus(ShipmentStatus status);
}
