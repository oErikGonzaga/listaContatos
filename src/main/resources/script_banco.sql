CREATE DATABASE contacts_db;

USE contacts_db;

DROP TABLE contact ;
DROP TABLE address ;

CREATE TABLE contact(
	id          BIGINT          PRIMARY KEY                 ,
    name        VARCHAR(255)    NOT NULL                    ,
    active      BOOLEAN         NOT NULL                    ,
    document    LONG            NOT NULL
);

CREATE TABLE address(
	id              BIGINT          AUTO_INCREMENT 	PRIMARY KEY	,
    cep             VARCHAR(8)      NOT NULL                    ,
    address         VARCHAR(150)    NOT NULL                    ,
    number          VARCHAR(150)    NULL                        ,
    complement      VARCHAR(30)     NULL                        ,
    district        VARCHAR(30)     NOT NULL                    ,
    city            VARCHAR(30)     NOT NULL                    ,
    uf              VARCHAR(2)      NOT NULL                    ,
    address_id      BIGINT    		NOT NULL  					,
    FOREIGN KEY	    (address_id)	REFERENCES contact (id)
);

SELECT * FROM contact ;
SELECT * FROM address ;