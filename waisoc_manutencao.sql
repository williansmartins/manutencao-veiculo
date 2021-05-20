-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 20, 2021 at 02:16 AM
-- Server version: 5.7.33-log-cll-lve
-- PHP Version: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `waisoc_manutencao`
--

-- --------------------------------------------------------

--
-- Table structure for table `carros`
--

CREATE TABLE `carros` (
  `id` int(11) NOT NULL,
  `fabricante` varchar(255) NOT NULL,
  `modelo` varchar(255) NOT NULL,
  `ano` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `carros`
--

INSERT INTO `carros` (`id`, `fabricante`, `modelo`, `ano`) VALUES
(4047, 'Honda', 'FIT', '2010'),
(4050, 'Honda', 'FIT', '2010'),
(4051, 'Honda', 'FIT', '2010'),
(4052, 'Honda', 'FIT', '2010');

-- --------------------------------------------------------

--
-- Table structure for table `manutencoes`
--

CREATE TABLE `manutencoes` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_veiculo` int(11) NOT NULL,
  `data` date NOT NULL,
  `categoria` varchar(255) NOT NULL,
  `kilometragem` int(11) NOT NULL,
  `observacoes` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `manutencoes`
--

INSERT INTO `manutencoes` (`id`, `id_usuario`, `id_veiculo`, `data`, `categoria`, `kilometragem`, `observacoes`) VALUES
(10, 2, 3, '2019-06-02', 'TROCADEOLEO', 12345, 'observacoes'),
(11, 2, 3, '2019-07-08', 'CORRENTE', 12345, 'observacoes'),
(12, 2, 3, '2019-06-02', 'CORRENTE', 12345, 'observacoes'),
(23, 0, 0, '2019-06-10', 'CORRENTE', 123, 'ooo'),
(24, 2, 3, '2019-06-03', 'COMBUSTIVEL', 12345, 'observacoes'),
(25, 2, 3, '2019-06-03', 'COMBUSTIVEL', 12345, 'observacoes');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `carros`
--
ALTER TABLE `carros`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `manutencoes`
--
ALTER TABLE `manutencoes`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `carros`
--
ALTER TABLE `carros`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4053;

--
-- AUTO_INCREMENT for table `manutencoes`
--
ALTER TABLE `manutencoes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
