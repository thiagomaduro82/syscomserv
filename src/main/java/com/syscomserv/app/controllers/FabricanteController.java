package com.syscomserv.app.controllers;

import com.syscomserv.app.models.Fabricante;
import com.syscomserv.app.services.FabricanteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/fabricante")
public class FabricanteController {

    final
    FabricanteService fabricanteService;

    public FabricanteController(FabricanteService fabricanteService) {
        this.fabricanteService = fabricanteService;
    }

    @GetMapping
    public ResponseEntity<Page<Fabricante>> findAll(Pageable pageable){
        Page<Fabricante> listFabricantes = fabricanteService.findAll(pageable);
        return ResponseEntity.ok().body(listFabricantes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Fabricante> findById(@PathVariable Integer id){
        Fabricante fabricante = fabricanteService.findById(id);
        return ResponseEntity.ok().body(fabricante);
    }

    @GetMapping(value = "/list-all")
    public ResponseEntity<List<Fabricante>> listAll(){
        return ResponseEntity.ok().body(fabricanteService.listAll());
    }

    @PostMapping
    public ResponseEntity<Fabricante> create(@Valid @RequestBody Fabricante fabricante){
        fabricante = fabricanteService.create(fabricante);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(fabricante.getId()).toUri();
        return ResponseEntity.created(uri).body(fabricante);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Fabricante> update(@Valid @PathVariable Integer id, @RequestBody Fabricante fabricante){
        Fabricante updatedFabricante = fabricanteService.update(id, fabricante);
        return ResponseEntity.ok().body(updatedFabricante);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        fabricanteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
