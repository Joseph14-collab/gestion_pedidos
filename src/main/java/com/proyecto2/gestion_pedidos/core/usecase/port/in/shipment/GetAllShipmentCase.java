package com.proyecto2.gestion_pedidos.core.usecase.port.in.shipment;

import com.proyecto2.gestion_pedidos.core.entity.Shipment;
import com.proyecto2.gestion_pedidos.core.entity.enums.ShipmentStatus;

import java.util.List;

public interface GetAllShipmentCase {
    List<Shipment> getAllShipments();
    List<Shipment> getShipmentsByStatus(ShipmentStatus status);
}
