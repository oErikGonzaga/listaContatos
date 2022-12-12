package com.gonzaga.contatos.controller;


import com.gonzaga.contatos.model.Contato;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class ContatosController {

     private List<Contato> contatos = new ArrayList<>();

     @PostMapping(value = "contatos")
     public Contato criar(@RequestBody Contato contato) {
         contato.setId(UUID.randomUUID().toString());
         contatos.add(contato);
         return contato;
     }

     @GetMapping(value = "contatos")
     public List<Contato> listar(){
         return contatos;
     }

    @GetMapping(value = "healthcheck")
    public String check() {
        return "App is Working";
    }



}
