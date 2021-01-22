package br.com.bootcamp.ecommerce.api.response;

import br.com.bootcamp.ecommerce.domain.model.Usuario;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NovoUsuarioResponse {
    private final Long id;
    private final String login;
    private final LocalDate instanteCadastro;

    public NovoUsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.instanteCadastro = usuario.getInstanteCadastro();
    }
}
