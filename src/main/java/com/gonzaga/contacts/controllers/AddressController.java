package com.gonzaga.contacts.controllers;

import com.gonzaga.contacts.models.Address;
import com.gonzaga.contacts.services.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("contacts")
public class AddressController {

    private final AddressService addressService;

    private static final String TOKEN_ACCESS = "BC6X8639be18b115a9";

    @PostMapping("{idContact}/address")
    public ResponseEntity<Address> create(@PathVariable String idContact,
                                          @RequestHeader(value = "Token") String token,
                                          @RequestBody Address address){

        log.info("AddressController.create init");

        if (!TOKEN_ACCESS.equals(token))
            return ResponseEntity.status(401).build();

        var addressCreated = addressService.create(address, idContact);

        return Objects.isNull(addressCreated) ?
               ResponseEntity.badRequest().build() :
               ResponseEntity.ok(addressCreated);
    }

    @GetMapping("{idContact}/address")
    public ResponseEntity<List<Address>> list(@PathVariable String idContact,
                                              @RequestHeader(value = "Token") String token){

        log.info("AddressController.list init");

        if (!TOKEN_ACCESS.equals(token))
            return ResponseEntity.status(401).build();

        return ResponseEntity.ok(addressService.list(idContact));
    }
}
