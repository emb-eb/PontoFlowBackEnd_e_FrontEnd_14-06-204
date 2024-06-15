package com.pontoflowatual.pfw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pontoflowatual.pfw.models.EnumEstadoAusencia;
import com.pontoflowatual.pfw.models.SolicitarAusencia;
import com.pontoflowatual.pfw.models.TipoAusencia;

@Repository
public interface SolicitarAusenciaRepository extends JpaRepository<SolicitarAusencia, Long> {
   
    List<SolicitarAusencia> findByFuncionario_Id(Long funcionarioId);
   
    List<SolicitarAusencia> findByEstadoAusencia(EnumEstadoAusencia estadoAusencia);
     List<SolicitarAusencia> findByTipoAusencia(TipoAusencia tipoAusencia); 

    
  
}
