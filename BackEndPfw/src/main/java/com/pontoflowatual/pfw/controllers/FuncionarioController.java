package com.pontoflowatual.pfw.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pontoflowatual.pfw.models.Funcionario;
import com.pontoflowatual.pfw.models.Departamento;
import com.pontoflowatual.pfw.models.SolicitarAusencia;
import com.pontoflowatual.pfw.services.FuncionarioService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> getAllFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.getAllFuncionarios();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(id);
        return funcionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Funcionario> createFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario savedFuncionario = funcionarioService.saveFuncionario(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFuncionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionarioDetails) {
        Optional<Funcionario> optionalFuncionario = funcionarioService.getFuncionarioById(id);
        if (optionalFuncionario.isPresent()) {
            Funcionario existingFuncionario = optionalFuncionario.get();
            existingFuncionario.setNomeFuncionario(funcionarioDetails.getNomeFuncionario());
            existingFuncionario.setNumCNI(funcionarioDetails.getNumCNI());
            existingFuncionario.setNumNIF(funcionarioDetails.getNumNIF());
            existingFuncionario.setDataNascimento(funcionarioDetails.getDataNascimento());
            existingFuncionario.setEmail(funcionarioDetails.getEmail());
            existingFuncionario.setDataAdmissao(funcionarioDetails.getDataAdmissao());
            existingFuncionario.setFuncao(funcionarioDetails.getFuncao());
            existingFuncionario.setTelefone(funcionarioDetails.getTelefone());
            existingFuncionario.setDataCriacao(funcionarioDetails.getDataCriacao());
            existingFuncionario.setDepartamento(funcionarioDetails.getDepartamento()); // Defina o departamento conforme necessário

            Funcionario updatedFuncionario = funcionarioService.updateFuncionario(id, funcionarioDetails);
            return ResponseEntity.ok(updatedFuncionario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(id);
        if (funcionario.isPresent()) {
            funcionarioService.deleteFuncionario(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}/departamento")
    public ResponseEntity<Departamento> getDepartamentoByFuncionarioId(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(id);
        return funcionario.map(f -> ResponseEntity.ok(f.getDepartamento())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/solicitacoes-ausencia")
    public ResponseEntity<List<SolicitarAusencia>> getSolicitacoesAusenciaByFuncionarioId(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(id);
        return funcionario.map(f -> ResponseEntity.ok(f.getSolicitacoesAusencia())).orElseGet(() -> ResponseEntity.notFound().build());
    }

   
    @GetMapping("/buscarPorNome")
    public ResponseEntity<List<Funcionario>> buscarPorNome(@RequestParam String nome) {
        List<Funcionario> funcionarios = funcionarioService.buscarPorNome(nome);
        return ResponseEntity.ok(funcionarios);
    }
    
    
    @GetMapping("/buscarPorEmail")
    public ResponseEntity<?> buscarPorEmail(@RequestParam String email) {
        Optional<Funcionario> funcionario = funcionarioService.buscarPorEmail(email);
        return funcionario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
