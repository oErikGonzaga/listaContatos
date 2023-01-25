package com.gonzaga.contacts.v2.serviceV2.modelV2;

import lombok.Data;
@Data
public class ContatosV2 {

    private String id;
    private String nome;
    private boolean ativo = true;
    private Long documento;

}
