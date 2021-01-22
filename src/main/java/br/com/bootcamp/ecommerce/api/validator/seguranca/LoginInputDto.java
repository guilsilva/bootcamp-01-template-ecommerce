package br.com.bootcamp.ecommerce.api.validator.seguranca;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
public class LoginInputDto {

	private String login;
	private String password;

	public UsernamePasswordAuthenticationToken build() {
		return new UsernamePasswordAuthenticationToken(this.login,
				this.password);
	}
}
