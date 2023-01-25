package com.gonzaga.contacts.services.impl;

import com.gonzaga.contacts.clients.ViaCepClient;
import com.gonzaga.contacts.models.Address;
import com.gonzaga.contacts.repositories.AddressRepository;
import com.gonzaga.contacts.repositories.ContactsRepository;
import com.gonzaga.contacts.services.AddressService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final ViaCepClient viaCepClient;
    private final AddressRepository addressRepository;
    private final ContactsRepository contactsRepository;

    @Override
    @Transactional
    public Address create(Address address, String idContact) {

        try {
            var addressViaCep = viaCepClient.getAddressByCep(address.getCep()).orElseThrow(() -> {
                throw new RuntimeException("Endereço não encontrado");
            });

            if (addressViaCep.isErro())
                throw new RuntimeException("Endereço não encontrado");

            var contact = contactsRepository.findById(idContact).orElseThrow();
            address.setContact(contact);

            if (StringUtils.isBlank(address.getAddress()))
                address.setAddress(addressViaCep.getLogradouro());

            if (StringUtils.isBlank(address.getDistrict()))
                address.setDistrict(addressViaCep.getBairro());

            if (StringUtils.isBlank(address.getCity()))
                address.setCity(addressViaCep.getLocalidade());

            if (StringUtils.isBlank(address.getUf()))
                address.setUf(addressViaCep.getEstado());

        } finally {

        }

        return addressRepository.save(address);
    }

    @Override
    public List<Address> list(String idContact) {

        var contact = contactsRepository.findById(idContact).orElseThrow();

        return addressRepository.findAllByContactId(contact.getId());
    }
}
