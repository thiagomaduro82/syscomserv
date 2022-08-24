package com.syscomserv.app.services;

import com.syscomserv.app.models.Unidade;
import com.syscomserv.app.repositories.UnidadeRepository;
import com.syscomserv.app.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;


    public UnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    public Unidade findById(Integer id){
        Optional<Unidade> unidade = unidadeRepository.findById(id);
        return unidade.orElseThrow(() -> new ObjectNotFoundException("Unidade não encontrada! Id: "+id+", Type: "+Unidade.class.getName()));
    }

    public Page<Unidade> findAll(Pageable pageable){
        return unidadeRepository.findAll(pageable);
    }

    public List<Unidade> listAll(Sort sort){ return unidadeRepository.findAll(sort);}

    public Unidade create(Unidade unidade) {
        unidade.setId(null);
        unidade.setCriadoEm(new Timestamp(System.currentTimeMillis()));
        unidade.setAlteradoEm(new Timestamp(System.currentTimeMillis()));
        return unidadeRepository.save(unidade);
    }

    public Unidade update(Integer id, Unidade unidade) {
        Unidade unidadeUpdated = findById(id);
        unidadeUpdated.setDescricao(unidade.getDescricao());
        unidadeUpdated.setCriadoEm(unidadeUpdated.getCriadoEm());
        unidadeUpdated.setAlteradoEm(new Timestamp(System.currentTimeMillis()));
        return unidadeRepository.save(unidadeUpdated);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            unidadeRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new com.syscomserv.app.services.exceptions.DataIntegrityViolationException("Unidade não pode ser apagada!");
        }
    }
}
