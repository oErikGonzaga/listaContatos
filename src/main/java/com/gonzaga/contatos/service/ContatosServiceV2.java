package com.gonzaga.contatos.service;

import com.gonzaga.contatos.model.ContatosV2;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class ContatosServiceV2 {

    private final List<ContatosV2> listaContatos = new ArrayList<>();

    public ContatosV2 cadastrar(ContatosV2 contatosV2){

        contatosV2.setId(UUID.randomUUID().toString());
        listaContatos.add(contatosV2);

        return contatosV2;

    }
    public List<ContatosV2> listar(){
        return listaContatos;
    }
    public ContatosV2 buscarPorId(String id){

        for (ContatosV2 c : listaContatos) {
            if (id.equals(c.getId()) && c.isAtivo()){
                return c;
            }
        }
        System.out.println("Não encontrado");
        return null;
    }
   public void alterar(){


    }
    public void deletar(){

    }

    public ContatosV2 inativar(String id){

        for (var c : listaContatos) {
            if ((id.equals(c.getId()) && (c.isAtivo()))) {
                c.setAtivo(false);
            }
        }
        return null;
    }
}
