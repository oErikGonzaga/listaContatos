package com.gonzaga.contatos.serviceV2;

import com.gonzaga.contatos.modelV2.ContatosV2;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Data
public class ContatosServiceV2 {

    private final List<ContatosV2> listaContatos = new ArrayList<>();

    public List<ContatosV2> listar(){
        return listaContatos;
    }

    public ContatosV2 cadastrar(ContatosV2 contatosV2){

        /*  Verificando se o contato já existe.
            Se documento existir no banco, será retornado Null.
            Se não, um contato será criado.
        */

        for (ContatosV2 c : listaContatos) {
            if (c.getDocumento().equals(contatosV2.getDocumento())){
                return null;
            }
        }
        contatosV2.setId(UUID.randomUUID().toString());
        listaContatos.add(contatosV2);
        return contatosV2;
    }

    public ContatosV2 buscarPorId(String id){

        /*  Buscando um contato pelo ID gerado no cadastro;
            Caso o ID seja igual ao do banco e ele esteja ATIVO será retornado o contato.
        */

        for (ContatosV2 c : listaContatos) {
            if (id.equals(c.getId()) && c.isAtivo()){
                return c;
            }
        }
        return null;
    }

    public boolean inativar(String id){

        for (ContatosV2 c : listaContatos) {
            if ((id.equals(c.getId()) && (c.isAtivo()))) {
                c.setAtivo(false);
                return true;
            }
        }
        return false;
    }

    public ContatosV2 deletar(String id){

        listaContatos.removeIf(c -> c.getId().equals(id) && c.isAtivo());
        return null;
    }

    public ContatosV2 alterar(String id,String nome, String documento){

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
        return null;
    }
}
