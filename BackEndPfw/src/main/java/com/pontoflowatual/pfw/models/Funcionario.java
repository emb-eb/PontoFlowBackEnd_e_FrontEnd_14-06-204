package com.pontoflowatual.pfw.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "tb_funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String nomeFuncionario;

    @Getter @Setter
    private String numCNI;

    @Getter @Setter
    private String numNIF;

    @Getter @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @Getter @Setter
    private String email;

    @Getter @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAdmissao;

    @Getter @Setter
    private String funcao;

    @Getter @Setter
    private String telefone;

    @Getter @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCriacao;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    @Getter @Setter
    @JsonIgnore
    private List<SolicitarAusencia> solicitacoesAusencia;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    @Getter @Setter
  
    private Departamento departamento;

    @OneToOne(mappedBy = "funcionario", cascade = CascadeType.ALL)
    @Getter @Setter
    private Usuario usuario;
    
    

    public Funcionario(String nomeFuncionario, String numCNI, String numNIF, LocalDate dataNascimento, String email, LocalDate dataAdmissao, String funcao, String telefone, LocalDate dataCriacao, Departamento departamento) {
        this.nomeFuncionario = nomeFuncionario;
        this.numCNI = numCNI;
        this.numNIF = numNIF;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.dataAdmissao = dataAdmissao;
        this.funcao = funcao;
        this.telefone = telefone;
        this.dataCriacao = dataCriacao;
        this.departamento = departamento;
    }



    public Funcionario(Long id) {
        this.id = id;
    }
    
}
