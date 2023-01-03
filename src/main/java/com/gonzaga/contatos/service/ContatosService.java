package com.gonzaga.contatos.service;

import com.gonzaga.contatos.model.Contato;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class ContatosService {

    private final List<Contato> contatos = new ArrayList<>();

    public Contato cadastrar(Contato contato){

        contato.setId(UUID.randomUUID().toString());
        contatos.add(contato);
        return contato;
    }
    public List<Contato> listar(){
        return contatos;
    }
    public Contato buscarPorId(String id){

        var resp = contatos.stream()
                // Garantindo que não vou tomar um NullPointer (invertendo a lógica)
                .filter(c -> id.equals(c.getId()))
                .findFirst()
                .orElse(null);

        return resp;
    }
    public Contato alterar(String id){

        var contato = contatos
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Contato nao encontrado"));

        return contato;
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

        var contato = contatos
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Contato nao encontrado"));

        contatos.remove(contato);

        return contato;
    }
}
