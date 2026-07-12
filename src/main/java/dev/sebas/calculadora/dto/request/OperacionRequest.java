package dev.sebas.calculadora.dto.request;

import dev.sebas.calculadora.model.TipoOperacion;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OperacionRequest {
    
    @NotNull
    private Double valor1;

    @NotNull
    private Double valor2;

    @NotNull
    private TipoOperacion tipo;
}
