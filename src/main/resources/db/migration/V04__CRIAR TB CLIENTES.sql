CREATE TABLE `cliente` (
  `id` BIGINT AUTO_INCREMENT NOT NULL,
  `email` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `cliente_ingressos` (
  `cliente_id` BIGINT NOT NULL,
  `evento_id` BIGINT NOT NULL,
  `quantidade_ingressos` int NOT NULL,
  `valor_ingresso` decimal(19,2) NOT NULL,
  KEY `FK88cb8qupl6srfmy9jkqvxjm6w` (`evento_id`),
  KEY `FKsjfd885rdksvitqkc2k9n3i33` (`cliente_id`),
  CONSTRAINT `FK88cb8qupl6srfmy9jkqvxjm6w` FOREIGN KEY (`evento_id`) REFERENCES `evento` (`id`),
  CONSTRAINT `FKsjfd885rdksvitqkc2k9n3i33` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `cliente` (`id`, `email`, `nome`) VALUES ('150', 'teste@teste.com', 'Teste');
INSERT INTO `cliente_ingressos` (cliente_id, evento_id, quantidade_ingressos, valor_ingresso) VALUES (150, 1, 10, 150);
INSERT INTO `cliente_ingressos` (cliente_id, evento_id, quantidade_ingressos, valor_ingresso) VALUES (150, 2, 3, 120);
INSERT INTO `cliente_ingressos` (cliente_id, evento_id, quantidade_ingressos, valor_ingresso) VALUES (150, 1, 3, 120);
INSERT INTO `cliente_ingressos` (cliente_id, evento_id, quantidade_ingressos, valor_ingresso) VALUES (150, 1, 12, 120);
