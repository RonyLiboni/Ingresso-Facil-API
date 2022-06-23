CREATE TABLE `carrinho` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cliente_email` varchar(255) NOT NULL,
  `evento_id` bigint NOT NULL,
  `quantidade_ingressos` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `carrinho` (`id`, `cliente_email`, `evento_id`, `quantidade_ingressos`) VALUES ('1', 'teste@teste.com', '1', '10');
INSERT INTO `carrinho` (`id`, `cliente_email`, `evento_id`, `quantidade_ingressos`) VALUES ('2', 'teste@teste.com', '2', '3');