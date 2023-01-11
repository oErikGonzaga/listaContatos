package com.gonzaga.contatos.controllerV2;

import com.gonzaga.contatos.modelV2.ContatosV2;

import com.gonzaga.contatos.serviceV2.ContatosServiceV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RestController
@RequestMapping(value = "contatos/v2")
public class ContatosV2Controller {

    private final List<ContatosV2> listaContatos = new ArrayList<>();

    ContatosServiceV2 contatosServiceV2 = new ContatosServiceV2();

    private static final String TOKEN_ACCESS = "BC6X8639be18b115a9";

    /* Chave de Acesso, comparada via Header
    (em exemplo: uma chave fica com o cliente e outra no sistema
    para comparação e liberação de acesso) */

    @GetMapping(value= "healthcheckv2")
    public ResponseEntity<String> checkStatus(){

        // Checkando conexão com o EndPoint

        String result = "App Contatos, Ok";
        return ResponseEntity.ok(result);
    }

    @PostMapping("cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ContatosV2 contatosV2,
                                       @RequestHeader(value = "Token") String token){

        //  ResponseEntity passando o número do StatusCode com Body como resposta

        var contatoCriado = contatosServiceV2.cadastrar(contatosV2);

        if (!TOKEN_ACCESS.equals(token))
            return ResponseEntity.status(400).body("Token de acesso inválido");

        return Objects.isNull(contatoCriado) ?
                ResponseEntity.status(404).body("Contato já existe") :
                ResponseEntity.status(200).body(contatoCriado);
    }

    @GetMapping("listar")
    public List<ContatosV2> listar(@RequestParam(value = "ativo", required = false) Boolean ativo){

        return contatosServiceV2.listar();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id){

        var contatoEncontrado = contatosServiceV2.buscarPorId(id);

        //  ResponseEntity passando o nome do StatusCode com Body e Build como resposta

        return (Objects.nonNull(contatoEncontrado)) ?
                ResponseEntity.accepted().body(contatoEncontrado) :
                ResponseEntity.notFound().build();
    }

    @PatchMapping("inativar/{id}")
    public ResponseEntity<?> inativar(@PathVariable String id){

        var contatoInativado = contatosServiceV2.inativar(id);

        return isNull(contatoInativado) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok().build();
    }

    @PatchMapping("ativar{id}")
    public ResponseEntity<?> ativar(@PathVariable String id){

        var contatoAtivado = contatosServiceV2.ativar(id);

        return isNull(contatoAtivado) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable String id){

        var contatoDeletado = contatosServiceV2.deletar(id);

        return isNull(contatoDeletado) ?
                ResponseEntity.status(404).body("Contato não Encontrado") :
                ResponseEntity.status(201).body("Contato Deletado");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizar(@PathVariable String id,
                                       @RequestParam(value = "nome", required = false) String nome,
                                       @RequestParam(value = "documento", required = false) String documento){

        // RequestParam para receber os dados dos campos Value nas variáveis do Parametro.

        var contatoAlterado = contatosServiceV2.atualizar(id, nome, documento);

        return isNull(contatoAlterado) ?
                ResponseEntity.status(404).body("Contato não encontrado") :
                ResponseEntity.status(201).body(contatoAlterado);
    }


}
