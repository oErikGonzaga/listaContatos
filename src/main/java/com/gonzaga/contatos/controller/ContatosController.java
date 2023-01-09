package com.gonzaga.contatos.controller;

import com.gonzaga.contatos.model.Contato;
import com.gonzaga.contatos.service.ContatosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

// Entrada de informações, EndPoint (aonde recebemos as informações dos usuários)
@Slf4j
@RestController
@RequestMapping("contatos") // Adiciona um path fixo antecedente aos demais paths.
public class ContatosController {

    ContatosService contatosService = new ContatosService();
    private static final String TOKEN_ACCESS = "BC6X8639be18b115a9";

    /* Chave de Acesso, comparada via Header
    (em exemplo: uma chave fica com o cliente e outra no sistema
    para comparação e liberação de acesso) */

    @GetMapping(value = "check")
    public ResponseEntity<String> checkStatus() {

        // Checkando conexão com o EndPoint

        String check = "App Contatos está Ok";
        return ResponseEntity.ok(check);
    }

    @PostMapping()
    public ResponseEntity<?> cadastrar(@RequestBody Contato contato,
                                       @RequestHeader(value = "Token") String token) {

         /* Garantindo que não seja gerado um nullPointer
         ao inves de if (!auth.equals(TOKEN_ACCESS))
         Certificamas qual variavel / Objeto contém um valor
         e priorizamos-lá na ordem de acesso */

         var contatoCriado = contatosService.cadastrar(contato);

         if (!TOKEN_ACCESS.equals(auth)){
             return ResponseEntity.badRequest().body("Token de Acesso Inválido");
         }

        return Objects.isNull(contatoCriado) ?
                ResponseEntity.badRequest().build() :
                ResponseEntity.ok(contatoCriado);
    }

     @GetMapping
     public List<Contato> listar(){
         return contatosService.listar();
     }


    // Filtrando um contato pelo Id
    @GetMapping(value = "{id}")
    public  ResponseEntity<?> buscarPorId(@PathVariable String id,
                                          @RequestHeader(value = "Token") String token){

         var resp = contatosService.buscarPorId(id);

         // Op. Ternário: se a resposta for nula, retorne um 404 se não retorne a resposta.
         return isNull(resp) ? ResponseEntity.status(404).build() : ResponseEntity.ok(resp);
     }

     @PutMapping(value = "alterar/{id}")
     public ResponseEntity<Contato> alterar(@PathVariable String id,
                                            @RequestHeader(value = "Authorization") String auth,
                                            @RequestParam(value = "nome", required = false) String nome,
                                            @RequestParam(value = "email", required = false) String valor) {

         var contato = contatosService.alterar(id);

         if (!TOKEN_ACCESS.equals(auth)){
             throw new RuntimeException("Token de Acesso Inválido");
         }

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
     public ResponseEntity<Void> deletar(@PathVariable(value = "id") String id,
                                         @RequestHeader(value = "Authorization") String auth) {

         var contato = contatosService.deletar(id);

         if (!TOKEN_ACCESS.equals(auth)){
            throw new RuntimeException("Token de Acesso Inválido");
         }
         return ResponseEntity.noContent().build();
     }

    @PatchMapping(value = "inativar/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> inativar(@PathVariable String id,
                         @RequestHeader(value = "Authorization") String auth) {

        boolean contatoInativado = contatosService.inativar(id);

        if (!TOKEN_ACCESS.equals(auth)){
            throw new RuntimeException("Token de Acesso Inválido");
        }
        return contatoInativado ? ResponseEntity.ok(201) : ResponseEntity.status(404).build();
    }
}