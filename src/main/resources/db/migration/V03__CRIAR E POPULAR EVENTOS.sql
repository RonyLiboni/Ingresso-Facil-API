CREATE TABLE `ingresso-facil-api`.`evento` (
  `id` BIGINT AUTO_INCREMENT NOT NULL,
  `caminho_imagem_do_evento` varchar(255),
  `data_evento` date NOT NULL,
  `hora_evento` time NOT NULL,
  `nome` varchar(255) NOT NULL,
  `quantidade_ingressos` int NOT NULL,
  `quantidade_ingressos_disponiveis` int NOT NULL,
  `quantidade_ingressos_vendidos` int NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `valor` decimal(19,2) NOT NULL,
  `local_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK98sxhoyieqgakssikyqiyq3fd` (`local_id`),
  CONSTRAINT `FK98sxhoyieqgakssikyqiyq3fd` FOREIGN KEY (`local_id`) REFERENCES `local` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `ingresso-facil-api`.`evento` (`id`, `caminho_imagem_do_evento`,`data_evento`,`hora_evento`,`nome`,`quantidade_ingressos`,`quantidade_ingressos_disponiveis`,`quantidade_ingressos_vendidos`,`tipo`,`valor`,`local_id`)
VALUES (1, 'ImagensDosEventos/1.jpg',"2022-12-12","20:00:00", 'Braba - Luisa Sonza', 5000, 4999, 1, 'MUSICAL', 165.00, 3);

INSERT INTO `ingresso-facil-api`.`evento` (`id`, `caminho_imagem_do_evento`,`data_evento`,`hora_evento`,`nome`,`quantidade_ingressos`,`quantidade_ingressos_disponiveis`,`quantidade_ingressos_vendidos`,`tipo`,`valor`,`local_id`)
VALUES (2, 'ImagensDosEventos/2.jpg',"2022-12-12","20:00:00", 'As patroas - Marilia Mendoça', 3000, 3990, 10, 'MUSICAL', 120.00, 3);

INSERT INTO `ingresso-facil-api`.`evento` (`id`, `caminho_imagem_do_evento`,`data_evento`,`hora_evento`,`nome`,`quantidade_ingressos`,`quantidade_ingressos_disponiveis`,`quantidade_ingressos_vendidos`,`tipo`,`valor`,`local_id`)
VALUES (3, 'ImagensDosEventos/3.jpg',"2022-09-22","20:00:00", 'Guns N´ Roses', 8000, 8000, 0, 'MUSICAL', 220.00 , 3);

INSERT INTO `ingresso-facil-api`.`evento` (`id`, `caminho_imagem_do_evento`,`data_evento`,`hora_evento`,`nome`,`quantidade_ingressos`,`quantidade_ingressos_disponiveis`,`quantidade_ingressos_vendidos`,`tipo`,`valor`,`local_id`)
VALUES (4, 'ImagensDosEventos/4.jpg',"2022-09-22","20:00:00", 'Aprenda java em 63 minutos - Do zero ao avançado', 300, 300, 0, 'TREINAMENTO', 29.90 , 4);
