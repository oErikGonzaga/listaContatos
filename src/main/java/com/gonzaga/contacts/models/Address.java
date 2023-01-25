package com.gonzaga.contacts.models;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 8, nullable = false)
    private String cep;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String number;

    private String complement;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String uf;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Contact contact;
}
