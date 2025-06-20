CREATE TABLE transacao (
    id BIGINT NOT NULL AUTO_INCREMENT,
    valor DECIMAL(15,2) NOT NULL,
    data TIMESTAMP NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    descricao VARCHAR(255),
    conta_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_transacao_conta FOREIGN KEY (conta_id) REFERENCES conta(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;