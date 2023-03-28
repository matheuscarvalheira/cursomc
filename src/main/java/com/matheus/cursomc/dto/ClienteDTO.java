package com.matheus.cursomc.dto;

import com.matheus.cursomc.domain.Cliente;

import java.io.Serializable;

public class ClienteDTO implements Serializable {

    private Integer id;
    private String nome;
    private String email;


    public ClienteDTO(Cliente obj) {
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
