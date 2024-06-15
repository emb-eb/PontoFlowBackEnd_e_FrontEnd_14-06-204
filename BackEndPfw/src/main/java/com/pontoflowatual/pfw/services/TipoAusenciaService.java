package com.pontoflowatual.pfw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pontoflowatual.pfw.models.TipoAusencia;
import com.pontoflowatual.pfw.repositories.TipoAusenciaRepository;

@Service
public class TipoAusenciaService {

    @Autowired
    private TipoAusenciaRepository tipoAusenciaRepository;

    public List<TipoAusencia> getAllTiposAusencia() {
        return tipoAusenciaRepository.findAll();
    }

    public TipoAusencia getTipoAusenciaById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }

        Optional<TipoAusencia> tipoAusenciaOptional = tipoAusenciaRepository.findById(id);
        return tipoAusenciaOptional.orElse(null);
    }

    public TipoAusencia salvaTipoAusencia(TipoAusencia tipoAusencia) {

        if (tipoAusencia == null) {
            throw new IllegalArgumentException("tipoAusencia não pode ser nulo");
        }

        TipoAusencia savedTipoAusencia = tipoAusenciaRepository.save(tipoAusencia);
        return savedTipoAusencia;

    }

    @Transactional
    public TipoAusencia updateTipoAusencia(Long id, TipoAusencia tipoAusencia)

    {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }

        Optional<TipoAusencia> tipoAusenciaOptional = tipoAusenciaRepository.findById(id);
        if (tipoAusenciaOptional.isPresent()) {
            TipoAusencia existingTipoAusencia = tipoAusenciaOptional.get();
            existingTipoAusencia.setDescricaoAusencia(tipoAusencia.getDescricaoAusencia());
            return tipoAusenciaRepository.save(existingTipoAusencia);
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteTipoAusencia(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não encontrado");
        }

        tipoAusenciaRepository.deleteById(id);
    }

}
