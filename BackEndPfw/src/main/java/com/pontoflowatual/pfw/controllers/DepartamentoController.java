package com.pontoflowatual.pfw.controllers;
import com.pontoflowatual.pfw.models.Departamento;
import com.pontoflowatual.pfw.models.Funcionario;
import com.pontoflowatual.pfw.services.DepartamentoService;

import lombok.NonNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping
    public ResponseEntity<List<Departamento>> listarDepartamentos() {
        List<Departamento> departamentos = departamentoService.listarDepartamentos();
        return ResponseEntity.ok(departamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> obterDepartamentoPorId(@PathVariable @NonNull Long id) throws Exception {
        Departamento departamento = departamentoService.obterDepartamentoPorId(id);
        if (departamento != null) {
            return ResponseEntity.ok(departamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Departamento> criarDepartamento(@RequestBody @NonNull Departamento departamento) {
        Departamento novoDepartamento = departamentoService.criarDepartamento(departamento);
        if (novoDepartamento != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(novoDepartamento);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> atualizarDepartamento(@PathVariable @NonNull Long id, @RequestBody Departamento departamento) throws Exception {
        Departamento departamentoAtualizado = departamentoService.atualizarDepartamento(id, departamento);
        if (departamentoAtualizado != null) {
            return ResponseEntity.ok(departamentoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDepartamento(@PathVariable @NonNull Long id) throws Exception {
        try {
            departamentoService.deletarDepartamento(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/funcionarios")
    public ResponseEntity<List<Funcionario>> listarFuncionariosPorDepartamento(@PathVariable Long id) {
        List<Funcionario> funcionarios = departamentoService.listarFuncionariosPorDepartamento(id);
        return ResponseEntity.ok(funcionarios);
    }
}