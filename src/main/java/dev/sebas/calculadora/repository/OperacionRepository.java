package dev.sebas.calculadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.sebas.calculadora.model.Operacion;

public interface OperacionRepository extends JpaRepository<Operacion, Long> {
    
}
