package com.gonzaga.contatos.model;

import lombok.Data;

@Data
public class Contato {

    private String id;
    private boolean ativo;
    private String nome;
    private Long documento;
    public Contato(){
        ativo = true;
    }
}
