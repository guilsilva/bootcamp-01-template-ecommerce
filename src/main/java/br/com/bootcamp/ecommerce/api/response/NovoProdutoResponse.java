package br.com.bootcamp.ecommerce.api.response;

import br.com.bootcamp.ecommerce.domain.model.Produto;
import br.com.bootcamp.ecommerce.domain.model.Usuario;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NovoProdutoResponse {
    private Long id;
    private String nome;
    private String descricao;
    private Integer quantidade;
    private BigDecimal valor;
    private NovaCategoriaResponse categoria;
    private List<CaracteristicasResponse> caracteristicas;
    private LocalDate instanteCriacao;
    private Usuario usuario;

    public NovoProdutoResponse(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.quantidade = produto.getQuantidade();
        this.valor = produto.getValor();
        this.categoria = new NovaCategoriaResponse(produto.getCategoria());
        this.caracteristicas = produto.getCaracteristicas().stream()
                .map(CaracteristicasResponse::new)
                .collect(Collectors.toList());
        this.instanteCriacao = produto.getInstanteCriacao();
        this.usuario = produto.getUsuario();
    }
}
