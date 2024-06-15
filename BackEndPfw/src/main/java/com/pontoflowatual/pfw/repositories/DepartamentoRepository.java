package com.pontoflowatual.pfw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pontoflowatual.pfw.models.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    
}
