package br.com.bootcamp.ecommerce.domain.model;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Entity
@Getter
public class Produto {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "{NotBlank}")
    private String nome;
    @NotNull(message = "{NotBlank}")
    @Positive(message = "{Positive}")
    private BigDecimal valor;
    @NotNull(message = "{NotBlank}")
    @PositiveOrZero(message = "{Positive}")
    private Integer quantidade;
    @NotNull(message = "{NotBlank}")
    @OneToOne
    private Categoria categoria;
    @NotBlank(message = "{NotBlank}")
    @Length(max = 1000, message = "{LengthMax1000}")
    private String descricao;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Caracteristicas> caracteristicas;
    @NotNull(message = "{NotBlank}")
    private LocalDate instanteCriacao = LocalDate.now();
    @NotNull(message = "{NotBlank}")
    @ManyToOne
    private Usuario usuario;

    @Deprecated
    public Produto(){}

    public Produto(@NotBlank(message = "{NotBlank}") String nome,
                   @NotNull(message = "{NotBlank}")
                   @Positive(message = "{Positive}") BigDecimal valor,
                   @NotNull(message = "{NotBlank}")
                   @PositiveOrZero(message = "{Positive}") Integer quantidade,
                   @NotNull(message = "{NotBlank}") Categoria categoria,
                   @NotBlank(message = "{NotBlank}")
                   @Length(max = 1000, message = "{LengthMax1000}") String descricao,
                   List<Caracteristicas> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
    }

    public void persist(EntityManager manager, Usuario usuario, Produto produto){
        produto.usuario = usuario;
        manager.persist(produto);
    }
}
