package com.pontoflowatual.pfw.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pontoflowatual.pfw.models.TipoAusencia;
import com.pontoflowatual.pfw.services.TipoAusenciaService;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/tipoausencia")
public class TipoAusenciaController {
    
    @Autowired
    private TipoAusenciaService tipoAusenciaService;

    
    @GetMapping
    public ResponseEntity<List<TipoAusencia>> getAllTiposAusencia() {
        List<TipoAusencia> tiposAusencia = tipoAusenciaService.getAllTiposAusencia();
        return new ResponseEntity<>(tiposAusencia, HttpStatus.OK);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<TipoAusencia> getTipoAusenciaById(@PathVariable("id") Long id) {
        TipoAusencia tipoAusencia = tipoAusenciaService.getTipoAusenciaById(id);
        if (tipoAusencia != null) {
            return new ResponseEntity<>(tipoAusencia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para adicionar um novo tipo de ausência
    @PostMapping
    @Transactional
    public ResponseEntity<TipoAusencia> salvaTipoAusencia(@RequestBody TipoAusencia tipoAusencia) {

        if(tipoAusencia != null)
        {
        TipoAusencia novoTipoAusencia = tipoAusenciaService.salvaTipoAusencia(tipoAusencia);
        return new ResponseEntity<>(novoTipoAusencia, HttpStatus.CREATED);
        }
        else {

            return ResponseEntity.badRequest().build();
        }
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<TipoAusencia> updateTipoAusencia(@PathVariable("id") Long id,
            @RequestBody TipoAusencia tipoAusencia) {
        TipoAusencia updatedTipoAusencia = tipoAusenciaService.updateTipoAusencia(id, tipoAusencia);
        if (updatedTipoAusencia != null) {
            return new ResponseEntity<>(updatedTipoAusencia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoAusencia(@PathVariable("id") Long id) {
        tipoAusenciaService.deleteTipoAusencia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
