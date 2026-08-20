package com.proyecto2.gestion_pedidos.core.usecase.port.in.shipment;

import com.proyecto2.gestion_pedidos.core.entity.Shipment;
import com.proyecto2.gestion_pedidos.core.entity.enums.ShipmentStatus;

public interface UpdateShipmentCase {
    Shipment updateShipment(Long id, Shipment shipment);
    Shipment updateShipmentStatus(Long id, ShipmentStatus status);
    Shipment updateTrackingNumber(Long id, String trackingNumber);
}
