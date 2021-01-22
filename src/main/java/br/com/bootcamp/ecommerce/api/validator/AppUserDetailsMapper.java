package br.com.bootcamp.ecommerce.api.validator;

import br.com.bootcamp.ecommerce.api.validator.seguranca.UserDetailsMapper;
import br.com.bootcamp.ecommerce.domain.model.Usuario;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper {

	@Override
	public UsuarioLogado map(Object shouldBeASystemUser) {
		return new UsuarioLogado((Usuario)shouldBeASystemUser);
	}

}
