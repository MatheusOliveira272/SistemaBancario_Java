/*

----SCRIPT PARA O BANCO DE DADOS DO SISTEMA BANCÁRIO----
				---- MATHEUS OLIVEIRA ----
                
*/

/*CRIANDO O BANCO E AS TABELAS PERTINENTES*/

CREATE DATABASE sistema_bancario;

USE sistema_bancario;

CREATE TABLE clientes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefone VARCHAR(20) NOT NULL,
    endereco VARCHAR(200) NOT NULL
);

CREATE TABLE agencias (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(200) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);

CREATE TABLE contas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cliente_id INT NOT NULL,
    agencia_id INT NOT NULL,
    saldo DECIMAL(10,2) NOT NULL DEFAULT 0,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (agencia_id) REFERENCES agencias(id)
);

CREATE TABLE transferencias (
    id INT PRIMARY KEY AUTO_INCREMENT,
    conta_origem_id INT NOT NULL,
    conta_destino_id INT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data_transferencia DATETIME NOT NULL,
    FOREIGN KEY (conta_origem_id) REFERENCES contas(id),
    FOREIGN KEY (conta_destino_id) REFERENCES contas(id)
);

CREATE TABLE pagamentos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    conta_id INT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data_pagamento DATETIME NOT NULL,
    FOREIGN KEY (conta_id) REFERENCES contas(id)
);

CREATE TABLE saques (
    id INT PRIMARY KEY AUTO_INCREMENT,
    conta_id INT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data_saque DATETIME NOT NULL,
    FOREIGN KEY (conta_id) REFERENCES contas(id)
);

CREATE TABLE depositos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    conta_id INT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data_deposito DATETIME NOT NULL,
    FOREIGN KEY (conta_id) REFERENCES contas(id)
);

CREATE TABLE emprestimos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    conta_id INT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    taxa_juros DECIMAL(5,2) NOT NULL,
    prazo_meses INT NOT NULL,
    data_contratacao DATETIME NOT NULL,
    FOREIGN KEY (conta_id) REFERENCES contas(id)
);

CREATE TABLE investimentos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    conta_id INT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    taxa_juros DECIMAL(5,2) NOT NULL,
    prazo_meses INT NOT NULL,
    data_inicio DATETIME NOT NULL,
    FOREIGN KEY (conta_id) REFERENCES contas(id)
);

CREATE TABLE usuarios (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80)NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(150) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE controle_login(
	id INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT,
    data_login TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_logoff TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
);

/* POPULANDO O BD INSERTS */

INSERT INTO clientes (nome, cpf, email, telefone, endereco) VALUES
('José da Silva', '11122233244', 'jose.silva@gmail.com', '(11) 99999-9999', 'Rua das Flores, 123'),
('Maria Souza', '22233344255', 'maria.souza@yahoo.com.br', '(11) 88888-8888', 'Avenida Paulista, 1000'),
('João Santos', '33344455516', 'joao.santos@hotmail.com', '(11) 77777-7777', 'Rua das Pedras, 789'),
('Ana Oliveira', '44455564677', 'ana.oliveira@gmail.com', '(11) 66666-6666', 'Rua das Árvores, 555'),
('Pedro Rocha', '55566677888', 'pedro.rocha@yahoo.com.br', '(11) 55555-5555', 'Rua dos Pinheiros, 222'),
('Carla Almeida', '66677788099', 'carla.almeida@hotmail.com', '(11) 44444-4444', 'Avenida Faria Lima, 789'),
('Lucas Costa', '77788829900', 'lucas.costa@gmail.com', '(11) 33333-3333', 'Rua das Flores, 123'),
('Mariana Fernandes', '88899900012', 'mariana.fernandes@yahoo.com.br', '(11) 22222-2222', 'Avenida Paulista, 1000'),
('Rafaela Lima', '99900011122', 'rafaela.lima@hotmail.com', '(11) 11111-1111', 'Rua das Pedras, 789'),
('Rodrigo Santos', '00011122233', 'rodrigo.santos@gmail.com', '(11) 99999-9999', 'Rua das Árvores, 555'),
('Tatiana Oliveira', '11122233344', 'tatiana.oliveira@yahoo.com.br', '(11) 88888-8888', 'Rua dos Pinheiros, 222'),
('Leonardo Carvalho', '22233344455', 'leonardo.carvalho@hotmail.com', '(11) 77777-7777', 'Avenida Faria Lima, 789'),
('Amanda Souza', '33344455566', 'amanda.souza@gmail.com', '(11) 66666-6666', 'Rua das Flores, 123'),
('Fernando Alves', '44455566677', 'fernando.alves@yahoo.com.br', '(11) 55555-5555', 'Avenida Paulista, 1000'),
('Isabella Rodrigues', '55566677788', 'isabella.rodrigues@hotmail.com', '(11) 44444-4444', 'Rua das Pedras, 789'),
('Roberto Gomes', '66677788899', 'roberto.gomes@gmail.com', '(11) 33333-3333', 'Rua das Árvores, 555'),
('Gabriela Ferreira', '77788899900', 'gabriela.ferreira@yahoo.com.br', '(11) 22222-2222', 'Rua dos Pinheiros, 222'),
('Gabriela Oliveira', '01234567891', 'gabriela.oliveira@gmail.com', '(11) 99876-5432', 'Rua das Flores, 111'),
('Ricardo Santos', '12345678921', 'ricardo.santos@yahoo.com.br', '(11) 98765-4321', 'Rua dos Pinheiros, 789'),
('Juliana Costa', '23456759012', 'juliana.costa@gmail.com', '(11) 99876-5432', 'Avenida Paulista, 1000'),
('Daniel Martins', '34567891123', 'daniel.martins@yahoo.com.br', '(11) 98765-4321', 'Rua Oscar Freire, 111'),
('Fernanda Alves', '45678902234', 'fernanda.alves@hotmail.com', '(11) 99876-5432', 'Rua Augusta, 555'),
('Pedro Silva', '56789019345', 'pedro.silva@gmail.com', '(11) 98765-4321', 'Rua Amauri, 1000'),
('Luciana Rodrigues', '17890123456', 'luciana.rodrigues@yahoo.com.br', '(11) 99876-5432', 'Rua João Cachoeira, 789'),
('Rodrigo Oliveira', '74901234567', 'rodrigo.oliveira@hotmail.com', '(11) 98765-4321', 'Rua das Figueiras, 111'),
('Tatiana Souza', '89016345678', 'tatiana.souza@gmail.com', '(11) 99876-5432', 'Avenida Paulista, 1000'),
('Márcio Almeida', '90173456789', 'marcio.almeida@yahoo.com.br', '(11) 98765-4321', 'Rua Oscar Freire, 111'),
('Amanda Santos', '01239567890', 'amanda.santos@hotmail.com', '(11) 99876-5432', 'Rua Augusta, 555'),
('Gustavo Pereira', '12745678901', 'gustavo.pereira@gmail.com', '(11) 98765-4321', 'Rua Amauri, 1000'),
('Carolina Alves', '23486789012', 'carolina.alves@yahoo.com.br', '(11) 99876-5432', 'Rua João Cachoeira, 789'),
('Felipe Oliveira', '34967890123', 'felipe.oliveira@hotmail.com', '(11) 98765-4321', 'Avenida Paulista, 1000'),
('Marcela Rodrigues', '05678901234', 'marcela.rodrigues@gmail.com', '(11) 99876-5432', 'Rua das Figueiras, 111'),
('Leonardo Silva', '56729012345', 'leonardo.silva@yahoo.com.br', '(11) 98765-4321', 'Rua Oscar Freire, 111'),
('Larissa Costa', '67895123456', 'larissa.costa@hotmail.com', '(11) 99876-5432', 'Rua Augusta, 555'),
('Ana Paula Rodrigues', '31122233344', 'anapaula.rodrigues@gmail.com', '(11) 99876-5432', 'Rua Augusta, 555'),
('Caio Oliveira', '2223354455', 'caio.oliveira@yahoo.com.br', '(11) 98765-4321', 'Rua Amauri, 1000'),
('Luana Silva', '33344451566', 'luana.silva@gmail.com', '(11) 99876-5432', 'Rua João Cachoeira, 789'),
('Thiago Alves', '44455546677', 'thiago.alves@yahoo.com.br', '(11) 98765-4321', 'Avenida Paulista, 1000'),
('Julia Santos', '55566637788', 'julia.santos@hotmail.com', '(11) 99876-5432', 'Rua das Figueiras, 111'),
('Guilherme Oliveira', '16677788899', 'guilherme.oliveira@gmail.com', '(11) 98765-4321', 'Rua Oscar Freire, 111'),
('Beatriz Souza', '77788599900', 'beatriz.souza@yahoo.com.br', '(11) 99876-5432', 'Rua Augusta, 555'),
('Carlos Martins', '88896900011', 'carlos.martins@gmail.com', '(11) 98765-4321', 'Rua Amauri, 1000'),
('Isadora Costa', '99900211122', 'isadora.costa@hotmail.com', '(11) 99876-5432', 'Rua João Cachoeira, 789'),
('Lucas Oliveira', '12344678901', 'lucas.oliveira@yahoo.com.br', '(11) 98765-4321', 'Avenida Paulista, 1000'),
('Renata Silva', '23456789012', 'renata.silva@gmail.com', '(11) 99876-5432', 'Rua das Figueiras, 111'),
('Henrique Almeida', '34567890123', 'henrique.almeida@yahoo.com.br', '(11) 98765-4321', 'Rua Oscar Freire, 111'),
('Aline Santos', '45678901234', 'aline.santos@hotmail.com', '(11) 99876-5432', 'Rua Augusta, 555'),
('Gustavo Pereira', '56789012345', 'gustavo.pereira12@gmail.com', '(11) 98765-4321', 'Rua Amauri, 1000'),
('Carolina Alves', '67890123456', 'carolina.alves94@yahoo.com.br', '(11) 99876-5432', 'Rua João Cachoeira, 789'),
('Felipe Oliveira', '78901234567', 'felipe.oliveira14@hotmail.com', '(11) 98765-4321', 'Avenida Paulista, 1000'),
('Marcela Rodrigues', '89012345678', 'marcela.rodrigues9@hotmail.com', '(11) 99876-5432', 'Rua das Figueiras, 111'),
('Leonardo Silva', '90123456789', 'leonardo.silva19@yahoo.com.br', '(11) 98765-4321', 'Rua Oscar Freire, 111');

INSERT INTO agencias (nome, endereco, telefone) VALUES ('Agência Central', 'Rua Central, 123 - Centro', '(11) 1234-5678');
INSERT INTO agencias (nome, endereco, telefone) VALUES ('Agência Norte', 'Av. Norte, 456 - Vila Nova', '(11) 2345-6789');
INSERT INTO agencias (nome, endereco, telefone) VALUES ('Agência Sul', 'Av. Sul, 789 - Jardim do Sul', '(11) 3456-7890');
INSERT INTO agencias (nome, endereco, telefone) VALUES ('Agência Leste', 'Rua Leste, 1010 - Vila Leste', '(11) 4567-8901');
INSERT INTO agencias (nome, endereco, telefone) VALUES ('Agência Oeste', 'Rua Oeste, 2020 - Vila Oeste', '(11) 5678-9012');
INSERT INTO agencias (nome, endereco, telefone) VALUES ('Agência ABC', 'Av. ABC, 3030 - Vila ABC', '(11) 6789-0123');
INSERT INTO agencias (nome, endereco, telefone) VALUES ('Agência XYZ', 'Rua XYZ, 4040 - Vila XYZ', '(11) 7890-1234');
INSERT INTO agencias (nome, endereco, telefone) VALUES ('Agência Central II', 'Rua Central, 567 - Centro', '(11) 8901-2345');
INSERT INTO agencias (nome, endereco, telefone) VALUES ('Agência Norte II', 'Av. Norte, 789 - Vila Nova', '(11) 9012-3456');
INSERT INTO agencias (nome, endereco, telefone) VALUES ('Agência Sul II', 'Av. Sul, 1010 - Jardim do Sul', '(11) 0123-4567');

INSERT INTO contas (cliente_id, agencia_id, saldo) VALUES
(1, 1, 1000.00),
(2, 2, 2500.50),
(3, 3, 5000.00),
(4, 4, 1200.00),
(5, 5, 800.00),
(6, 6, 300.00),
(7, 7, 1500.00),
(8, 8, 200.00),
(9, 9, 7000.00),
(10, 10, 1500.00),
(11, 1, 3500.00),
(12, 2, 200.00),
(13, 3, 450.00),
(14, 4, 1000.00),
(15, 5, 600.00),
(16, 6, 4000.00),
(17, 7, 150.00),
(18, 8, 900.00),
(19, 9, 3200.00),
(20, 10, 700.00),
(21, 1, 250.00),
(22, 2, 600.00),
(23, 3, 100.00),
(24, 4, 1800.00),
(25, 5, 400.00),
(26, 6, 2500.00),
(27, 7, 1200.00),
(28, 8, 1900.00),
(29, 9, 10000.00),
(30, 10, 500.00),
(31, 1, 800.00),
(32, 2, 1200.00),
(33, 3, 600.00),
(34, 4, 3000.00),
(35, 5, 700.00),
(36, 6, 5000.00),
(37, 7, 100.00),
(38, 8, 2000.00),
(39, 9, 1800.00),
(40, 10, 1500.00);

INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (1, 2, 100.00, '2023-03-27 10:30:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (3, 4, 500.00, '2023-03-27 14:20:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (2, 7, 50.00, '2023-03-27 11:45:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (5, 8, 250.00, '2023-03-27 17:10:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (10, 6, 75.00, '2023-03-27 16:35:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (9, 7, 200.00, '2023-03-27 09:15:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (1, 5, 150.00, '2023-03-27 13:50:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (4, 2, 300.00, '2023-03-27 12:05:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (6, 3, 50.00, '2023-03-27 10:00:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (8, 5, 100.00, '2023-03-27 15:25:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (2, 4, 500.00, '2022-10-01 14:30:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (5, 6, 1000.00, '2022-10-02 09:45:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (9, 7, 250.00, '2022-10-03 15:20:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (13, 8, 200.00, '2022-10-04 11:00:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (16, 10, 1500.00, '2022-10-05 13:30:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (21, 23, 800.00, '2022-10-06 16:45:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (27, 28, 300.00, '2022-10-07 10:00:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (32, 36, 600.00, '2022-10-08 11:15:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (38, 37, 750.00, '2022-10-09 14:00:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (3, 1, 400.00, '2022-10-10 09:30:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (7, 10, 900.00, '2022-10-11 13:45:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (11, 14, 100.00, '2022-10-12 16:15:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (15, 18, 750.00, '2022-10-13 10:00:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (19, 22, 400.00, '2022-10-14 14:30:00');
INSERT INTO transferencias (conta_origem_id, conta_destino_id, valor, data_transferencia) VALUES (24, 27, 200.00, '2022-10-15 11:30:00');

INSERT INTO emprestimos (conta_id, valor, taxa_juros, prazo_meses, data_contratacao) VALUES
(1, 5000.00, 0.05, 12, '2022-01-10 14:00:00'),
(2, 7000.00, 0.08, 18, '2022-02-15 16:30:00'),
(3, 3000.00, 0.10, 6, '2022-03-20 09:45:00'),
(4, 9000.00, 0.12, 24, '2022-04-25 11:15:00'),
(5, 10000.00, 0.06, 12, '2022-05-30 13:00:00'),
(6, 4000.00, 0.09, 18, '2022-06-05 14:30:00'),
(7, 8000.00, 0.11, 24, '2022-07-10 08:45:00'),
(8, 6000.00, 0.07, 12, '2022-08-15 10:15:00'),
(9, 2000.00, 0.10, 6, '2022-09-20 12:00:00'),
(10, 5000.00, 0.13, 24, '2022-10-25 14:30:00'),
(11, 7000.00, 0.08, 12, '2022-11-30 16:45:00'),
(12, 3000.00, 0.11, 18, '2022-12-05 09:00:00'),
(13, 9000.00, 0.14, 24, '2023-01-10 11:30:00'),
(14, 10000.00, 0.09, 12, '2023-02-15 13:45:00'),
(15, 4000.00, 0.12, 18, '2023-03-20 15:00:00'),
(16, 8000.00, 0.15, 24, '2023-04-25 08:30:00'),
(17, 6000.00, 0.10, 12, '2023-05-30 10:45:00'),
(18, 2000.00, 0.13, 6, '2023-06-05 12:00:00'),
(19, 5000.00, 0.16, 24, '2023-07-10 14:30:00'),
(20, 7000.00, 0.11, 12, '2023-08-15 16:45:00'),
(21, 3000.00, 0.14, 18, '2023-09-20 09:00:00'),
(22, 9000.00, 0.17, 24, '2023-10-25 11:30:00'),
(23, 10000.00, 0.12, 12, '2023-11-30 13:00:01');

INSERT INTO pagamentos (conta_id, valor, data_pagamento) VALUES 
(1, 500.00, '2022-02-15 10:30:00'),
(2, 250.00, '2022-02-10 15:00:00'),
(3, 200.00, '2022-01-20 09:45:00'),
(4, 300.00, '2022-03-01 13:20:00'),
(5, 1000.00, '2022-03-22 16:10:00'),
(6, 800.00, '2022-02-01 08:00:00'),
(7, 700.00, '2022-01-15 18:30:00'),
(8, 400.00, '2022-02-28 11:45:00'),
(9, 1500.00, '2022-03-10 14:00:00'),
(10, 900.00, '2022-02-12 10:30:00'),
(11, 600.00, '2022-01-25 11:20:00'),
(12, 350.00, '2022-02-14 15:00:00'),
(13, 100.00, '2022-02-05 09:00:00'),
(14, 1200.00, '2022-01-30 12:30:00'),
(15, 450.00, '2022-03-02 17:40:00'),
(16, 2000.00, '2022-02-20 08:00:00'),
(17, 300.00, '2022-01-18 11:15:00'),
(18, 150.00, '2022-03-15 14:20:00'),
(19, 1800.00, '2022-01-12 16:30:00'),
(20, 750.00, '2022-02-22 09:30:00'),
(21, 950.00, '2022-02-28 12:00:00'),
(22, 550.00, '2022-03-18 15:10:00'),
(23, 400.00, '2022-02-06 10:00:00'),
(24, 650.00, '2022-01-23 14:45:00'),
(25, 3000.00, '2022-03-07 16:20:00'),
(26, 800.00, '2022-02-11 10:00:00'),
(27, 1200.00, '2022-02-28 13:30:00'),
(28, 900.00, '2022-01-19 09:15:00'),
(29, 700.00, '2022-03-12 11:40:00'),
(30, 600.00, '2022-02-17 14:00:00'),
(3, 1200.00, '2022-01-04 10:15:00'),
(5, 400.00, '2022-01-08 16:30:00'),
(5, 800.00, '2022-02-01 09:45:00'),
(7, 1000.00, '2022-02-14 14:00:00'),
(10, 250.00, '2022-02-20 11:20:00'),
(10, 350.00, '2022-03-01 08:40:00'),
(12, 600.00, '2022-03-10 16:15:00'),
(12, 300.00, '2022-04-02 10:30:00'),
(14, 450.00, '2022-04-15 14:00:00'),
(15, 900.00, '2022-05-03 09:30:00'),
(17, 1200.00, '2022-05-15 15:45:00'),
(18, 150.00, '2022-06-01 11:00:00'),
(18, 250.00, '2022-06-17 08:20:00'),
(19, 1800.00, '2022-06-30 16:00:00'),
(21, 500.00, '2022-07-08 13:40:00'),
(21, 650.00, '2022-07-20 09:30:00'),
(23, 900.00, '2022-08-01 15:20:00'),
(24, 400.00, '2022-08-18 12:00:00'),
(26, 750.00, '2022-08-31 08:30:00'),
(27, 1000.00, '2022-09-12 17:00:00'),
(29, 1200.00, '2022-10-02 10:15:00'),
(30, 150.00, '2022-10-19 11:30:00'),
(32, 200.00, '2022-10-31 14:00:00'),
(32, 800.00, '2022-11-12 09:45:00'),
(35, 1000.00, '2022-11-24 16:00:00'),
(1, 500.00, '2023-03-25 15:00:00'),
(2, 200.00, '2023-03-23 10:30:00'),
(3, 800.00, '2023-03-22 14:45:00'),
(4, 1000.00, '2023-03-27 09:15:00'),
(5, 350.00, '2023-03-21 11:00:00'),
(6, 250.00, '2023-03-26 16:30:00'),
(7, 1500.00, '2023-03-24 13:00:00'),
(8, 1200.00, '2023-03-20 09:45:00'),
(9, 500.00, '2023-03-25 17:00:00'),
(10, 900.00, '2023-03-27 11:30:00'),
(11, 600.00, '2023-03-24 15:15:00'),
(12, 800.00, '2023-03-22 12:00:00'),
(13, 450.00, '2023-03-23 09:00:00'),
(14, 300.00, '2023-03-26 14:30:00'),
(15, 2000.00, '2023-03-25 16:00:00'),
(16, 1500.00, '2023-03-21 08:45:00'),
(17, 700.00, '2023-03-27 10:00:00'),
(18, 900.00, '2023-03-23 13:30:00'),
(19, 1200.00, '2023-03-22 11:15:00');

INSERT INTO saques (conta_id, valor, data_saque)
VALUES (1, 500.00, '2022-05-12 10:30:00'),
       (2, 1000.00, '2022-06-05 14:20:00'),
       (3, 300.00, '2022-06-30 18:15:00'),
       (4, 200.00, '2022-07-07 09:45:00'),
       (5, 750.00, '2022-07-21 11:00:00'),
       (6, 100.00, '2022-08-05 08:10:00'),
       (7, 250.00, '2022-08-22 16:30:00'),
       (8, 1500.00, '2022-09-03 09:00:00'),
       (9, 400.00, '2022-09-18 13:45:00'),
       (10, 600.00, '2022-09-29 17:00:00'),
	   (11, 800.00, '2022-10-11 10:30:00'),
       (12, 1500.00, '2022-10-25 14:20:00'),
       (13, 450.00, '2022-11-10 18:15:00'),
       (14, 300.00, '2022-11-28 09:45:00'),
       (15, 1200.00, '2022-12-05 11:00:00'),
       (16, 200.00, '2022-12-21 08:10:00'),
       (17, 350.00, '2023-01-08 16:30:00'),
       (18, 1800.00, '2023-01-20 09:00:00'),
       (19, 800.00, '2023-02-03 13:45:00'),
       (20, 600.00, '2023-02-15 17:00:00'),
       (21, 200.00, '2023-03-01 10:30:00'),
       (22, 1500.00, '2023-03-10 14:20:00'),
       (23, 350.00, '2023-03-22 18:15:00'),
       (24, 400.00, '2023-04-08 09:45:00'),
       (25, 900.00, '2023-04-20 11:00:00'),
       (26, 100.00, '2023-05-05 08:10:00'),
       (27, 200.00, '2023-05-18 16:30:00'),
       (28, 1200.00, '2023-06-03 09:00:00'),
       (29, 600.00, '2023-06-22 13:45:00'),
       (30, 800.00, '2023-07-07 17:00:00');


INSERT INTO depositos (conta_id, valor, data_deposito) VALUES
(1, 1500.00, '2022-01-01 10:00:00'),
(1, 2000.00, '2022-02-15 15:30:00'),
(2, 1000.00, '2022-02-01 08:45:00'),
(3, 500.00, '2022-03-05 11:20:00'),
(4, 3000.00, '2022-04-01 14:00:00'),
(5, 1500.00, '2022-05-10 09:15:00'),
(5, 1000.00, '2022-06-05 16:45:00'),
(6, 2000.00, '2022-06-01 13:30:00'),
(6, 500.00, '2022-07-08 11:10:00'),
(7, 3000.00, '2022-08-01 09:00:00'),
(8, 1500.00, '2022-09-20 17:00:00'),
(9, 1000.00, '2022-10-15 10:30:00'),
(9, 2000.00, '2022-11-01 14:15:00'),
(10, 500.00, '2022-12-10 08:20:00'),
(11, 3000.00, '2023-01-01 12:00:00'),
(12, 1500.00, '2023-02-20 09:45:00'),
(13, 1000.00, '2023-03-15 14:00:00'),
(14, 2000.00, '2023-04-01 16:30:00'),
(15, 500.00, '2023-05-10 10:45:00'),
(16, 3000.00, '2023-06-01 13:00:00'),
(17, 1500.00, '2023-07-20 11:15:00'),
(18, 1000.00, '2023-08-15 08:30:00'),
(18, 2000.00, '2023-09-01 15:15:00'),
(19, 500.00, '2023-10-10 14:30:00'),
(20, 3000.00, '2023-11-01 11:00:00'),
(20, 1500.00, '2023-12-05 17:30:00'),
(21, 1000.00, '2024-01-15 10:45:00');

INSERT INTO investimentos (conta_id, valor, taxa_juros, prazo_meses, data_inicio) VALUES
(5, 5000.00, 0.02, 24, '2022-01-01 10:00:00'),
(23, 10000.00, 0.03, 12, '2021-11-25 15:30:00'),
(17, 2500.00, 0.15, 36, '2022-02-12 08:45:00'),
(12, 7500.00, 0.25, 6, '2022-05-18 13:20:00'),
(31, 4000.00, 0.02, 12, '2022-03-22 11:10:00'),
(2, 15000.00, 0.04, 36, '2021-12-05 09:15:00'),
(38, 8000.00, 0.25, 24, '2022-04-28 14:30:00'),
(7, 1000.00, 0.01, 12, '2022-02-01 10:00:00'),
(33, 3000.00, 0.02, 6, '2022-06-01 12:00:00'),
(22, 10000.00, 0.35, 12, '2022-01-15 08:00:00'),
(4, 5000.00, 0.03, 36, '2022-03-01 14:30:00'),
(13, 7500.00, 0.05, 24, '2022-02-22 16:45:00'),
(27, 12000.00, 0.04, 36, '2021-12-10 09:00:00'),
(20, 6000.00, 0.02, 12, '2022-04-20 13:30:00'),
(36, 4000.00, 0.15, 6, '2022-03-02 11:45:00'),
(29, 8000.00, 0.02, 24, '2022-05-18 15:00:00'),
(10, 10000.00, 0.03, 36, '2022-01-01 08:00:00'),
(21, 5000.00, 0.15, 12, '2022-06-05 11:00:00'),
(34, 2000.00, 0.01, 6, '2022-02-10 14:30:00'),
(15, 3000.00, 0.02, 12, '2022-03-15 16:00:00'),
(37, 6000.00, 0.25, 24, '2022-02-18 09:45:00'),
(6, 15000.00, 0.35, 36, '2022-04-01 13:15:00'),
(25, 7500.00, 0.02, 12, '2022-05-25 14:30:00');

/* TRIGGERS PARA O BD */
	DELIMITER |
	CREATE TRIGGER ins_pagamentos_trigger
	BEFORE INSERT  ON pagamentos
	FOR EACH ROW
	BEGIN
	UPDATE contas SET contas.saldo = contas.saldo - NEW.valor
		WHERE contas.id = NEW.conta_id;
	END;

	DELIMITER |
	CREATE TRIGGER transferencia_trigger
	BEFORE INSERT  ON transferencias
	FOR EACH ROW
	BEGIN
	UPDATE contas SET contas.saldo = contas.saldo - NEW.valor
		WHERE contas.id = NEW.conta_origem_id;
		UPDATE contas SET contas.saldo =  contas.saldo + NEW.valor
		WHERE contas.id = NEW.conta_destino_id;
	END;

DELIMITER |
	CREATE TRIGGER ins_depositos_trigger
	BEFORE INSERT  ON depositos
	FOR EACH ROW
	BEGIN
	UPDATE contas SET contas.saldo = contas.saldo + NEW.valor
		WHERE contas.id = NEW.conta_id;
	END;

	DELIMITER |
	CREATE TRIGGER ins_saques_trigger
	BEFORE INSERT  ON saques
	FOR EACH ROW
	BEGIN
	UPDATE contas SET contas.saldo = contas.saldo - NEW.valor
		WHERE contas.id = NEW.conta_id;
	END;

 
 DELIMITER |
	CREATE TRIGGER del_pagamentos_trigger
	BEFORE DELETE  ON pagamentos
	FOR EACH ROW
	BEGIN
	UPDATE contas SET contas.saldo = contas.saldo + OLD.valor
		WHERE contas.id = OLD.conta_id;
	END;

 Delimiter |
CREATE TRIGGER del_transferencia_trigger
BEFORE DELETE ON transferencias
FOR EACH ROW
BEGIN
UPDATE contas SET contas.saldo = contas.saldo + OLD.valor
    WHERE contas.id = OLD.conta_origem_id;
    UPDATE contas SET contas.saldo = contas.saldo - OLD.valor
    WHERE contas.id = OLD.conta_destino_id;
 END;
 
 DELIMITER |
	CREATE TRIGGER del_depositos_trigger
	BEFORE DELETE  ON depositos
	FOR EACH ROW
	BEGIN
	UPDATE contas SET contas.saldo = contas.saldo - OLD.valor
		WHERE contas.id = OLD.conta_id;
	END;
 
 DELIMITER |
	CREATE TRIGGER del_saques_trigger
	BEFORE DELETE  ON saques
	FOR EACH ROW
	BEGIN
	UPDATE contas SET contas.saldo = contas.saldo + OLD.valor
		WHERE contas.id = OLD.conta_id;
	END;
 