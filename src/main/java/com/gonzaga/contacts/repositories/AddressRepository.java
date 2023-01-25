package com.gonzaga.contacts.repositories;

import com.gonzaga.contacts.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByContactId(String idContact);

}
