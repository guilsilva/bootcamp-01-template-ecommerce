package br.com.bootcamp.ecommerce.api.request;

import br.com.bootcamp.ecommerce.api.validator.ExistentValue;
import br.com.bootcamp.ecommerce.domain.model.Caracteristicas;
import br.com.bootcamp.ecommerce.domain.model.Categoria;
import br.com.bootcamp.ecommerce.domain.model.Produto;
import br.com.bootcamp.ecommerce.domain.repository.CategoriaRepository;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class NovoProdutoRequest {
    @NotBlank(message = "{NotBlank}")
    private String nome;
    @NotNull(message = "{NotBlank}")
    @Positive(message = "{Positive}")
    private BigDecimal valor;
    @NotNull(message = "{NotBlank}")
    @PositiveOrZero(message = "{Positive}")
    private Integer quantidade;
    @NotBlank(message = "{NotBlank}")
    @Length(max = 1000, message = "{LengthMax1000}")
    private String descricao;
    @NotBlank(message = "{NotBlank}")
    @ExistentValue(domainClass = Categoria.class, fieldName = "nome")
    private String categoria;
    @Valid
    private List<CaracteristicaRequest> caracteristicas ;

    public Produto toModel(CategoriaRepository categoriaRepository){
        return new Produto(this.nome, this.valor, this.quantidade,
                categoriaRepository.findByNome(this.categoria).get(),
                this.descricao, toCaracteristicaModel());
    }

    public List<Caracteristicas> toCaracteristicaModel(){
        return this.caracteristicas.stream().map(CaracteristicaRequest::toModel).collect(Collectors.toList());
    }
}
