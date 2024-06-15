package com.pontoflowatual.pfw.services;

import com.pontoflowatual.pfw.models.Departamento;
import com.pontoflowatual.pfw.models.Funcionario;
import com.pontoflowatual.pfw.repositories.DepartamentoRepository;
import com.pontoflowatual.pfw.repositories.FuncionarioRepository;

import lombok.NonNull;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {
    private final FuncionarioRepository funcionarioRepository;
    private final DepartamentoRepository departamentoRepository;

    public DepartamentoService(FuncionarioRepository funcionarioRepository, DepartamentoRepository departamentoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.departamentoRepository = departamentoRepository;
    }

    public List<Departamento> listarDepartamentos() {
        return departamentoRepository.findAll();
    }

    public Departamento obterDepartamentoPorId(@NonNull Long id) throws Exception {
        Optional<Departamento> departamentoOptional = departamentoRepository.findById(id);
        return departamentoOptional.orElseThrow(() -> new Exception("Departamento não encontrado com o ID: " + id));
    }

    public Departamento criarDepartamento(@NonNull Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public Departamento atualizarDepartamento(@NonNull Long id, Departamento departamento) throws Exception {
        if (!departamentoRepository.existsById(id)) {
            throw new Exception("Departamento não encontrado com o ID: " + id);
        }
        
        Departamento departamentoExistente = departamentoRepository.findById(id).get();
        departamentoExistente.setNomeDepartamento(departamento.getNomeDepartamento());
        departamentoExistente.setDescricaoDepartamento(departamento.getDescricaoDepartamento());

        return departamentoRepository.save(departamentoExistente);
    }

    public void deletarDepartamento(@NonNull Long id) throws Exception {
        if (!departamentoRepository.existsById(id)) {
            throw new Exception("Departamento não encontrado com o ID: " + id);
        }

        departamentoRepository.deleteById(id);
    }

    public List<Funcionario> listarFuncionariosPorDepartamento(Long departamentoId) {
        return funcionarioRepository.findByDepartamento_Id(departamentoId);
    }
}
