package com.syscomserv.app.services;

import com.syscomserv.app.models.Fabricante;
import com.syscomserv.app.repositories.FabricanteRepository;
import com.syscomserv.app.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class FabricanteService {

    final
    FabricanteRepository fabricanteRepository;

    public FabricanteService(FabricanteRepository fabricanteRepository) {
        this.fabricanteRepository = fabricanteRepository;
    }

    public Fabricante findById(Integer id){
        Optional<Fabricante> fabricante = fabricanteRepository.findById(id);
        return fabricante.orElseThrow(() -> new ObjectNotFoundException("Fabricante não encontrado! Id: "+id+", Type: "+ Fabricante.class.getName()));
    }

    public Page<Fabricante> findAll(Pageable pageable){
        return fabricanteRepository.findAll(pageable);
    }

    public List<Fabricante> listAll(){
        return fabricanteRepository.findAll();
    }

    public Fabricante create(Fabricante fabricante) {
        fabricante.setId(null);
        fabricante.setCriadoEm(new Timestamp(System.currentTimeMillis()));
        fabricante.setAlteradoEm(new Timestamp(System.currentTimeMillis()));
        return fabricanteRepository.save(fabricante);
    }

    public Fabricante update(Integer id, Fabricante fabricante) {
        Fabricante fabricanteUpdated = findById(id);
        fabricanteUpdated.setDescricao(fabricante.getDescricao());
        fabricanteUpdated.setCriadoEm(fabricanteUpdated.getCriadoEm());
        fabricanteUpdated.setAlteradoEm(new Timestamp(System.currentTimeMillis()));
        return fabricanteRepository.save(fabricanteUpdated);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            fabricanteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new com.syscomserv.app.services.exceptions.DataIntegrityViolationException("Fabricante não pode ser apagado!");
        }
    }
}
