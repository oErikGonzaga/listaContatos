package com.gonzaga.contatos.services;

import com.gonzaga.contatos.models.Contact;

import java.util.List;

public interface ContactsService {

    List<Contact> list(Boolean active);
    Contact register(Contact contact);
    Contact searchById(String id);
    boolean inactivate(String id);
    boolean activate(String id);
    boolean delete(String id);
    boolean update(String id, String name, String document);
    List<Contact> imports(Contact contact);
}
