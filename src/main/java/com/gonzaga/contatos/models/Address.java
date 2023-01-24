package com.gonzaga.contatos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    @Column(length = 8, nullable = false)
    private String cep;

    @Column(length = 150, nullable = false)
    private String address;

    @Column(length = 150)
    private String number;

    @Column(length = 30)
    private String complement;

    @Column(length = 30, nullable = false)
    private String district;

    @Column(length = 30, nullable = false)
    private String city;

    @Column(length = 2, nullable = false)
    private String uf;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Contact contact;
}
