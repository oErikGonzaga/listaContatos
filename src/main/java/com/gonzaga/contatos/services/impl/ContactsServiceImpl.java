package com.gonzaga.contatos.services.impl;
import com.gonzaga.contatos.models.Contact;
import com.gonzaga.contatos.repositories.ContactsRepository;
import com.gonzaga.contatos.services.ContactsServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Service
public class ContactsServiceImpl implements ContactsServices {

    @Autowired
    private ContactsRepository contactsRepository;

    @Override
    public Contact register(Contact contact) {

        var contactFound = contactsRepository.findFirstByDocumento(contact.getDocument());

        if (Objects.nonNull(contactFound))
            return null;

        contact.setId(UUID.randomUUID().toString());
        return contactsRepository.save(contact);
    }

    @Override
    public List<Contact> list(Boolean active) {

        if (Objects.isNull(active))
            return contactsRepository.findAll();

        return contactsRepository.findAllByAtivo(active);
    }

    @Override
    public Contact searchById(String id) {
        return contactsRepository.findById(id).orElse(null);
    }

    @Override
    public boolean inactivate(String id) {

        var contact = searchById(id);

        if (Objects.isNull(contact)) return false;

        contact.setActive(false);
        contactsRepository.save(contact);
        return true;

    }

    @Override
    public boolean activate(String id) {

        var contact = searchById(id);

        if (Objects.nonNull(contact)){

            contact.setActive(true);
            contactsRepository.save(contact);
            return true;
        }

        return true;
    }

    @Override
    public boolean delete(String id) {

        var contact = searchById(id);

        if (Objects.isNull(contact)) return false;

        contactsRepository.deleteById(contact.getId());

        return true;
    }

    @Override
    public boolean update(String id, String name, String document) {

        var contact = searchById(id);

        if (Objects.isNull(contact)) return false;

        boolean isUpdated = false;

        if (Objects.nonNull(name) && !("".equals(name)) && !(name.equals(contact.getName()))) {
            contact.setName(name);
            isUpdated = true;
        }

        if (Objects.nonNull(document) && !(document.equals(contact.getDocument()))) {
            contact.setDocument(Long.valueOf(document));
            isUpdated = true;
        }

        if (isUpdated = true) contactsRepository.save(contact);

        return isUpdated;
    }

    @Override
    public List<Contact> imports(Contact contact) {

        return null;
    }

}
