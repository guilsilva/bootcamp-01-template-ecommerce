package br.com.bootcamp.ecommerce.domain.model;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
@Getter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Email
    private String login;
    @NotBlank @Length(min = 6)
    private String senha;
    @NotNull
    @PastOrPresent
    private LocalDate instanteCadastro = LocalDate.now()
    @Deprecated
    public Usuario(){}
    ;

    public Usuario(@NotBlank @Email String login,
                   @NotBlank @Length(min = 6) String senha) {
        this.login = login;
        this.senha = new BCryptPasswordEncoder();
    }
}
