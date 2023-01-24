package com.gonzaga.contatos.controller;

import com.gonzaga.contatos.models.Address;
import com.gonzaga.contatos.services.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("contacts/")
public class AddressController {

    @Autowired(required = true)
    AddressService addressService;

    private static final String TOKEN_ACCESS = "BC6X8639be18b115a9";

    @PostMapping("{idContact}/address")
    public ResponseEntity<?> create(@PathVariable String idContact,
                                    @RequestBody Address address){

        var addressCreated = addressService.create(address, idContact);

        return Objects.isNull(addressCreated) ?
               ResponseEntity.badRequest().build() :
               ResponseEntity.ok(addressCreated);
    }

    @GetMapping("{idContact}/address")
    public ResponseEntity<List<Address>> list(@PathVariable String idContact){
        return ResponseEntity.ok(addressService.list(idContact));
    }
}
