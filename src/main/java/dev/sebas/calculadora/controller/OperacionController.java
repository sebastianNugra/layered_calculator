package dev.sebas.calculadora.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sebas.calculadora.dto.request.OperacionRequest;
import dev.sebas.calculadora.dto.response.OperacionResponse;
import dev.sebas.calculadora.service.OperacionService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/operaciones")
public class OperacionController {

    private final OperacionService operacionService;

    public OperacionController(OperacionService operacionService) {
        this.operacionService = operacionService;
    }

    @PostMapping
    public ResponseEntity<OperacionResponse> crearOperacion(@Valid @RequestBody OperacionRequest request) {
        OperacionResponse respuesta = operacionService.crearOperacion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }
}
