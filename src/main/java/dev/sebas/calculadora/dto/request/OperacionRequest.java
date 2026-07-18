package dev.sebas.calculadora.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import dev.sebas.calculadora.model.TipoOperacion;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OperacionRequest {

    @NotNull
    @JsonDeserialize(using = StrictDoubleDeserializer.class)
    private Double valor1;

    @NotNull
    @JsonDeserialize(using = StrictDoubleDeserializer.class)
    private Double valor2;

    @NotNull
    private TipoOperacion tipo;
}
