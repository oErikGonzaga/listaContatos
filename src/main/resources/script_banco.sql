CREATE DATABASE contatos_db;

USE contatos_db;

CREATE TABLE contato(
    id          VARCHAR(255)    NOT NULL    PRIMARY KEY,
    nome        VARCHAR(255)    NOT NULL               ,
    ativo       BOOLEAN         NOT NULL               ,
    documento   LONG            NOT NULL
);

SELECT * FROM contato;

INSERT INTO contato VALUES('12345678900', 'Erik Gonzaga de Souza', '12345678900', true);