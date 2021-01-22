package br.com.bootcamp.ecommerce.domain.model;

import br.com.bootcamp.ecommerce.api.validator.ExistentValue;
import br.com.bootcamp.ecommerce.api.validator.UniqueValue;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @OneToOne
    private Categoria categoriaMae;

    @Deprecated
    public Categoria(){}

    public Categoria(@NotBlank(message = "{NotBlank}") String nome, Categoria categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public Categoria(@NotBlank(message = "{NotBlank}") String nome){
        this.nome = nome;
    }
}
