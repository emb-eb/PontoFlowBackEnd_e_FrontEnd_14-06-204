package com.pontoflowatual.pfw.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pontoflowatual.pfw.models.EnumEstadoAusencia;
import com.pontoflowatual.pfw.models.Funcionario;
import com.pontoflowatual.pfw.models.SolicitarAusencia;
import com.pontoflowatual.pfw.services.FuncionarioService;
import com.pontoflowatual.pfw.services.SolicitarAusenciaService;


import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/solicitarAusencia")
public class SolicitarAusenciaController {

    private final SolicitarAusenciaService solicitarAusenciaService;
    private final FuncionarioService funcionarioService;

    public SolicitarAusenciaController(FuncionarioService funcionarioService,
            SolicitarAusenciaService solicitarAusenciaService) {
        this.solicitarAusenciaService = solicitarAusenciaService;
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public ResponseEntity<List<SolicitarAusencia>> listarSolicitacoes() {
        List<SolicitarAusencia> solicitacoes = solicitarAusenciaService.listarSolicitacoes();
        return ResponseEntity.ok(solicitacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarSolicitacaoPorId(@PathVariable Long id) {
        try {
            Optional<SolicitarAusencia> solicitacao = solicitarAusenciaService.buscarSolicitacaoPorId(id);
            return solicitacao.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> salvarSolicitacao(@Valid @RequestBody SolicitarAusencia solicitacao) {
        try {
            Long funcionarioId = solicitacao.getFuncionario().getId(); // Obter o ID do funcionário do objeto SolicitarAusencia
            Optional<Funcionario> funcionarioOptional = funcionarioService.getFuncionarioById(funcionarioId);
            if (!funcionarioOptional.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            
            // Atribuir o funcionário à solicitação de ausência
            solicitacao.setFuncionario(funcionarioOptional.get());
    
            SolicitarAusencia novaSolicitacao = solicitarAusenciaService.salvarSolicitarAusencia(solicitacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaSolicitacao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarSolicitacao(@PathVariable Long id,
            @Valid @RequestBody SolicitarAusencia novaSolicitacao) {
        try {
            SolicitarAusencia solicitacaoAtualizada = solicitarAusenciaService.atualizarSolicitacao(id,
                    novaSolicitacao);
            return solicitacaoAtualizada != null ? ResponseEntity.ok(solicitacaoAtualizada)
                    : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}/atualizarEstado")
    public ResponseEntity<?> atualizarEstadoSolicitacao(@PathVariable Long id,
            @RequestParam("estado") EnumEstadoAusencia novoEstado) {
        try {
            SolicitarAusencia solicitacaoAtualizada = solicitarAusenciaService.atualizarEstadoSolicitacao(id,
                    novoEstado);
            return solicitacaoAtualizada != null ? ResponseEntity.ok(solicitacaoAtualizada)
                    : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
