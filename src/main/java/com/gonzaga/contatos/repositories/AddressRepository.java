package com.gonzaga.contatos.repositories;

import com.gonzaga.contatos.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByContactId(String idContact);

}
