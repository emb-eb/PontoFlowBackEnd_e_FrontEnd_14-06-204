package com.pontoflowatual.pfw.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_departamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    @NotBlank(message = "O nome do departamento é obrigatório")
    private String nomeDepartamento;

    @Getter @Setter
    private String descricaoDepartamento;

  

   @JsonIgnore
    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
    private List<Funcionario> funcionarios;

}
