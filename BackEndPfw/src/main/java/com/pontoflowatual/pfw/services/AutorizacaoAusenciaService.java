package com.pontoflowatual.pfw.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pontoflowatual.pfw.models.AutorizacaoAusencia;
import com.pontoflowatual.pfw.models.Funcionario;
import com.pontoflowatual.pfw.models.SolicitarAusencia;
import com.pontoflowatual.pfw.models.TipoAprovacao;
import com.pontoflowatual.pfw.repositories.AutorizacaoAusenciaRepository;

@Service
@Transactional
public class AutorizacaoAusenciaService {

    private final AutorizacaoAusenciaRepository autorizacaoAusenciaRepository;

    public AutorizacaoAusenciaService(AutorizacaoAusenciaRepository autorizacaoAusenciaRepository) {
        this.autorizacaoAusenciaRepository = autorizacaoAusenciaRepository;
    }

    public AutorizacaoAusencia criarAutorizacaoAusencia(SolicitarAusencia solicitacaoAusencia, Funcionario funcionario, TipoAprovacao tipoAprovacao) {
        AutorizacaoAusencia autorizacaoAusencia = new AutorizacaoAusencia();
        autorizacaoAusencia.setSolicitacaoAusencia(solicitacaoAusencia);
        autorizacaoAusencia.setFuncionario(funcionario);
        autorizacaoAusencia.setTipoAprovacao(tipoAprovacao);
        autorizacaoAusencia.setDataAprovacao(LocalDate.now());
        return autorizacaoAusenciaRepository.save(autorizacaoAusencia);
    }

    @Transactional(readOnly = true)
    public List<AutorizacaoAusencia> listarAutorizacoesPorFuncionario(Funcionario funcionario) {
        return autorizacaoAusenciaRepository.findByFuncionario(funcionario);
    }

    @Transactional(readOnly = true)
    public Optional<AutorizacaoAusencia> buscarAutorizacaoPorId(Long id) {
        return autorizacaoAusenciaRepository.findById(id);
    }

    
}
