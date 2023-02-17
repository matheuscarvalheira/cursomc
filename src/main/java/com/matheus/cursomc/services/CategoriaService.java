package com.matheus.cursomc.services;

import com.matheus.cursomc.domain.Categoria;
import com.matheus.cursomc.repositories.CategoriaRepository;
import com.matheus.cursomc.services.exceptions.ObjectNotFoundtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;


    public Categoria find(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundtException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj){
        obj.setId(null);
        return repo.save(obj);
    }


}
