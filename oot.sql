-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 01, 2015 at 12:10 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `guru`
--

CREATE TABLE IF NOT EXISTS `guru` (
`id_guru` int(4) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `admin` enum('0','1') NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `guru`
--

INSERT INTO `guru` (`id_guru`, `username`, `password`, `nama`, `admin`) VALUES
(1, 'admin', 'admin', 'Admin Ganteng', '1');

-- --------------------------------------------------------

--
-- Table structure for table `kompomen`
--

CREATE TABLE IF NOT EXISTS `kompomen` (
`id_kompomen` int(4) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `bobot` int(11) NOT NULL,
  `keterangan` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kompomen`
--

INSERT INTO `kompomen` (`id_kompomen`, `nama`, `bobot`, `keterangan`) VALUES
(3, 'Harian', 40, ''),
(5, 'UAS', 30, 'Nilai Ujian Akhir Semester'),
(6, 'Presensi', 10, 'Total Kehadiran'),
(7, 'tes', 10, 'tes'),
(8, 'tes', 10, 'tes'),
(17, 'Harian', 30, 'Nilai Ulangan Harian'),
(18, 'UTS', 20, 'Nilai Ulangan Tengah Semester'),
(19, 'UAS', 20, 'Nilai Ulangan Akhir Semester');

-- --------------------------------------------------------

--
-- Table structure for table `kompomen_pelajaran`
--

CREATE TABLE IF NOT EXISTS `kompomen_pelajaran` (
  `id_pelajaran` int(11) NOT NULL,
  `id_kompomen` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kompomen_pelajaran`
--

INSERT INTO `kompomen_pelajaran` (`id_pelajaran`, `id_kompomen`) VALUES
(1, 17),
(1, 18),
(1, 19);

-- --------------------------------------------------------

--
-- Table structure for table `nilai`
--

CREATE TABLE IF NOT EXISTS `nilai` (
  `id_siswa` int(4) NOT NULL,
  `id_kompomen` int(4) NOT NULL,
  `nilai` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pelajaran`
--

CREATE TABLE IF NOT EXISTS `pelajaran` (
`id_pelajaran` int(4) NOT NULL,
  `id_guru` int(4) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `sks` int(1) NOT NULL,
  `keterangan` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pelajaran`
--

INSERT INTO `pelajaran` (`id_pelajaran`, `id_guru`, `nama`, `sks`, `keterangan`) VALUES
(1, 1, 'IPA', 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE IF NOT EXISTS `siswa` (
`id_siswa` int(4) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `kelas` varchar(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`id_siswa`, `username`, `password`, `kelas`) VALUES
(1, 'siswa', 'siswa', 'K 01'),
(2, 'siswa2', 'siswa2', 'K 2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `guru`
--
ALTER TABLE `guru`
 ADD PRIMARY KEY (`id_guru`);

--
-- Indexes for table `kompomen`
--
ALTER TABLE `kompomen`
 ADD PRIMARY KEY (`id_kompomen`);

--
-- Indexes for table `kompomen_pelajaran`
--
ALTER TABLE `kompomen_pelajaran`
 ADD PRIMARY KEY (`id_pelajaran`,`id_kompomen`), ADD KEY `id_kompomen` (`id_kompomen`);

--
-- Indexes for table `nilai`
--
ALTER TABLE `nilai`
 ADD KEY `id_siswa` (`id_siswa`), ADD KEY `id_kompomen` (`id_kompomen`);

--
-- Indexes for table `pelajaran`
--
ALTER TABLE `pelajaran`
 ADD PRIMARY KEY (`id_pelajaran`), ADD KEY `id_guru` (`id_guru`);

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
MODIFY `id_guru` int(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `kompomen`
--
ALTER TABLE `kompomen`
MODIFY `id_kompomen` int(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `pelajaran`
--
ALTER TABLE `pelajaran`
MODIFY `id_pelajaran` int(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `siswa`
--
ALTER TABLE `siswa`
MODIFY `id_siswa` int(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `kompomen_pelajaran`
--
ALTER TABLE `kompomen_pelajaran`
ADD CONSTRAINT `kompomen_pelajaran_ibfk_1` FOREIGN KEY (`id_pelajaran`) REFERENCES `pelajaran` (`id_pelajaran`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `kompomen_pelajaran_ibfk_2` FOREIGN KEY (`id_kompomen`) REFERENCES `kompomen` (`id_kompomen`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `nilai`
--
ALTER TABLE `nilai`
ADD CONSTRAINT `nilai_ibfk_1` FOREIGN KEY (`id_siswa`) REFERENCES `siswa` (`id_siswa`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `nilai_ibfk_2` FOREIGN KEY (`id_kompomen`) REFERENCES `kompomen` (`id_kompomen`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pelajaran`
--
ALTER TABLE `pelajaran`
ADD CONSTRAINT `pelajaran_ibfk_1` FOREIGN KEY (`id_guru`) REFERENCES `guru` (`id_guru`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
