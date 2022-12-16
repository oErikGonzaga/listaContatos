package com.gonzaga.contatos.controller;


import com.gonzaga.contatos.model.Contato;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class ContatosController {

     private List<Contato> contatos = new ArrayList<>();

    @GetMapping(value = "healthcheck")
    public String check() {
        return "App is Working";
    }

     @PostMapping(value = "contatos")
     @ResponseStatus(HttpStatus.CREATED)
     public Contato criar(@RequestBody Contato contato) {
         contato.setId(UUID.randomUUID().toString());
         contatos.add(contato);
         return contato;
     }

     @GetMapping(value = "listar-contatos")
     public List<Contato> listar(){
         return contatos;
     }

     @GetMapping(value = "contatos/{id}")
     // Filtrando um contato pelo Id
     public  Contato buscarPorId(@PathVariable String id,
                                 @RequestParam(value = "ativo",
                                         required = false, defaultValue = "true") boolean ativo){
         return contatos
                 .stream()
                 .filter(c -> c.getId().equals(id))
                 .findFirst()
                 .orElseThrow(() -> new RuntimeException("Contato nao encontrado"));
     }

     @PutMapping(value = "alterar/{id}")
    public Contato alterar(@PathVariable String id){
        return null;
     }
}