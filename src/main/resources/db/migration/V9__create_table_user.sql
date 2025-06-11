CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user VARCHAR(255) NOT NULL,
    pass VARCHAR(255) NOT NULL,
    role VARCHAR(255),
    cliente_id BIGINT,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;