package com.gonzaga.contacts.controllers;

import com.gonzaga.contacts.models.Contact;
import com.gonzaga.contacts.services.ContactsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

// Entrada de informações, EndPoint (aonde recebemos as informações dos usuários)
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("contacts") // Adiciona um path fixo antecedente aos demais paths.
public class ContactsControllers {

    private final ContactsService contactsService;
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

        log.info("ContatosController.register init");
        var contactCreated = contactsService.register(contact);

        if (!TOKEN_ACCESS.equals(token)) {
            return ResponseEntity.status(401).build();
        }

        return Objects.isNull(contactCreated) ?
                ResponseEntity.badRequest().build() :
                ResponseEntity.ok(contactCreated);
    }

    @GetMapping
    public ResponseEntity<List<Contact>> list(@RequestParam(value = "active", required = false) Boolean active,
                                              @RequestHeader(value = "Token") String token){

       log.info("ContatosController.listar init");
       if (!TOKEN_ACCESS.equals(token)) {
           return ResponseEntity.status(401).build();
       }

       return ResponseEntity.ok(contactsService.list(active));
    }
    @GetMapping(value = "{id}")
    public  ResponseEntity<?> searchById(@PathVariable String id,
                                         @RequestHeader(value = "Token") String token){

        log.info("ContatosController.searchById init");
        var resp = contactsService.searchById(id);

        if (!TOKEN_ACCESS.equals(token)) {
            return ResponseEntity.status(401).build();
        }

        // Op. Ternário: se a resposta for nula, retorne um 404 se não retorne a resposta.

        return isNull(resp) ?
               ResponseEntity.notFound().build() :
               ResponseEntity.ok(resp);
    }

    @PatchMapping(value = "inactivate/{id}")
    public ResponseEntity<?> inactivate(@PathVariable String id,
                                        @RequestHeader(value = "Token") String token) {

        log.info("ContatosController.inactivate init");
        boolean contactInactivated = contactsService.inactivate(id);

        if (!TOKEN_ACCESS.equals(token)) {
            return ResponseEntity.status(401).build();
        }
        return contactInactivated ?
               ResponseEntity.ok().build() :
               ResponseEntity.notFound().build();
    }

    @PatchMapping(value = "activate/{id}")
    public ResponseEntity<?> activate(@PathVariable String id,
                                      @RequestHeader(value = "Token") String token) {

        log.info("ContatosController.activate init");
        boolean contactActivated = contactsService.activate(id);

        if (!TOKEN_ACCESS.equals(token)) {
            return ResponseEntity.status(401).build();
        }
        return contactActivated ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") String id,
                                       @RequestHeader(value = "Token") String token) {

        log.info("ContatosController.delete init");
        var contato = contactsService.delete(id);

        if (!TOKEN_ACCESS.equals(token)) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<?> update(@PathVariable String id,
                                    @RequestHeader(value = "Token") String token,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "document", required = false) Long document) {

        log.info("ContatosController.update init");
        var contato = contactsService.update(id, name, document);

        if (!TOKEN_ACCESS.equals(token)) {
            return ResponseEntity.status(401).build();
        }

        return contato ? ResponseEntity.ok(true) : ResponseEntity.status(304).build();
    }
    @PostMapping("imports")
    public ResponseEntity<?> imports(@RequestBody Contact contact){
        return null;
    }

    @GetMapping(value = "exports", produces = "application/json")
    public ResponseEntity<?> exports(@RequestParam(value = "active", required = false) Boolean active,
                                     @RequestHeader(value = "Token") String token) {

        log.info("ContatosController.listar init");

        if (!TOKEN_ACCESS.equals(token)) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(contactsService.list(active));
    }
}