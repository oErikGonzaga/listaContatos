package com.gonzaga.contacts.responses;

import lombok.Data;
import java.io.Serializable;

@Data
public class AddressResponse implements Serializable {

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String estado;
    private boolean erro;
}