package com.proyecto2.gestion_pedidos.infrastructure.controller;

import com.proyecto2.gestion_pedidos.core.entity.Shipment;
import com.proyecto2.gestion_pedidos.core.entity.enums.ShipmentStatus;
import com.proyecto2.gestion_pedidos.core.usecase.dto.mapper.ShipmentMapper;
import com.proyecto2.gestion_pedidos.core.usecase.dto.request.ShipmentRequest;
import com.proyecto2.gestion_pedidos.core.usecase.dto.response.ShipmentResponse;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.shipment.GetAllShipmentCase;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.shipment.GetShipmentCase;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.shipment.RegisterShipmentCase;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.shipment.UpdateShipmentCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
@RequiredArgsConstructor
public class ShipmentController {
    private final RegisterShipmentCase registerShipmentCase;
    private final GetShipmentCase getShipmentCase;
    private final GetAllShipmentCase getAllShipmentCase;
    private final UpdateShipmentCase updateShipmentCase;
    private final ShipmentMapper shipmentMapper;

    @PostMapping
    public ResponseEntity<ShipmentResponse> createShipment(@Valid @RequestBody ShipmentRequest request){
        Shipment created = registerShipmentCase.registerShipment(shipmentMapper.toDomain(request));
        return new ResponseEntity<>(shipmentMapper.toResponse(created), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShipmentResponse> getShipment(@PathVariable Long id){
        Shipment shipment = getShipmentCase.getShipment(id);
        return ResponseEntity.ok(shipmentMapper.toResponse(shipment));
    }

    @GetMapping
    public ResponseEntity<List<ShipmentResponse>> getAllShipments(){
        List<ShipmentResponse> responses = getAllShipmentCase.getAllShipments().stream()
                .map(shipmentMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ShipmentResponse>> getShipmentsByStatus(@PathVariable ShipmentStatus status){
        List<ShipmentResponse> responses = getAllShipmentCase.getShipmentsByStatus(status).stream()
                .map(shipmentMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShipmentResponse> updateShipment(@PathVariable Long id, @Valid @RequestBody ShipmentRequest request){
        Shipment updated = updateShipmentCase.updateShipment(id, shipmentMapper.toDomain(request));
        return ResponseEntity.ok(shipmentMapper.toResponse(updated));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ShipmentResponse> updateStatus(@PathVariable Long id, @RequestParam ShipmentStatus status){
        Shipment updated = updateShipmentCase.updateShipmentStatus(id, status);
        return ResponseEntity.ok(shipmentMapper.toResponse(updated));
    }

    @PatchMapping("/{id}/tracking")
    public ResponseEntity<ShipmentResponse> updateTracking(@PathVariable Long id, @RequestParam String trackingNumber){
        Shipment updated = updateShipmentCase.updateTrackingNumber(id, trackingNumber);
        return ResponseEntity.ok(shipmentMapper.toResponse(updated));
    }

}
