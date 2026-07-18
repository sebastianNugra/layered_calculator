package dev.sebas.calculadora.exception;

import lombok.Getter;

@Getter
public class OperacionNotFoundException extends RuntimeException {
    private final Long id;

    public OperacionNotFoundException(Long id) {
        super("Operacion no encontrada: " + id);
        this.id = id;
    }

}
