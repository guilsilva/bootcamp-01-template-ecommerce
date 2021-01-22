package br.com.bootcamp.ecommerce.api.controller;

import br.com.bootcamp.ecommerce.api.response.NovoUsuarioResponse;
import br.com.bootcamp.ecommerce.api.request.NovoUsuarioRequest;
import br.com.bootcamp.ecommerce.domain.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @PersistenceContext
    EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @PostMapping
    @Transactional
    public ResponseEntity<NovoUsuarioResponse> cadastrar(@RequestBody @Valid NovoUsuarioRequest request,
                                                         UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = request.toModel();
        entityManager.persist(usuario);
        URI uri = uriComponentsBuilder.path("/api/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new NovoUsuarioResponse(usuario));
    }
}
