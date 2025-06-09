CREATE TABLE conta (
    id BIGINT NOT NULL AUTO_INCREMENT,
    numero BIGINT NOT NULL UNIQUE,
    agencia VARCHAR(20) NOT NULL,
    saldo DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    tipo VARCHAR(50) NOT NULL,
    data_de_abertura TIMESTAMP NOT NULL,
    cliente_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_conta_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;