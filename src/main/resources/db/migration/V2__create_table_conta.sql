CREATE TABLE conta (
  id BINARY(16) NOT NULL PRIMARY KEY,
  numero BIGINT,
  agencia VARCHAR(255),
  saldo DOUBLE,
  tipo VARCHAR(255),
  data_de_abertura TIMESTAMP,
  cliente_id BINARY(16),
  CONSTRAINT fk_conta_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);