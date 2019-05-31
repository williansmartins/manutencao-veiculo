-- TABELA MANUTENCAO_VEICULOS
CREATE TABLE `manutencao_veiculo`.`carros` ( `id` INT NOT NULL AUTO_INCREMENT , `fabricante` VARCHAR(255) NOT NULL , `modelo` VARCHAR(255) NOT NULL , `ano` VARCHAR(255) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;

-- TABELA CARROS
CREATE TABLE `carros` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fabricante` varchar(255) NOT NULL,
  `modelo` varchar(255) NOT NULL,
  `ano` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8