package com.pontoflowatual.pfw.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;





@Entity
public class GerenteDepartamento {

 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
   

     @OneToOne 
     @Getter
     @Setter
    private Departamento departamento;
    
       @ManyToOne
    private Funcionario gerente;
    

}
