CREATE TABLE cliente (
    id BINARY(16) PRIMARY KEY,
    nome VARCHAR(255),
    cpf VARCHAR(14),
    idade INT,
    endereco VARCHAR(255),
    telefone VARCHAR(20),
    email VARCHAR(255)
);