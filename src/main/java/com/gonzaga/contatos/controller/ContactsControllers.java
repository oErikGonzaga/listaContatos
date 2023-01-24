package com.gonzaga.contatos.controller;

import com.gonzaga.contatos.models.Contact;
import com.gonzaga.contatos.services.ContactsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

// Entrada de informações, EndPoint (aonde recebemos as informações dos usuários)
@Slf4j
@RestController
@RequestMapping("contacts") // Adiciona um path fixo antecedente aos demais paths.
public class ContactsControllers {

    @Autowired // Injetando a classe ContatosServices, para instanciação da classe.
    ContactsService contactsService;
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
    public ResponseEntity<?> register(@RequestBody Contact contact,
                                      @RequestHeader String token) {
                                       /* Quando o paramêtro recebe o mesmo nome que o Header
                                        não necessidade de adicionar value */

        log.info("ContatosController.cadastrar init");
        var contactCreated = contactsService.register(contact);

        if (!TOKEN_ACCESS.equals(token)) {
            return ResponseEntity.status(401).build();
        }

        return Objects.isNull(contactCreated) ?
                ResponseEntity.badRequest().build() :
                ResponseEntity.ok(contactCreated);
    }

    @GetMapping
    public ResponseEntity<List<Contact>> list(@RequestParam(value = "ativo", required = false) Boolean ativo,
                                              @RequestHeader(value = "Token") String token){

       log.info("ContatosController.listar init");
       if (!TOKEN_ACCESS.equals(token)) {
           return ResponseEntity.status(401).build();
       }

       return ResponseEntity.ok(contactsService.list(ativo));
    }
    @GetMapping(value = "{id}")
    public  ResponseEntity<?> SearchById(@PathVariable String id,
                                         @RequestHeader(value = "Token") String token){

        log.info("ContatosController.buscarPorId init");
        var resp = contactsService.searchById(id);

        if (!TOKEN_ACCESS.equals(token)) {
            return ResponseEntity.status(401).build();
        }

        // Op. Ternário: se a resposta for nula, retorne um 404 se não retorne a resposta.

        return isNull(resp) ?
               ResponseEntity.notFound().build() :
               ResponseEntity.ok(resp);
    }

    @PatchMapping(value = "inativar/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> inactivate(@PathVariable String id,
                                        @RequestHeader(value = "Token") String token) {

        log.info("ContatosController.inativar init");
        boolean contatoInativado = contactsService.inactivate(id);

        if (!TOKEN_ACCESS.equals(token)) {
            return ResponseEntity.status(401).build();
        }
        return contatoInativado ?
               ResponseEntity.ok().build() :
               ResponseEntity.notFound().build();
    }

    @PatchMapping(value = "ativar/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> activate(@PathVariable String id,
                                      @RequestHeader(value = "Token") String token) {

        log.info("ContatosController.ativar init");
        boolean contatoAtivado = contactsService.activate(id);

        if (!TOKEN_ACCESS.equals(token)) {
            return ResponseEntity.status(401).build();
        }
        return contatoAtivado ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> delete(@PathVariable(value = "id") String id,
                                       @RequestHeader(value = "Token") String token) {

        log.info("ContatosController.deletar init");
        var contato = contactsService.delete(id);

        if (!TOKEN_ACCESS.equals(token)) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "atualizar/{id}")
    public ResponseEntity<?> update(@PathVariable String id,
                                    @RequestHeader(value = "Token") String token,
                                    @RequestParam(value = "nome", required = false) String nome,
                                    @RequestParam(value = "documento", required = false) String documento) {

        log.info("ContatosController.atualizar init");
        var contato = contactsService.update(id, nome, documento);

        if (!TOKEN_ACCESS.equals(token)) {
            return ResponseEntity.status(401).build();
        }

        return contato ? ResponseEntity.ok(contato) : ResponseEntity.status(304).build();
    }
    @PostMapping("importar")
    public ResponseEntity<?> imports(@RequestBody Contact contact){
        return null;
    }

    @GetMapping(value = "exportar", produces = "application/json")
    public ResponseEntity<?> exports(@RequestParam(value = "ativo", required = false) Boolean ativo,
                                     @RequestHeader(value = "Token") String token) {

        log.info("ContatosController.listar init");

        if (!TOKEN_ACCESS.equals(token)) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(contactsService.list(ativo));
    }
}