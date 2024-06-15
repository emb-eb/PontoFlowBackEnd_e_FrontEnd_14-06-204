package com.pontoflowatual.pfw.repositories;

import com.pontoflowatual.pfw.models.Funcionario;

import com.pontoflowatual.pfw.models.SolicitarAusencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    // Consulta funcionários por ID do departamento
    List<Funcionario> findByDepartamento_Id(Long departamentoId);

    // Consulta funcionários por solicitação de ausência
    List<Funcionario> findBySolicitacoesAusencia(SolicitarAusencia solicitacao);


    
    List<Funcionario> findByNomeFuncionario(String nome);

    // Consulta funcionário por email
    Optional<Funcionario> findByEmail(String email);

    // Se necessário, adicione métodos adicionais de consulta relacionados a funcionários aqui

}