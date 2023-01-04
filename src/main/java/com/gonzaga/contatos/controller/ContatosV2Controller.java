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

        for (var c : listaContatos) {
            if ((id.equals(c.getId()) && (c.isAtivo()) &&
                !(c.equals(nome)) && (nonNull(nome)))) {
                c.setNome(nome);
            }
            if ((id.equals(c.getId()) && (c.isAtivo()) &&
                !(c.equals(documento)) && c.getDocumento() != null)) {
                c.setDocumento(Long.valueOf(documento));
            }
            return ResponseEntity.status(201).body(c);
        }
        return ResponseEntity.status(404).body("Contato não encontrado");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable String id){

        for (ContatosV2 c : listaContatos) {
            if (c.getId().equals(id) && c.isAtivo()){
                listaContatos.remove(c);
                System.out.println("Apagou");
            }
            return ResponseEntity.status(201).body("Contato apagado com Sucesso");
        }
        return ResponseEntity.status(404).body("Contato não Encontrado");
    }
}
