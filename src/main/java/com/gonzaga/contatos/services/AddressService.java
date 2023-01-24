package com.gonzaga.contatos.services;

import com.gonzaga.contatos.models.Address;

import java.util.List;

public interface AddressService {

    Address create(Address address, String idContact);
    List<Address> list(String idContact);
}