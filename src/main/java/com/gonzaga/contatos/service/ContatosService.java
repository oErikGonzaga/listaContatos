package com.gonzaga.contatos.service;
import com.gonzaga.contatos.model.Contato;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Data
public class ContatosService {

    private final List<Contato> contatos = new ArrayList<>();

    public List<Contato> listar(){
        return contatos;
    }
    public Contato cadastrar(Contato contato){


        for (Contato c : contatos) {
            if (c.getDocumento().equals(contato.getDocumento())){
                return null;
            }
        }
        contato.setId(UUID.randomUUID().toString());
        contatos.add(contato);
        return contato;
    }
    public Contato buscarPorId(String id){

        var resp = contatos.stream()
                .filter(c -> id.equals(c.getId()))
                .findFirst()
                .orElse(null);

        return resp;
    }
    public boolean inativar(String id){

        var contato = contatos
                .stream()
                .filter(c -> c.getId().equals(id) && c.isAtivo())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Contato nao encontrado ou inativo"));

        contato.setAtivo(false);

        System.out.println("Alterado");

        return true;
    }
    public Contato deletar(String id){

        contatos.removeIf(c -> c.getId().equals(id) && c.isAtivo());
        return null;
    }
    public Contato atualizar(String id, String nome, String documento){

        var contato = contatos
                .stream()
                .filter(c -> id.equals(c.getId()) && (c.isAtivo())
                        && !(c.equals(nome)) && (nonNull(nome))
                        && !(c.equals(documento)) && c.getDocumento() != null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Contato nao encontrado"));

        return contato;
    }
}
