package com.gonzaga.contatos.v2.serviceV2;

import com.gonzaga.contatos.v2.serviceV2.modelV2.ContatosV2;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

        /*  Anotando (Transformando) esta classe como (em)
            um Bean do Spring com @Service. */

@Data
@Slf4j
@Service
public class ContatosServiceV2 {

    private final List<ContatosV2> listaContatos = new ArrayList<>();

    public ContatosV2 cadastrar(ContatosV2 contatosV2){

        /*  Verificando se o contato já existe.
            Se documento existir no banco, será retornado Null.
            Se não, um contato será criado.
        */

        /*  Garantindo que não seja gerado um nullPointer
            ao inves de if (!auth.equals(TOKEN_ACCESS))
            Certificamas qual variavel / Objeto contém um valor
            e priorizamos-lá na ordem de acesso
        */

        for (ContatosV2 c : listaContatos) {
            if (c.getDocumento().equals(contatosV2.getDocumento())){
                return null;
            }
        }
        contatosV2.setId(UUID.randomUUID().toString());
        listaContatos.add(contatosV2);

        log.info("ContatosController.cadastrar end");

        return contatosV2;

    }

    public List<ContatosV2> listar(){

        // Retornando a Lista de Contatos

        log.info("ContatosController.listar end");

        return listaContatos;
    }

    public ContatosV2 buscarPorId(String id){

        /*  Buscando um contato pelo ID gerado no cadastro;
            Caso o ID seja igual ao do banco e ele esteja ATIVO será retornado o contato.
        */

        // Garantindo que não vou tomar um NullPointer (invertendo a lógica)

        for (ContatosV2 c : listaContatos) {
            if (id.equals(c.getId()) && c.isAtivo()){
                return c;
            }
        }
        log.info("ContatosController.buscarPorID end");

        return null;
    }

    public boolean inativar(String id){

        /*  Buscando um contato pelo ID gerado no cadastro;
            Caso o ID seja igual ao do banco e ele esteja ATIVO
            o contato será alterado para INATIVO.
        */

        for (ContatosV2 c : listaContatos) {
            if ((id.equals(c.getId()) && (c.isAtivo()))) {
                c.setAtivo(false);
                return true;
            }
        }

        log.info("ContatosController.inativar end");

        return false;
    }

    public boolean ativar(String id){

        /*  Buscando um contato pelo ID gerado no cadastro;
            Caso o ID seja igual ao do banco e ele esteja ATIVO
            o contato será alterado para INATIVO.
        */

        for (ContatosV2 c : listaContatos) {
            if ((c.getId().equals(id) && !c.isAtivo())) {
                c.setAtivo(true);
                return true;
            }
        }

        log.info("ContatosController.inativar end");

        return false;
    }

    public ContatosV2 deletar(String id){

        /*  Buscando um contato pelo ID gerado no cadastro;
            Caso o ID seja igual ao do banco e ele esteja ATIVO
            o contato será Deletado.
        */

        for (ContatosV2 c : listaContatos) {
            if (c.getId().equals(id) && c.isAtivo()) {
                listaContatos.remove(c);
            }
            return c;
        }

        log.info("ContatosController.deletar end");

        return null;
    }

    public ContatosV2 atualizar(String id, String nome, String documento){

        for (ContatosV2 c : listaContatos) {
            if ((id.equals(c.getId()) && (c.isAtivo()) &&
                    !(c.equals(nome)) && (nonNull(nome)))) {
                c.setNome(nome);
            }

            if ((id.equals(c.getId()) && (c.isAtivo()) &&
                    !(c.equals(documento)) && c.getDocumento() != null)) {
                c.setDocumento(Long.valueOf(documento));
            }
            return c;
        }

        log.info("ContatosController.atualizar end");

        return null;
    }
}