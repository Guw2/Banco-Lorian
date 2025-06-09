ALTER TABLE transacao
ADD COLUMN conta_destino_id BIGINT NOT NULL,
ADD CONSTRAINT fk_conta_destino FOREIGN KEY (conta_destino_id) REFERENCES conta(id);