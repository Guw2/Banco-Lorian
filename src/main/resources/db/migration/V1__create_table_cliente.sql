CREATE TABLE cliente (
    id BINARY(16) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    idade INT,
    endereco VARCHAR(255),
    telefone VARCHAR(20),
    email VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
