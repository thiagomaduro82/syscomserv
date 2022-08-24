package com.syscomserv.app.controllers;

import com.syscomserv.app.models.Unidade;
import com.syscomserv.app.services.UnidadeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/unidade")
public class UnidadeController {

    private final UnidadeService unidadeService;

    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @GetMapping
    public ResponseEntity<Page<Unidade>> findAll(Pageable pageable){
        Page<Unidade> listUnidades = unidadeService.findAll(pageable);
        return ResponseEntity.ok().body(listUnidades);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Unidade> findById(@PathVariable Integer id){
        Unidade unidade = unidadeService.findById(id);
        return ResponseEntity.ok().body(unidade);
    }

    @GetMapping(value = "/list-all/{campo}/{ordem}")
    public ResponseEntity<List<Unidade>> listAll(@PathVariable String campo, @PathVariable String ordem){
        if(ordem.equals("crescente")){
            return ResponseEntity.ok().body(unidadeService.listAll(Sort.by(Sort.Direction.ASC, campo)));
        } else {
            return ResponseEntity.ok().body(unidadeService.listAll(Sort.by(Sort.Direction.DESC, campo)));
        }

    }

    @PostMapping
    public ResponseEntity<Unidade> create(@Valid @RequestBody Unidade unidade){
        unidade = unidadeService.create(unidade);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(unidade.getId()).toUri();
        return ResponseEntity.created(uri).body(unidade);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Unidade> update(@Valid @PathVariable Integer id, @RequestBody Unidade unidade){
        Unidade updatedUnidade = unidadeService.update(id, unidade);
        return ResponseEntity.ok().body(updatedUnidade);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        unidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
