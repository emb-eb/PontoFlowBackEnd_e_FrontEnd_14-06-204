// TipoAusencia.java
package com.pontoflowatual.pfw.models;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;

@Entity
@Table(name = "tb_tipo_ausencia")
@AllArgsConstructor

public class TipoAusencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Getter @Setter
    private String descricaoAusencia;

    // Construtor padrão sem argumentos
    public TipoAusencia() {}

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Getter
    @Setter
    private Long maximoDia;


    // Construtor que aceita ID como String
    public TipoAusencia(String id) {
        this.id = Long.parseLong(id);
    }
}