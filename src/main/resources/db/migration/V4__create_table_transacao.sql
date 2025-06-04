CREATE TABLE transacao (
    id BINARY(16) PRIMARY KEY NOT NULL,
    valor DOUBLE NOT NULL,
    data TIMESTAMP NOT NULL,
    tipo VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    conta_id BINARY(16) NOT NULL,

    CONSTRAINT fk_transacao_conta FOREIGN KEY (conta_id) REFERENCES conta(id)
);