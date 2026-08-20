package com.proyecto2.gestion_pedidos.core.usecase.port.in.shipment;

import com.proyecto2.gestion_pedidos.core.entity.Shipment;

public interface GetShipmentCase {
    Shipment getShipment(Long id);
}
