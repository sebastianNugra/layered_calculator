package dev.sebas.calculadora.service;

import org.springframework.data.domain.Page;

import dev.sebas.calculadora.dto.request.OperacionRequest;
import dev.sebas.calculadora.dto.response.HistorialResponse;
import dev.sebas.calculadora.dto.response.OperacionResponse;

public interface OperacionService {

    OperacionResponse crearOperacion(OperacionRequest request);

    Page<HistorialResponse> obtenerHistorial(int page, int size);

    HistorialResponse obtenerPorId(Long id);

    OperacionResponse actualizarOperacion(Long id, OperacionRequest request);

    void eliminarOperacion(Long id);
}
