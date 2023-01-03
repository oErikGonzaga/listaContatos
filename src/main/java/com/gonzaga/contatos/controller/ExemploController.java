package com.gonzaga.contatos.controller;

import com.gonzaga.contatos.model.Contato;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
public class ExemploController {
    // A Lista será final pq ninguém deve altera-la
//    private final List<Contato> contatos = new ArrayList<>();
//
//    // Param que será adicionado ao nosso endereço: https://localhost:8080/contatos
//    // @ RequestBody recebe o corpo da requisição, nosso arquivo JSON,
//    // e lança na variável contato. Caso o Body esteja vazio, receberemos um erro 400
//
//    @PostMapping("contato")
//    public Contato criar(@RequestBody Contato contato){
//        // 1.º Gerando um ID Aleatório com UUID
//        String id = UUID.randomUUID().toString();
//        // 2.º - passando o id para dentro de contato (contato <- recebe id)
//        contato.setId(id);
//        // 3.º passando o body, mais o ID gerado para lista de contatos
//        contatos.add(contato);
//        return contato;
//    }

}
