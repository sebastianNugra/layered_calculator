package dev.sebas.calculadora.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.sebas.calculadora.dto.request.OperacionRequest;
import dev.sebas.calculadora.dto.response.HistorialResponse;
import dev.sebas.calculadora.dto.response.OperacionResponse;
import dev.sebas.calculadora.model.Operacion;
import dev.sebas.calculadora.model.TipoOperacion;
import dev.sebas.calculadora.repository.OperacionRepository;
import dev.sebas.calculadora.service.OperacionService;

@Service
public class OperacionServiceImpl implements OperacionService {

    // Inyectar el repositorio de operaciones
    private final OperacionRepository operacionRepository;

    public OperacionServiceImpl(OperacionRepository operacionRepository) {
        this.operacionRepository = operacionRepository;
    }

    // implementacion metodos

    @Override
    public OperacionResponse crearOperacion(OperacionRequest request) {

        Double resultado = calcularResultado(
                request.getValor1(),
                request.getValor2(),
                request.getTipo());

        Operacion operacion = new Operacion();

        operacion.setValor1(request.getValor1());
        operacion.setValor2(request.getValor2());
        operacion.setTipo(request.getTipo());
        operacion.setResultado(resultado);
        operacion.setFecha(LocalDateTime.now());

        Operacion guardada = operacionRepository.save(operacion);

        return new OperacionResponse(
                guardada.getId(),
                guardada.getResultado());

    }

    private Double calcularResultado(Double valor1, Double valor2, TipoOperacion tipo) {
        return switch (tipo) {
            case SUMA -> valor1 + valor2;
            case RESTA -> valor1 - valor2;
            case MULTIPLICACION -> valor1 * valor2;
            case DIVISION -> {
                if (valor2 == 0) {
                    throw new IllegalArgumentException("No se puede dividir por cero");
                }
                yield valor1 / valor2;
            }
        };
    }

    @Override
    public Page<HistorialResponse> obtenerHistorial(int page, int size) {
        return operacionRepository.findAll(PageRequest.of(page, size))
                .map(this::convertirAHistorialResponse);
    }

    @Override
    public HistorialResponse obtenerPorId(Long id) {
        return operacionRepository.findById(id)
                .map(this::convertirAHistorialResponse)
                .orElseThrow(() -> new RuntimeException("Operación no encontrada con id: " + id));
    }

    @Override
    public OperacionResponse actualizarOperacion(Long id, OperacionRequest request) {
        Operacion operacionExistente = operacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operación no encontrada con id: " + id));

        Double resultado = calcularResultado(
                request.getValor1(),
                request.getValor2(),
                request.getTipo());

        operacionExistente.setValor1(request.getValor1());
        operacionExistente.setValor2(request.getValor2());
        operacionExistente.setTipo(request.getTipo());
        operacionExistente.setResultado(resultado);
        operacionExistente.setFecha(LocalDateTime.now());

        Operacion actualizada = operacionRepository.save(operacionExistente);

        return new OperacionResponse(
                actualizada.getId(),
                actualizada.getResultado());
    }

    @Override
    public void eliminarOperacion(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarOperacion'");
    }

    private HistorialResponse convertirAHistorialResponse(Operacion op) {
        return new HistorialResponse(
                op.getId(),
                op.getValor1(),
                op.getValor2(),
                op.getTipo().name(),
                op.getResultado(),
                op.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }
}
