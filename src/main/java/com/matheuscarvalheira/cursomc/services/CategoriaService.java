package com.matheuscarvalheira.cursomc.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheuscarvalheira.cursomc.domain.Categoria;
import com.matheuscarvalheira.cursomc.repositories.CategoriaRepository;
import com.matheuscarvalheira.cursomc.services.exceptions.ObjectNotFountException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFountException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

}
