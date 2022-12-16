package com.gonzaga.contatos.model;

import lombok.Data;

@Data
public class Contato {

    private String id;
    private boolean ativo;
    private String nome;
    private String email;

    public Contato(){
        ativo = true;
    }
}
