package dev.sebas.calculadora.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HistorialResponse {
    private Long id;
    private Double valor1;
    private Double valor2;
    private String tipo;
    private Double resultado;
    private String fecha;

}
