package com.logica.desenvolvimento.facade.bean;

import java.io.Serializable;

public class PessoaBean implements Serializable {

    private Long id;
    private String nome;

    public PessoaBean() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
