package br.com.bootcamp.ecommerce.api.controller;

import br.com.bootcamp.ecommerce.api.request.NovasImagensRequest;
import br.com.bootcamp.ecommerce.api.request.NovoProdutoRequest;
import br.com.bootcamp.ecommerce.api.response.NovoProdutoResponse;
import br.com.bootcamp.ecommerce.api.validator.UsuarioLogado;
import br.com.bootcamp.ecommerce.domain.model.Produto;
import br.com.bootcamp.ecommerce.domain.repository.CategoriaRepository;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    UploaderFaker uploaderFaker;

    @PostMapping
    @Transactional
    public ResponseEntity<NovoProdutoResponse> cadastrar(@RequestBody @Valid NovoProdutoRequest request,
                                                         UriComponentsBuilder uriComponentsBuilder,
                                                         @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        Produto produto = request.toModel(categoriaRepository);
        produto.persist(entityManager, usuarioLogado.get(), produto);
        URI uri = uriComponentsBuilder.path("/api/produto/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new NovoProdutoResponse(produto));
    }

    @PostMapping(value = "/{id}/imagens")
    @Transactional
    public ResponseEntity<?> inserirImagens(@PathVariable("{id}") Long id,
                                            @Valid NovasImagensRequest novasImagensRequest){
        Set<String> links = uploaderFaker.envia(novasImagensRequest.getImagens());
        return ResponseEntity.ok().body("");
    }
}
