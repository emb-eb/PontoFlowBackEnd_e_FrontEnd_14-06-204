package com.pontoflowatual.pfw.services;

import com.pontoflowatual.pfw.models.Funcionario;
import com.pontoflowatual.pfw.repositories.FuncionarioRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional 
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> getFuncionarioById(@NonNull Long id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario saveFuncionario(@NonNull Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void deleteFuncionario(@NonNull Long id) {
        funcionarioRepository.deleteById(id);
    }

    public Funcionario updateFuncionario(@NonNull Long id, @NonNull Funcionario funcionario) {
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);
        if (optionalFuncionario.isPresent()) {
            Funcionario existingFuncionario = optionalFuncionario.get();
           
            existingFuncionario.setNomeFuncionario(funcionario.getNomeFuncionario());
            existingFuncionario.setNumCNI(funcionario.getNumCNI());
            existingFuncionario.setNumNIF(funcionario.getNumNIF());
            existingFuncionario.setDataNascimento(funcionario.getDataNascimento());
            existingFuncionario.setEmail(funcionario.getEmail());
            existingFuncionario.setDataAdmissao(funcionario.getDataAdmissao());
            existingFuncionario.setFuncao(funcionario.getFuncao());
            existingFuncionario.setTelefone(funcionario.getTelefone());
            existingFuncionario.setDataCriacao(funcionario.getDataCriacao());
            existingFuncionario.setDepartamento(funcionario.getDepartamento());
            
            return funcionarioRepository.save(existingFuncionario);
        } else {
            
            return null; 
        }
    }

  // funcao pa busca funcionario pa Nome
    public List<Funcionario> buscarPorNome(@NonNull String nome) {
        return funcionarioRepository.findByNomeFuncionario(nome);
    }
    
    // Função para buscar funcionário por email
    public Optional<Funcionario> buscarPorEmail(@NonNull String email) {
        return funcionarioRepository.findByEmail(email);
    }
}
