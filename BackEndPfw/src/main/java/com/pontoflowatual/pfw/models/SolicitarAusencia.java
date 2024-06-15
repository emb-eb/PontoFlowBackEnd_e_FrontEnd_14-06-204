package com.pontoflowatual.pfw.models;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;

@Entity
@Table(name = "tbsolicitar_ausencia")

@AllArgsConstructor
public class SolicitarAusencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    @NotNull(message = "O funcionário é obrigatório")
    @Getter @Setter
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "tipo_ausencia_id", nullable = false)
    @NotNull(message = "O tipo de ausência é obrigatório")
    @Getter @Setter
    private TipoAusencia tipoAusencia;

    @NotNull(message = "A data de início é obrigatória")
    @Getter @Setter
    private LocalDate dataInicio;

    @NotNull(message = "A data de término é obrigatória")
    @Getter @Setter
    private LocalDate dataTermino;

    @AssertTrue(message = "A data de término deve ser posterior à data de início")
    public boolean isDataTerminoAfterDataInicio() {
        return this.dataTermino != null && this.dataInicio != null && this.dataTermino.isAfter(this.dataInicio);
    }

    @NotNull(message = "A duração da ausência é obrigatória")
    @Getter @Setter
    private Long numeroDias;

    @Column(length = 1000)
    @Getter @Setter
    private String motivo;

    @Column(length = 255)
    @Getter @Setter
    private String anexo;

    @Column(name = "funcionario_a_substituir", length = 255)
    @Getter @Setter
    private String funcionarioASubstituir;

   

    @Enumerated(EnumType.STRING)
   
    @Getter @Setter
    private EnumEstadoAusencia estadoAusencia;

    // Adicionando o estado padrão como "Pedido" no construtor
    public SolicitarAusencia() {
        this.estadoAusencia = EnumEstadoAusencia.PEDIDO;
    }



    // Construtores, métodos setter e getter para outras propriedades da classe
}
