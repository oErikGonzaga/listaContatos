package com.gonzaga.contacts.services;

import com.gonzaga.contacts.models.Address;

import java.util.List;

public interface AddressService {

    Address create(Address address, String idContact);
    List<Address> list(String idContact);
}