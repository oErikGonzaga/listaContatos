CREATE DATABASE contatos_db;

USE contatos_db;

DROP TABLE contato ;
DROP TABLE endereco ;

CREATE TABLE endereco(
    id          LONG            NOT NULL    AUTO_INCREMENT  ,
    cep         VARCHAR(8)      NOT NULL                    ,
    logradouro  VARCHAR(150)    NOT NULL                    ,
    complemento VARCHAR(30)                                 ,
    numero      VARCHAR(150)    NOT NULL                    ,
    bairro      VARCHAR(30)     NOT NULL                    ,
    cidade      VARCHAR(30)     NOT NULL                    ,
    estado      VARCHAR(2)      NOT NULL                    ,
    PRIMARY KEY	(id)
);

CREATE TABLE contato(
    id          VARCHAR(255)	NOT NULL	PRIMARY KEY		,
    nome        VARCHAR(255)    NOT NULL                    ,
    ativo       BOOLEAN         NOT NULL                    ,
    documento   LONG            NOT NULL                    ,
    id_endereco INT    			NOT NULL  					,
    FOREIGN KEY (id_endereco)   REFERENCES  endereco (id)
);

SELECT * FROM contato;
SELECT * FROM endereco;