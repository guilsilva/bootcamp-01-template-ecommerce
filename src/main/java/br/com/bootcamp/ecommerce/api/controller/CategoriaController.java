package br.com.bootcamp.ecommerce.api.controller;

import br.com.bootcamp.ecommerce.api.request.NovaCategoriaRequest;
import br.com.bootcamp.ecommerce.api.response.NovaCategoriaResponse;
import br.com.bootcamp.ecommerce.api.validator.UsuarioLogado;
import br.com.bootcamp.ecommerce.domain.model.Categoria;
import br.com.bootcamp.ecommerce.domain.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequestMapping("/api/categoria")
public class CategoriaController {
    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<NovaCategoriaResponse> cadastrar(@RequestBody @Valid NovaCategoriaRequest request,
                                                           UriComponentsBuilder uriComponentsBuilder){
        Categoria categoria = request.toModel(categoriaRepository);
        categoriaRepository.save(categoria);
        URI uri = uriComponentsBuilder.path("/api/categoria/{id}")
                .buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new NovaCategoriaResponse(categoria));
    }
}
