package br.com.bootcamp.ecommerce.api.response;

import br.com.bootcamp.ecommerce.domain.model.Caracteristicas;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CaracteristicasResponse {
    private Long id;
    private String nome;
    private String valor;

    public CaracteristicasResponse(Caracteristicas caracteristicas){
        this.id = caracteristicas.getId();
        this.nome = caracteristicas.getNome();
        this.valor = caracteristicas.getValor();
    }
}
