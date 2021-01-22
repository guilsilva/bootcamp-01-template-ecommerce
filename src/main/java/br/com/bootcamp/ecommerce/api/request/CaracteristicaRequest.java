package br.com.bootcamp.ecommerce.api.request;

import br.com.bootcamp.ecommerce.api.validator.UniqueValue;
import br.com.bootcamp.ecommerce.domain.model.Caracteristicas;
import lombok.Getter;

@Getter
public class CaracteristicaRequest {
    @UniqueValue(domainClass = Caracteristicas.class, fieldName = "nome")
    private String nome;
    private String valor;

    public Caracteristicas toModel(){
        return new Caracteristicas(this.nome, this.valor);
    }
}
