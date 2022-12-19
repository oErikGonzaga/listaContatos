package com.gonzaga.contatos.controller;


import com.gonzaga.contatos.model.Contato;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("contatos") // Adiciona um path fixo antecedente aos demais paths.
public class ContatosController {

     private final List<Contato> contatos = new ArrayList<>();
     private static final String TOKEN_ACCESS = "BC6X8639be18b115a9";

     @GetMapping(value = "healthcheck")
     public String check() {
         return "App is Working";
     }

     @PostMapping
     public ResponseEntity<?> criar(@RequestBody Contato contato,
                                    @RequestHeader(value = "Authorization") String auth) {

        if (!auth.equals(TOKEN_ACCESS)){
            return ResponseEntity.badRequest().body("Token de Acesso Inválido");
        }

         contato.setId(UUID.randomUUID().toString());
         contatos.add(contato);
         return ResponseEntity.status(HttpStatus.CREATED).body(contato);
     }


     @GetMapping(value = "listar")
     public List<Contato> listar(){
         return contatos;
     }

     // Filtrando um contato pelo Id
     @GetMapping(value = "{id}")
     public  ResponseEntity<?> buscarPorId(@PathVariable String id,
                                 @RequestParam(value = "ativo", required = false, defaultValue = "true") boolean ativo){
         return contatos
                 .stream()
                 .filter(c -> c.getId().equals(id) && c.isAtivo() == ativo)
                 .findFirst()
                 .orElseThrow(() -> new RuntimeException("Contato nao encontrado"));
     }

     @PutMapping(value = "alterar/{id}")
     public ResponseEntity<Contato> alterar(@PathVariable String id,
                           @RequestParam(value = "nome", required = false) String nome,
                           @RequestParam(value = "email", required = false) String valor,
                           @RequestHeader(value = "Authorization") String auth) {

         if (!auth.equals(TOKEN_ACCESS)){
             throw new RuntimeException("Token de Acesso Inválido");
         }
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

         return ResponseEntity.status(HttpStatus.ACCEPTED).body(contato);
     }

     @DeleteMapping(value = "{id}")
     @ResponseStatus(HttpStatus.ACCEPTED)
     public ResponseEntity<Void> deletar(@PathVariable(value = "id") String idContato,
                                         @RequestHeader(value = "Authorization") String auth) {

        if (!auth.equals(TOKEN_ACCESS)){
            throw new RuntimeException("Token de Acesso Inválido");
        }

         var contato = contatos
                 .stream()
                 .filter(c -> c.getId().equals(idContato))
                 .findFirst()
                 .orElseThrow(() -> new RuntimeException("Contato nao encontrado"));

         contatos.remove(contato);
         return ResponseEntity.noContent().build();
     }
//        List<Contato> contatos = this.contatos
//                .stream()
//                .filter(c -> c.getId().equals(idContato))
//                .collect(Collectors.toList());
//
//        this.contatos = contatos

    @PatchMapping(value = "inativar/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void inativar(@PathVariable String id,
                         @RequestHeader(value = "Authorization") String auth) {

        if (!auth.equals(TOKEN_ACCESS)){
            throw new RuntimeException("Token de Acesso Inválido");
        }
        var contato = contatos
                 .stream()
                 .filter(c -> c.getId().equals(id) && c.isAtivo())
                 .findFirst()
                 .orElseThrow(() -> new RuntimeException("Contato nao encontrado ou inativo"));

        contato.setAtivo(false);

    }
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

