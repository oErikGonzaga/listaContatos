package com.gonzaga.contacts.clients;

import com.gonzaga.contacts.responses.AddressResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Optional;

@RestController
public class ViaCepClient implements Serializable {

    public Optional<AddressResponse> getAddressByCep(String cep) {

        RestTemplate restTemplate = new RestTemplate();

        String URL = String.format("https://viacep.com.br/ws/%s/json/", cep);

        ResponseEntity<AddressResponse> response = restTemplate.getForEntity(URL, AddressResponse.class);

        if (response.getBody() != null && response.getStatusCode() == HttpStatus.OK) {
            return Optional.of(response.getBody());
        }

        return Optional.empty();
    }

}