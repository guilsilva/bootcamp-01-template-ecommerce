package br.com.bootcamp.ecommerce.api.validator.seguranca;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
public class LoginResponse {
    private String token;

    public LoginResponse(String token){
        this.token = token;
    }
}
