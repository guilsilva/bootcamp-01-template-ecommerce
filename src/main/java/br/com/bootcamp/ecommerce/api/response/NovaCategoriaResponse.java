package br.com.bootcamp.ecommerce.api.response;

import br.com.bootcamp.ecommerce.domain.model.Categoria;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NovaCategoriaResponse {
    private Long id;
    private String nome;
    private NovaCategoriaResponse categoriaMae;

    public NovaCategoriaResponse(Categoria categoria){
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.categoriaMae = defineCategoriaMae(categoria);
    }

    public NovaCategoriaResponse defineCategoriaMae(Categoria categoria){
        if(categoria.getCategoriaMae() != null){
            return new NovaCategoriaResponse(categoria.getCategoriaMae());
        }
        return null;
    }
}
