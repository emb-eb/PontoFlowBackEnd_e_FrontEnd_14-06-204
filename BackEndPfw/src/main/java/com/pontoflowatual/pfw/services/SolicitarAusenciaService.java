package com.pontoflowatual.pfw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pontoflowatual.pfw.models.SolicitarAusencia;
import com.pontoflowatual.pfw.models.EnumEstadoAusencia;
import com.pontoflowatual.pfw.repositories.SolicitarAusenciaRepository;

@Service
@Transactional
public class SolicitarAusenciaService {

    private final SolicitarAusenciaRepository solicitarAusenciaRepository;

    public SolicitarAusenciaService(SolicitarAusenciaRepository solicitarAusenciaRepository) {
        this.solicitarAusenciaRepository = solicitarAusenciaRepository;
    }

    @Transactional(readOnly = true)
    public List<SolicitarAusencia> listarSolicitacoes() {
        return solicitarAusenciaRepository.findAll();
    }

    public SolicitarAusencia salvarSolicitarAusencia(SolicitarAusencia solicitacao) {
        solicitacao.setEstadoAusencia(EnumEstadoAusencia.PEDIDO); 
        if (solicitacao.getId() != null) {
            throw new IllegalArgumentException("A erro na gravaçao de solicitacao");
        }
        return solicitarAusenciaRepository.save(solicitacao);
    }

    @Transactional(readOnly = true)
    public Optional<SolicitarAusencia> buscarSolicitacaoPorId(Long id) {
        return solicitarAusenciaRepository.findById(id);
    }

    @Transactional
    public SolicitarAusencia atualizarSolicitacao(Long id, SolicitarAusencia novaSolicitacao) {
        if (novaSolicitacao == null) {
            throw new IllegalArgumentException("A nova solicitação não pode ser nula.");
        }
        return solicitarAusenciaRepository.findById(id)
                .map(solicitacao -> {
                    solicitacao.setFuncionario(novaSolicitacao.getFuncionario());
                    solicitacao.setDataInicio(novaSolicitacao.getDataInicio());
                    solicitacao.setDataTermino(novaSolicitacao.getDataTermino());
                    solicitacao.setNumeroDias(novaSolicitacao.getNumeroDias());
                    solicitacao.setMotivo(novaSolicitacao.getMotivo());
                    solicitacao.setTipoAusencia(novaSolicitacao.getTipoAusencia());
                    solicitacao.setEstadoAusencia(novaSolicitacao.getEstadoAusencia());
                    return solicitarAusenciaRepository.save(solicitacao);
                })
                .orElseThrow(() -> new IllegalArgumentException("Solicitação não encontrada para o ID: " + id));
    }

    @Transactional
    public SolicitarAusencia atualizarEstadoSolicitacao(Long id, EnumEstadoAusencia novoEstado) {
        return solicitarAusenciaRepository.findById(id)
                .map(solicitacao -> {
                    solicitacao.setEstadoAusencia(novoEstado);
                    return solicitarAusenciaRepository.save(solicitacao);
                })
                .orElseThrow(() -> new IllegalArgumentException("Solicitação não encontrada para o ID: " + id));
    }
}
