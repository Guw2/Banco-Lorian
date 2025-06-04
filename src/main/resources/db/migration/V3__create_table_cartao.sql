CREATE TABLE cartao (
    id BINARY(16) PRIMARY KEY NOT NULL,
    numero VARCHAR(255) NOT NULL,
    cvv INT NOT NULL,
    validade TIMESTAMP NOT NULL,
    limite DOUBLE NOT NULL,
    bandeira VARCHAR(255),
    cliente_id BINARY(16) NOT NULL,
    conta_id BINARY(16) NOT NULL,

    CONSTRAINT fk_cartao_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    CONSTRAINT fk_cartao_conta FOREIGN KEY (conta_id) REFERENCES conta(id)
);
