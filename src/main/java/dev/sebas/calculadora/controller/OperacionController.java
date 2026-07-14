package dev.sebas.calculadora.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.sebas.calculadora.dto.request.OperacionRequest;
import dev.sebas.calculadora.dto.response.HistorialResponse;
import dev.sebas.calculadora.dto.response.OperacionResponse;
import dev.sebas.calculadora.service.OperacionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

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

    @GetMapping("/historial")
    public ResponseEntity<Page<HistorialResponse>> historial(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(operacionService.obtenerHistorial(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialResponse> operacion(@PathVariable Long id) {
        HistorialResponse respuesta = operacionService.obtenerPorId(id);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperacionResponse> actualizarOperacion(@PathVariable Long id,
            @Valid @RequestBody OperacionRequest request) {
        OperacionResponse respuesta = operacionService.actualizarOperacion(id, request);
        return ResponseEntity.ok(respuesta);
    }

}
