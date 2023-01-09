CREATE DATABASE contatos_db;

USE contatos_db;

CREATE TABLE contato(
    id          VARCHAR(255)    NOT NULL    PRIMARY KEY,
    nome        VARCHAR(255)    NOT NULL               ,
    documento   VARCHAR(255)    NOT NULL               ,
    ativo       VARCHAR(255)    NOT NULL
);

SELECT * FROM contato;

INSERT INTO contato VALUES('', '', '', true);