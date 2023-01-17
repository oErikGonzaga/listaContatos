CREATE DATABASE contatos_db;

USE contatos_db;

CREATE TABLE contato(
    id          VARCHAR(255)    NOT NULL    PRIMARY KEY,
    nome        VARCHAR(255)    NOT NULL               ,
    ativo       BOOLEAN         NOT NULL               ,
    documento   LONG            NOT NULL
);

CREATE TABLE endereco(
    id          INT             NOT NULL    AUTO_INCREMENT  ,
    cep         VARCHAR(8)      NOT NULL                    ,
    logradouro  VARCHAR(150)    NOT NULL                    ,
    complemento VARCHAR(30)     NOT NULL                    ,
    numero      VARCHAR(150)    NOT NULL                    ,
    cidade      VARCHAR(30)     NOT NULL                    ,
    estado      VARCHAR(2)      NOT NULL                    ,
    id_endereco INT             NOT NULL                    ,
    PRIMARY KEY	(id)									    ,
    FOREIGN KEY (address_id) REFERENCES address (id)
);


SELECT * FROM contato;

INSERT INTO contato VALUES('12345678900', 'Erik Gonzaga de Souza', '12345678900', true);
INSERT INTO endereco VALUES(1, '02542110', 'Rua Abura', 'A', '641', 'São Paulo', 'SP');