CREATE TABLE `ingresso-facil-api`.usuario (
  id BIGINT AUTO_INCREMENT NOT NULL,
  email varchar(255) NOT NULL,
  senha varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `INGRESSO-FACIL-API`.USUARIO(id, email, senha) VALUES(1, 'admin@gmail.com', '$2a$12$AY2ZJ3COjWbQxWsACpWUBOJFGNEW1OFAfPoupuMp1JDlVZPQBtwom');
INSERT INTO `INGRESSO-FACIL-API`.USUARIO(id, email, senha) VALUES(2, 'cliente@gmail.com', '$2a$12$AY2ZJ3COjWbQxWsACpWUBOJFGNEW1OFAfPoupuMp1JDlVZPQBtwom');

CREATE TABLE `ingresso-facil-api`.`perfil` (
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO  `INGRESSO-FACIL-API`.PERFIL(nome) VALUES('ROLE_CLIENTE');
INSERT INTO  `INGRESSO-FACIL-API`.PERFIL(nome) VALUES('ROLE_ADMIN');

CREATE TABLE `INGRESSO-FACIL-API`.`usuario_perfis` (
  `usuario_id` BIGINT NOT NULL,
  `perfis_nome` varchar(255) NOT NULL,
  KEY `FKmnkmfvpilucevopx9qkmdfpe5` (`perfis_nome`),
  KEY `FKs91tgiyagbilt959wbufiphgc` (`usuario_id`),
  CONSTRAINT `FKmnkmfvpilucevopx9qkmdfpe5` FOREIGN KEY (`perfis_nome`) REFERENCES `perfil` (`nome`),
  CONSTRAINT `FKs91tgiyagbilt959wbufiphgc` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO  `INGRESSO-FACIL-API`.USUARIO_PERFIS(usuario_id, perfis_nome) VALUES(1, 'ROLE_ADMIN');
INSERT INTO  `INGRESSO-FACIL-API`.USUARIO_PERFIS(usuario_id, perfis_nome) VALUES(2, 'ROLE_CLIENTE');