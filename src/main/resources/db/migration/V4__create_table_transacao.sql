CREATE TABLE transacao (
    id BINARY(16) NOT NULL,
    valor DECIMAL(15,2) NOT NULL,
    data TIMESTAMP NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    descricao VARCHAR(255),
    conta_id BINARY(16) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_transacao_conta FOREIGN KEY (conta_id) REFERENCES conta(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;