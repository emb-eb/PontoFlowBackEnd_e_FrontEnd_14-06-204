package com.pontoflowatual.pfw.models;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_autorizacao_ausencia")
@NoArgsConstructor
@AllArgsConstructor
public class AutorizacaoAusencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solicitacao_ausencia_id", nullable = false)
    @Getter @Setter
    private SolicitarAusencia solicitacaoAusencia;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    @Getter @Setter
    private Funcionario funcionario;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private TipoAprovacao tipoAprovacao;

    @Column
    @Getter @Setter
    private LocalDate dataAprovacao;

    
}
