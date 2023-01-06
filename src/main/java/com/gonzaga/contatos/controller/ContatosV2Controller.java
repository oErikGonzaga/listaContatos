package com.gonzaga.contatos.controller;

import com.gonzaga.contatos.model.ContatosV2;

import com.gonzaga.contatos.service.ContatosServiceV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RestController
@RequestMapping(value = "contatos/v2")
public class ContatosV2Controller {

    private final List<ContatosV2> listaContatos = new ArrayList<>();


    ContatosServiceV2 contatosServiceV2 = new ContatosServiceV2();
    @GetMapping(value= "healthcheckv2")
    public ResponseEntity<String> checkStatus(){
        String result = "App Contatos, Ok";
        return ResponseEntity.ok(result);
    }


    @PostMapping("cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ContatosV2 contatosV2){

        var contatoCriado = contatosServiceV2.cadastrar(contatosV2);

        return ResponseEntity.status(200).body(contatoCriado);
    }


    @GetMapping("listar")
    public List<ContatosV2> listar(){
        return contatosServiceV2.listar();
    }
    @GetMapping("{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id){

        var contatoEncontrado = contatosServiceV2.buscarPorId(id);

        return isNull(contatoEncontrado) ?
                ResponseEntity.status(404).body("Contato não Encontrado"):
                ResponseEntity.ok(contatoEncontrado);
    }


    @PatchMapping("{id}")
    public ResponseEntity<?> inativarPorId(@PathVariable String id){

        var contatoInativado = contatosServiceV2.inativar(id);

        return nonNull(contatoInativado) ?
                ResponseEntity.ok(contatoInativado) :
                ResponseEntity.status(201).body("Contato inativado com Sucesso");
    }


    @PutMapping("{id}")
    public ResponseEntity<?> alterarContato(@PathVariable String id,
                                            @RequestParam(value = "nome", required = false) String nome,
                                            @RequestParam(value = "documento", required = false) String documento){

        var contatoAlterado = contatosServiceV2.alterar(id, nome, documento);

        return isNull(contatoAlterado) ?
                ResponseEntity.status(404).body("Contato não encontrado") :
                ResponseEntity.status(201).body(contatoAlterado);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable String id){

        var contatoDeletado = contatosServiceV2.deletar(id);

        return isNull(contatoDeletado) ?
                ResponseEntity.status(404).body("Contato não Encontrado") :
                ResponseEntity.status(201).body("Contato Deletado");
    }
}
