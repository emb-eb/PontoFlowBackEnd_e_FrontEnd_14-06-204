package com.pontoflowatual.pfw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pontoflowatual.pfw.models.AutorizacaoAusencia;
import com.pontoflowatual.pfw.models.Funcionario;

@Repository
public interface AutorizacaoAusenciaRepository extends JpaRepository<AutorizacaoAusencia, Long> {
   
    List<AutorizacaoAusencia> findByFuncionario(Funcionario funcionario);
  
}
