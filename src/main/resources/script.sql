DROP DATABASE contacts_db;
CREATE DATABASE contacts_db;

USE contacts_db;

DROP TABLE contact ;
DROP TABLE address ;

SELECT * FROM contact c;
SELECT * FROM address a;


CREATE TABLE contact(
	id          VARCHAR(255)    PRIMARY KEY 				,
    name        VARCHAR(255)    NOT NULL                    ,
    active      BOOLEAN         NOT NULL                    ,
    document    LONG            NOT NULL
);

CREATE TABLE address(
	id              BIGINT          AUTO_INCREMENT PRIMARY KEY				,
    cep             VARCHAR(8)      NOT NULL                ,
    address         VARCHAR(150)    NOT NULL                ,
    number          VARCHAR(150)    NOT NULL                ,
    complement      VARCHAR(30)     NULL                    ,
    district        VARCHAR(30)     NOT NULL                ,
    city            VARCHAR(30)     NOT NULL                ,
    uf              VARCHAR(2)      NOT NULL                ,
    address_id      VARCHAR(255)  	NOT NULL  				,
    FOREIGN KEY	    (address_id)	REFERENCES contact (id)
);




INSERT INTO contato (id, nome, documento, ativo, id_endereco) VALUES(1, 'Erik Gonzaga de Souza', '12345678900', true, 1);
INSERT INTO address (cep, address, number, complement, district, city, uf, address_id)
VALUES('02465300', 'Avenida Imirim', '2383', 'Loja', 'Imirim', 'São Paulo', 'SP', 'fac22244-051b-45de-bafa-e4f58659bd20');



INSERT INTO endereco (cep, logradouro, complemento, numero, bairro, cidade, estado)
VALUES('02542110', 'Rua Abura', 'A', '641', 'Imirim', 'São Paulo', 'SP');


SELECT 	c.nome			,
		c.documento 	,
		e.cep			,
		e.logradouro	,
		e.complemento	,
		e.numero		,
		e.bairro		,
		e.cidade		,
		e.estado		FROM contato c INNER JOIN endereco e
		ON (e.id = c.id_endereco) WHERE e.id = 1;