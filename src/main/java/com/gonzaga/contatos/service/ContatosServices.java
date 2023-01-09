package com.gonzaga.contatos.service;

import com.gonzaga.contatos.model.Contato;

import java.util.List;

public interface ContatosServices {

    List<Contato> listar();
    Contato cadastrar(Contato contato);
    Contato buscarPorId(String id);
    boolean inativar(String id);
    boolean ativar(String id);
    Contato deletar(String id);
    Contato atualizar(String id, String nome, String documento);
}
