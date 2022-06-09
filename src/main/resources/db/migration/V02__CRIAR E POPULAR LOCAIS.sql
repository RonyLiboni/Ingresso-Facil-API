CREATE TABLE `INGRESSO-FACIL-API`.`local` (
  `id` BIGINT AUTO_INCREMENT NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO  `INGRESSO-FACIL-API`.`local`(id, nome, endereco) VALUES(1, 'Gigantinho', 'Avenida Padre Cacique, 891 Ao lado do estádio Beira-Rio - Praia de Belas, Porto Alegre - RS, 90810-240');
INSERT INTO  `INGRESSO-FACIL-API`.`local`(id, nome, endereco) VALUES(2, 'FIERGS', 'Av. Assis Brasil, 8787 - Sarandi, Porto Alegre - RS, 91140-001');
INSERT INTO  `INGRESSO-FACIL-API`.`local`(id, nome, endereco) VALUES(3, 'Arena do Grêmio', 'Av. Padre Leopoldo Brentano, 110 - Farrapos, Porto Alegre - RS, 90250-590');
INSERT INTO  `INGRESSO-FACIL-API`.`local`(id, nome, endereco) VALUES(4, 'Teatro São Pedro', 'Centro Histórico de Porto Alegre');

