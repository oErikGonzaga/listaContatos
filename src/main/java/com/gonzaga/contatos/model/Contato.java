package com.gonzaga.contatos.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Contato {

    private String id;
    private String nome;
    private String tipo;
    private String valor;
}
