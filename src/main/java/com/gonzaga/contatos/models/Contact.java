package com.gonzaga.contatos.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

// Mapeamento objetos no Banco de Dados
// Anotando esta classe como uma entidade com @Entity.
// Marcando a calsse com nome da tabela com @Table

@Getter
@Setter
@Entity
@Table(name = "contact")
public class Contact implements Serializable {

    // Anotando os valores da classe com os nomes da tabela.
    @Id // Definindo Primary Key (PK)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    // Referênciando nomes das colunas,
    // indicando nome das colunas, tamanho e se not null
    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "document", nullable = false)
    private Long document;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
    private List<Address> addresses;

    public Contact(){
        active = true;
    }
}
