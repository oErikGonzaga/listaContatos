package com.gonzaga.contatos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

// Mapeamento objetos no Banco de Dados
// Anotando esta classe como uma entidade com @Entity.
// Marcando a calsse com nome da tabela com @Table

@Getter
@Setter
@Entity
@Table(name = "contato")
public class Contato implements Serializable {

    // Anotando os valores da classe com os nomes da tabela.
    @Id // Definindo Primary Key (PK)
    private String id;

    @Column(name = "nome", nullable = false)
    private String nome;

    // Referênciando nomes das colunas,
    // indicando nome das colunas, tamanho e se not null
    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @Column(name = "documento", nullable = false)
    private Long documento;

    public Contato(){
        ativo = true;
    }
}
