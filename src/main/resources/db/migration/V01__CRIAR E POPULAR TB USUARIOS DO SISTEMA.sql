CREATE TABLE USUARIO (
  id BIGINT AUTO_INCREMENT NOT NULL,
  email varchar(255) NOT NULL,
  senha varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO USUARIO(id, email, senha) VALUES(1, 'admin@gmail.com', '$2a$12$KxRyI0kAAQKEgCC..yIc4eVv/AraR1l0osAr783z5WPpbT0pm5y1q');
INSERT INTO USUARIO(id, email, senha) VALUES(2, 'teste@teste.com', '$2a$12$KxRyI0kAAQKEgCC..yIc4eVv/AraR1l0osAr783z5WPpbT0pm5y1q');

CREATE TABLE `perfil` (
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO  PERFIL(nome) VALUES('ROLE_CLIENTE');
INSERT INTO  PERFIL(nome) VALUES('ROLE_ADMIN');

CREATE TABLE `usuario_perfis` (
  `usuario_id` BIGINT NOT NULL,
  `perfis_nome` varchar(255) NOT NULL,
  KEY `FKmnkmfvpilucevopx9qkmdfpe5` (`perfis_nome`),
  KEY `FKs91tgiyagbilt959wbufiphgc` (`usuario_id`),
  CONSTRAINT `FKmnkmfvpilucevopx9qkmdfpe5` FOREIGN KEY (`perfis_nome`) REFERENCES `perfil` (`nome`),
  CONSTRAINT `FKs91tgiyagbilt959wbufiphgc` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO  USUARIO_PERFIS(usuario_id, perfis_nome) VALUES(1, 'ROLE_ADMIN');
INSERT INTO  USUARIO_PERFIS(usuario_id, perfis_nome) VALUES(2, 'ROLE_CLIENTE');

