package com.pontoflowatual.pfw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pontoflowatual.pfw.models.TipoAusencia;

@Repository
public interface TipoAusenciaRepository extends JpaRepository<TipoAusencia, Long> {
}
