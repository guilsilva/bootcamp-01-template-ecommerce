package br.com.bootcamp.ecommerce.domain.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Caracteristicas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String valor;

    @Deprecated
    public Caracteristicas(){}

    public Caracteristicas(String nome, String valor){
        this.nome = nome;
        this.valor = valor;
    }
}
