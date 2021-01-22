package br.com.bootcamp.ecommerce.api.request;


import br.com.bootcamp.ecommerce.api.validator.ExistentValue;
import br.com.bootcamp.ecommerce.api.validator.UniqueValue;
import br.com.bootcamp.ecommerce.domain.model.Categoria;
import br.com.bootcamp.ecommerce.domain.repository.CategoriaRepository;
import lombok.Getter;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Getter
public class NovaCategoriaRequest {
    @NotBlank(message = "{NotBlank}")
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;
    @ExistentValue(domainClass = Categoria.class, fieldName = "nome")
    private String categoriaMae;

    public Categoria toModel(CategoriaRepository categoriaRepository){
        if(categoriaMae != null){
            Optional<Categoria> categoriaMae = categoriaRepository.findByNome(this.categoriaMae);
            return new Categoria(nome, categoriaMae.get());
        }
        return new Categoria(nome);
    }
}
