CREATE DATABASE  IF NOT EXISTS `atmkb`;
USE `atmkb`;

CREATE TABLE `conta` (
  `numero` int NOT NULL,
  `saldo` float NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `conta` VALUES (1,100),(2,200);

CREATE TABLE `cartao` (
  `numero` int NOT NULL,
  `pin` varchar(255) NOT NULL,
  `tentativas` int NOT NULL,
  `conta` int NOT NULL,
  `data_validade` date NOT NULL,
  PRIMARY KEY (`numero`),
  UNIQUE KEY `conta_UNIQUE` (`conta`),
  KEY `fk_cartao_conta_idx` (`conta`),
  CONSTRAINT `fk_cartao_conta` FOREIGN KEY (`conta`) REFERENCES `conta` (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `cartao` VALUES (1,'$2a$10$TLEIxLc.IZYMRAzeQI3ESeuEoAMToKxehVNljWwsgxMe8jqPyIyWa',3,1,'2025-10-10'),(2,'$2a$10$YJ7NfpLr7pNSyBkRGt8mLu01JJ/ZjWlD0vkO4R46hLIUzW/nAtN6O',3,2,'2025-05-28');

CREATE TABLE `cliente` (
  `idcliente` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`idcliente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

INSERT INTO `cliente` VALUES (1,'Abel'),(2,'Bela'),(3,'Carlos');

CREATE TABLE `cliente_conta` (
  `cliente` int NOT NULL,
  `conta` int NOT NULL,
  PRIMARY KEY (`cliente`,`conta`),
  KEY `fk_cliente_conta_conta_idx` (`conta`),
  CONSTRAINT `fk_cliente_conta_cliente` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`idcliente`),
  CONSTRAINT `fk_cliente_conta_conta` FOREIGN KEY (`conta`) REFERENCES `conta` (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `cliente_conta` VALUES (1,1),(2,1),(3,2);

CREATE VIEW `v_cartao` AS 
select `cartao`.`numero` AS `numero_cartao`,
`cartao`.`pin` AS `pin`,
`cartao`.`tentativas` AS `tentativas`,
`cartao`.`data_validade` AS `data_validade`,
`conta`.`numero` AS `numero_conta`,
`conta`.`saldo` AS `saldo`,
`cliente`.`nome` AS `nome` 
from 
`cartao` join `conta`
join `cliente_conta`
join `cliente` 
on `cartao`.`conta` = `conta`.`numero` 
and `conta`.`numero` = `cliente_conta`.`conta` 
and `cliente_conta`.`cliente` = `cliente`.`idcliente`
order by `cliente`.`nome`;
