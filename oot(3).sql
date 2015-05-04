-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 03, 2015 at 01:19 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `oot`
--
CREATE DATABASE IF NOT EXISTS `oot` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `oot`;

-- --------------------------------------------------------

--
-- Table structure for table `guru`
--

DROP TABLE IF EXISTS `guru`;
CREATE TABLE IF NOT EXISTS `guru` (
`id_guru` int(4) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `pelajaran` varchar(40) NOT NULL,
  `admin` enum('0','1') NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `guru`
--

INSERT INTO `guru` (`id_guru`, `username`, `password`, `nama`, `pelajaran`, `admin`) VALUES
(1, 'admin', 'admin', 'Admin Ganteng', 'IPA', '1'),
(2, 'g', 'g', 'Guru 2', 'IPS', '0'),
(3, 'gg', 'gg2', 'Guru 3', 'Matematika', '0'),
(4, 'a2', 'a', 'Admin2', 'Tes', '1');

-- --------------------------------------------------------

--
-- Table structure for table `komplain`
--

DROP TABLE IF EXISTS `komplain`;
CREATE TABLE IF NOT EXISTS `komplain` (
  `id_guru` int(4) NOT NULL,
  `id_siswa` int(4) NOT NULL,
  `pesan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `komplain`
--

INSERT INTO `komplain` (`id_guru`, `id_siswa`, `pesan`) VALUES
(1, 1, 'tes'),
(1, 1, 'Cek');

-- --------------------------------------------------------

--
-- Table structure for table `kompomen`
--

DROP TABLE IF EXISTS `kompomen`;
CREATE TABLE IF NOT EXISTS `kompomen` (
`id_kompomen` int(4) NOT NULL,
  `id_guru` int(4) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `bobot` int(11) NOT NULL,
  `keterangan` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kompomen`
--

INSERT INTO `kompomen` (`id_kompomen`, `id_guru`, `nama`, `bobot`, `keterangan`) VALUES
(21, 1, 'UTS', 50, 'uts'),
(22, 1, 'UAS', 50, 'asdf'),
(23, 2, 'Harian', 40, 'Nilai Harian');

-- --------------------------------------------------------

--
-- Table structure for table `nilai`
--

DROP TABLE IF EXISTS `nilai`;
CREATE TABLE IF NOT EXISTS `nilai` (
  `id_siswa` int(4) NOT NULL,
  `id_kompomen` int(4) NOT NULL,
  `nilai` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nilai`
--

INSERT INTO `nilai` (`id_siswa`, `id_kompomen`, `nilai`) VALUES
(1, 21, 100),
(1, 22, 100);

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

DROP TABLE IF EXISTS `siswa`;
CREATE TABLE IF NOT EXISTS `siswa` (
`id_siswa` int(4) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `kelas` varchar(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`id_siswa`, `username`, `password`, `nama`, `kelas`) VALUES
(1, 'siswa', 'siswa', 'Siswa No.1', 'K 1'),
(2, 'siswa2', 'siswa2', 'Siswa No.2', 'K 2'),
(3, 'siswa3', 's3', 'Siswa No.3', 'K 3');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `guru`
--
ALTER TABLE `guru`
 ADD PRIMARY KEY (`id_guru`);

--
-- Indexes for table `komplain`
--
ALTER TABLE `komplain`
 ADD KEY `id_guru` (`id_guru`), ADD KEY `id_siswa` (`id_siswa`);

--
-- Indexes for table `kompomen`
--
ALTER TABLE `kompomen`
 ADD PRIMARY KEY (`id_kompomen`), ADD KEY `id_pelajaran` (`id_guru`);

--
-- Indexes for table `nilai`
--
ALTER TABLE `nilai`
 ADD PRIMARY KEY (`id_siswa`,`id_kompomen`), ADD KEY `id_kompomen` (`id_kompomen`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
 ADD PRIMARY KEY (`id_siswa`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `guru`
--
ALTER TABLE `guru`
MODIFY `id_guru` int(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `kompomen`
--
ALTER TABLE `kompomen`
MODIFY `id_kompomen` int(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT for table `siswa`
--
ALTER TABLE `siswa`
MODIFY `id_siswa` int(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `komplain`
--
ALTER TABLE `komplain`
ADD CONSTRAINT `komplain_ibfk_1` FOREIGN KEY (`id_guru`) REFERENCES `guru` (`id_guru`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `komplain_ibfk_2` FOREIGN KEY (`id_siswa`) REFERENCES `siswa` (`id_siswa`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `kompomen`
--
ALTER TABLE `kompomen`
ADD CONSTRAINT `kompomen_ibfk_1` FOREIGN KEY (`id_guru`) REFERENCES `guru` (`id_guru`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `nilai`
--
ALTER TABLE `nilai`
ADD CONSTRAINT `nilai_ibfk_1` FOREIGN KEY (`id_siswa`) REFERENCES `siswa` (`id_siswa`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `nilai_ibfk_2` FOREIGN KEY (`id_kompomen`) REFERENCES `kompomen` (`id_kompomen`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
