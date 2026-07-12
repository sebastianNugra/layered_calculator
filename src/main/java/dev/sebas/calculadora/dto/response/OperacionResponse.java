package dev.sebas.calculadora.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OperacionResponse {
    private Long id;
    private Double resultado;
}
