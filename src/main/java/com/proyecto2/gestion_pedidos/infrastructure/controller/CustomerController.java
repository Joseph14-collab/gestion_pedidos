package com.proyecto2.gestion_pedidos.infrastructure.controller;

import com.proyecto2.gestion_pedidos.core.entity.Customer;
import com.proyecto2.gestion_pedidos.core.usecase.dto.mapper.CustomerMapper;
import com.proyecto2.gestion_pedidos.core.usecase.dto.request.CustomerRequest;
import com.proyecto2.gestion_pedidos.core.usecase.dto.response.CustomerResponse;
import com.proyecto2.gestion_pedidos.core.usecase.port.in.customer.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final RegisterCustomerCase registerCustomerCase;
    private final GetCustomerCase getCustomerCase;
    private final GetAllCustomerCase getAllCustomerCase;
    private final UpdateCustomerCase updateCustomerCase;
    private final DeleteCustomerCase deleteCustomerCase;
    private final RestoreCustomerCase restoreCustomerCase;
    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request){
        Customer created = registerCustomerCase.registerCustomer(customerMapper.toDomain(request));
        return new ResponseEntity<>(customerMapper.toResponse(created), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long id){
        Customer customer = getCustomerCase.getCustomer(id);
        return ResponseEntity.ok(customerMapper.toResponse(customer));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getActiveCustomers(){
        List<CustomerResponse> responses = getAllCustomerCase.getActiveCustomers().stream()
                .map(customerMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(){
        List<CustomerResponse> responses = getAllCustomerCase.getAllCustomers().stream()
                .map(customerMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<CustomerResponse>> getInactiveCustomers(){
        List<CustomerResponse> responses = getAllCustomerCase.getInactiveCustomers().stream()
                .map(customerMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerRequest request){
        Customer customer = updateCustomerCase.updateCustomer(id, customerMapper.toDomain(request));
        return ResponseEntity.ok(customerMapper.toResponse(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        deleteCustomerCase.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/restore")
    public ResponseEntity<Void> restoreCustomer(@PathVariable Long id){
        restoreCustomerCase.restoreCustomer(id);
        return ResponseEntity.ok().build();
    }

}
