package com.gonzaga.contatos.controller;


import com.gonzaga.contatos.model.Contato;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
public class ContatosController {

     private final List<Contato> contatos = new ArrayList<>();

     private static final String TOKEN_ACCESS = "BC6X8639be18b115a9";

    @GetMapping(value = "healthcheck")
    public String check() {
        return "App is Working";
    }

     @PostMapping(value = "contatos")
     public ResponseEntity<?> criar(@RequestBody Contato contato,
                                    @RequestHeader(value = "Authorization") String auth) {

        if (!auth.equals(TOKEN_ACCESS)){
            return ResponseEntity.badRequest().body("Token de Acesso Inválido");
        }
         contato.setId(UUID.randomUUID().toString());
         contatos.add(contato);
         return ResponseEntity.status(HttpStatus.CREATED).body(contato);
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
                 .filter(c -> c.getId().equals(id) && c.isAtivo() == ativo)
                 .findFirst()
                 .orElseThrow(() -> new RuntimeException("Contato nao encontrado"));
     }

     @PutMapping(value = "alterar/{id}")
     public ResponseEntity<Contato> alterar(@PathVariable String id,
                           @RequestParam(value = "nome", required = false) String nome,
                           @RequestParam(value = "email", required = false) String valor){

         var contato = contatos
                 .stream()
                 .filter(c -> c.getId().equals(id))
                 .findFirst()
                 .orElseThrow(() -> new RuntimeException("Contato nao encontrado"));

         if (Objects.nonNull(nome)){
             contato.setNome(nome);
         }

         if (valor != null){
             contato.setEmail(valor);
         }

        return contato;
     }

     @DeleteMapping(value = "deletar/{id}")
     @ResponseStatus(HttpStatus.ACCEPTED)
     public void deletar(@PathVariable(value = "id") String idContato){

         var contato = contatos
                 .stream()
                 .filter(c -> c.getId().equals(idContato))
                 .findFirst()
                 .orElseThrow(() -> new RuntimeException("Contato nao encontrado"));

         contatos.remove(contato);

//        List<Contato> contatos = this.contatos
//                .stream()
//                .filter(c -> c.getId().equals(idContato))
//                .collect(Collectors.toList());
//
//        this.contatos = contatos;
     }

     @PatchMapping(value = "inativar/{id}")
     @ResponseStatus(HttpStatus.ACCEPTED)
    public void inativar(@PathVariable String id){

        var contato = contatos
                 .stream()
                 .filter(c -> c.getId().equals(id) && c.isAtivo())
                 .findFirst()
                 .orElseThrow(() -> new RuntimeException("Contato nao encontrado ou inativo"));

         contato.setAtivo(false);

    }

//        var contato = contatos
//                 .stream()
//                 .filter(c -> c.getId().equals(id))
//                 .findFirst()
//                 .orElseThrow(() -> new RuntimeException("Contato nao encontrado"));
//
//        if (contato.getAtivo() == false) {
//            throw new RuntimeException("Contato já está Inativo");
//        }
//
//        if (contato.getAtivo()) {
//            contato.setAtivo(false);
//        }

}