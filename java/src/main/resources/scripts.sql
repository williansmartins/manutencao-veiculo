-- TABELA MANUTENCAO_VEICULOS
CREATE TABLE `manutencoes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  `id_veiculo` INT NOT NULL,
  `data` date NOT NULL,
  `categoria` varchar(255) NOT NULL,
  `kilometragem` INT NOT NULL,
  `observacoes` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8

INSERT INTO `manutencoes` (`id`, `id_usuario`, `id_veiculo`, `data`, `categoria`, `kilometragem`, `observacoes`) VALUES (NULL, '1', '2', '2019-06-03', 'ccc', '123', 'ooo')


-- TABELA CARROS
CREATE TABLE `carros` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fabricante` varchar(255) NOT NULL,
  `modelo` varchar(255) NOT NULL,
  `ano` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8