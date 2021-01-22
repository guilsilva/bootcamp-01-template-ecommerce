package br.com.bootcamp.ecommerce.api.request;

import br.com.bootcamp.ecommerce.api.validator.UniqueValue;
import br.com.bootcamp.ecommerce.domain.model.Usuario;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class NovoUsuarioRequest {
    @NotBlank(message = "{NotBlank}")
    @Email(message = "{Email}")
    @UniqueValue(fieldName = "login", domainClass = Usuario.class)
    private String login;
    @NotBlank
    @Length(min = 6, message = "{LengthMin6}")
    private String senha;

    public Usuario toModel(){
        return new Usuario(this.login, this.senha);
    }
}
