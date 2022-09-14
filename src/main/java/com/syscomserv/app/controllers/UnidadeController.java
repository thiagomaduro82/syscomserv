package com.syscomserv.app.controllers;

import com.syscomserv.app.models.Unidade;
import com.syscomserv.app.services.UnidadeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UnidadeController {

    private final UnidadeService unidadeService;

    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @GetMapping(value = "/unidade")
    @Operation(summary = "Retorna uma página de unidades.")
    public ResponseEntity<Page<Unidade>> findAll(Pageable pageable){
        Page<Unidade> listUnidades = unidadeService.findAll(pageable);
        return ResponseEntity.ok().body(listUnidades);
    }

    @GetMapping(value = "/unidade/{id}")
    @Operation(summary = "Retorna uma unidade específica pelo seu id.")
    public ResponseEntity<Unidade> findById(@PathVariable Integer id){
        Unidade unidade = unidadeService.findById(id);
        return ResponseEntity.ok().body(unidade);
    }

    @GetMapping(value = "/unidade/list-all")
    @Operation(summary = "Retorna uma lista de unidades sem paginação.")
    public ResponseEntity<List<Unidade>> listAll(){
            return ResponseEntity.ok().body(unidadeService.listAll());
    }

    @PostMapping(value = "/unidade")
    @Operation(summary = "Permite incluir uma unidade no banco de dados.")
    public ResponseEntity<Unidade> create(@Valid @RequestBody Unidade unidade){
        unidade = unidadeService.create(unidade);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(unidade.getId()).toUri();
        return ResponseEntity.created(uri).body(unidade);
    }

    @PutMapping(value = "/unidade/{id}")
    @Operation(summary = "Altera dados de uma Unidade pelo seu id.")
    public ResponseEntity<Unidade> update(@Valid @PathVariable Integer id, @RequestBody Unidade unidade){
        Unidade updatedUnidade = unidadeService.update(id, unidade);
        return ResponseEntity.ok().body(updatedUnidade);
    }

    @DeleteMapping(value = "/unidade/{id}")
    @Operation(summary = "Apaga do banco de dados uma Unidade pelo seu id.")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        unidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
