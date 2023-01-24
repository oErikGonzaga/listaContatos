package com.gonzaga.contatos.services.impl;

import com.gonzaga.contatos.models.Address;
import com.gonzaga.contatos.repositories.AddressRepository;
import com.gonzaga.contatos.repositories.ContactsRepository;
import com.gonzaga.contatos.services.AddressService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class AddressServiceImpl implements AddressService {


    AddressRepository addressRepository;
    ContactsRepository contactsRepository;

    @Override
    public Address create(Address address, String idContact) {
        return null;
    }

    @Override
    public List<Address> list(String idContact) {

        var contact = contactsRepository.findById(idContact).orElseThrow();

        return addressRepository.findAllByContactId(contact.getId());
    }
}
