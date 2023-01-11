package com.gonzaga.contatos.controller;

import com.gonzaga.contatos.model.Contato;
import com.gonzaga.contatos.service.ContatosServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired // Injetando a classe ContatosServices, para instanciação da classe.
    ContatosServices contatosService;
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
                                       @RequestHeader String token) {
                                       /* Quando o paramêtro recebe o mesmo nome que o Header
                                        não necessidade de adicionar value */

        log.info("ContatosController.cadastrar init");

        var contatoCriado = contatosService.cadastrar(contato);

        if (!TOKEN_ACCESS.equals(token)) {
             return ResponseEntity.badRequest().build();
        }

        return Objects.isNull(contatoCriado) ?
                ResponseEntity.badRequest().build() :
                ResponseEntity.ok(contatoCriado);
    }

    @GetMapping
    public ResponseEntity<List<Contato>> listar(@RequestHeader(value = "Token") String token){

         log.info("ContatosController.listar init");

         if (!TOKEN_ACCESS.equals(token)) {
             return ResponseEntity.badRequest().build();
         }
         return ResponseEntity.ok(contatosService.listar());
     }

    // Filtrando um contato pelo Id
    @GetMapping(value = "{id}")
    public  ResponseEntity<?> buscarPorId(@PathVariable String id,
                                          @RequestHeader(value = "Token") String token){

        log.info("ContatosController.buscarPorId init");
        var resp = contatosService.buscarPorId(id);

        if (!TOKEN_ACCESS.equals(token)) {
            return ResponseEntity.badRequest().build();
        }

        // Op. Ternário: se a resposta for nula, retorne um 404 se não retorne a resposta.

        return isNull(resp) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(resp);
    }

    @PatchMapping(value = "inativar/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> inativar(@PathVariable String id,
                                      @RequestHeader(value = "Token") String token) {

        log.info("ContatosController.inativar init");

        boolean contatoInativado = contatosService.inativar(id);

        if (!TOKEN_ACCESS.equals(token)) {
            return ResponseEntity.badRequest().build();
        }
        return contatoInativado ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @PatchMapping(value = "ativar/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> ativar(@PathVariable String id,
                                    @RequestHeader(value = "Token") String token) {

        log.info("ContatosController.ativar init");

        boolean contatoAtivado = contatosService.ativar(id);

        if (!TOKEN_ACCESS.equals(token)) {
            return ResponseEntity.badRequest().build();
        }
        return contatoAtivado ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") String id,
                                        @RequestHeader(value = "Token") String token) {

        log.info("ContatosController.deletar init");

        var contato = contatosService.deletar(id);

        if (!TOKEN_ACCESS.equals(token)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "atualizar/{id}")
    public ResponseEntity<Contato> atualizar(@PathVariable String id,
                                       @RequestHeader(value = "Token") String token,
                                       @RequestParam(value = "nome", required = false) String nome,
                                       @RequestParam(value = "documento", required = false) String documento) {

        log.info("ContatosController.atualizar init");

        var contato = contatosService.atualizar(id, nome, documento);

        if (!TOKEN_ACCESS.equals(token)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(contato);
    }
}