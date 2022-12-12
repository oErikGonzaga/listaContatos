package com.gonzaga.contatos.model;

import lombok.Data;

@Data
public class Contato {

    private String id;
    private String nome;
    private String tipo;
    private String valor;
}
