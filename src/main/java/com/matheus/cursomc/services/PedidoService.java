package com.matheus.cursomc.services;

import com.matheus.cursomc.domain.Pedido;
import com.matheus.cursomc.repositories.PedidoRepository;
import com.matheus.cursomc.services.exceptions.ObjectNotFoundtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;


    public Pedido find(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundtException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }


}
