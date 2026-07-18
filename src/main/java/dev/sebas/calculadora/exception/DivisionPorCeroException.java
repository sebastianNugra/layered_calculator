package dev.sebas.calculadora.exception;

public class DivisionPorCeroException extends RuntimeException {

    public DivisionPorCeroException() {
        super("No se puede dividir por cero.");
    }
}
