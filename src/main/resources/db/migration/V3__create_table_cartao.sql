CREATE TABLE cartao (
    id BIGINT NOT NULL AUTO_INCREMENT,
    numero VARCHAR(20) NOT NULL UNIQUE,
    cvv SMALLINT NOT NULL,
    validade DATE NOT NULL,
    limite DECIMAL(15,2) NOT NULL,
    bandeira VARCHAR(50),
    cliente_id BIGINT NOT NULL,
    conta_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_cartao_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    CONSTRAINT fk_cartao_conta FOREIGN KEY (conta_id) REFERENCES conta(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;