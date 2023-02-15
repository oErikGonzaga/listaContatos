package com.gonzaga.contacts.models;

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

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private String document;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
    private List<Address> address;

    public Contact(){
        active = true;
    }
}
