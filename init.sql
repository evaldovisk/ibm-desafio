CREATE TABLE IF NOT EXISTS Cliente (
                                       id SERIAL PRIMARY KEY,
                                       nome VARCHAR(100) NOT NULL,
    idade INT NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
    );

CREATE TABLE IF NOT EXISTS Conta (
                                     id SERIAL PRIMARY KEY,
                                     numero_conta VARCHAR(20) UNIQUE NOT NULL,
    saldo NUMERIC(15, 2) DEFAULT 0.00,
    id_cliente INT UNIQUE REFERENCES Cliente(id) ON DELETE CASCADE,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS Transacao (
                                         id SERIAL PRIMARY KEY,
                                         id_origem INT REFERENCES Conta(id) ON DELETE CASCADE,
    id_destino INT REFERENCES Conta(id) ON DELETE CASCADE,
    tipo VARCHAR(20) NOT NULL DEFAULT 'transferencia',
    valor NUMERIC(15, 2) NOT NULL,
    data_transacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

DROP PROCEDURE IF EXISTS realizar_transferencia;

CREATE PROCEDURE realizar_transferencia(
    IN proc_id_origem INT,
    IN proc_id_destino INT,
    IN proc_valor NUMERIC(15, 2),
    OUT transacao_id INT

)
    LANGUAGE plpgsql
AS $$
DECLARE
novo_id INT;
BEGIN
    IF proc_valor <= 0 THEN
        RAISE EXCEPTION 'Valor deve ser positivo.';
END IF;

    IF (SELECT saldo FROM Conta WHERE id = proc_id_origem) < proc_valor THEN
        RAISE EXCEPTION 'Saldo insuficiente.';
END IF;

UPDATE Conta SET saldo = saldo - proc_valor WHERE id = proc_id_origem;
UPDATE Conta SET saldo = saldo + proc_valor WHERE id = proc_id_destino;

INSERT INTO Transacao (id_origem, id_destino, valor) VALUES (proc_id_origem, proc_id_destino, proc_valor) RETURNING id INTO novo_id;

transacao_id := novo_id;
END $$;


DO $$
DECLARE
i INT;
    cliente_id INT;
BEGIN
FOR i IN 1..50 LOOP
        INSERT INTO Cliente (nome, idade, email)
        VALUES (
            'Cliente ' || i,
            18 + (i % 50),
            'cliente' || i || '@exemplo.com'
        )
        RETURNING id INTO cliente_id;


INSERT INTO Conta (numero_conta, saldo, id_cliente)
VALUES (
               '00000' || LPAD(i::text, 5, '0'),
               10000.00,
               cliente_id
       );
END LOOP;
END $$;


DO $$
DECLARE
origem INT;
    destino INT;
    valor NUMERIC(15, 2);
    transacao_id INT;
BEGIN
FOR origem IN 1..50 LOOP
        destino := (origem % 50) + 1;
        valor := 50 + (origem * 10);

CALL realizar_transferencia(origem, destino, valor, transacao_id);
END LOOP;
END $$;