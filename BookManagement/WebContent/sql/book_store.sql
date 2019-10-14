-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 25, 2018 at 07:47 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `book_store`
--
CREATE DATABASE `book_store` CHARACTER SET utf8 COLLATE utf8_unicode_ci;
-- --------------------------------------------------------

--
-- Table structure for table `bs_author`
--

CREATE TABLE `bs_author` (
  `id` int(11) NOT NULL,
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `dob` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bs_author`
--

INSERT INTO `bs_author` (`id`, `name`, `dob`) VALUES
(1, 'Fujiko Fujio', '1962-12-21 00:00:00'),
(2, 'Đoàn Quỳnh', '1952-10-20 00:00:00'),
(3, 'Văn Như Cương', '1923-02-21 00:00:00'),
(4, 'Nam Cao', '1922-12-12 00:00:00'),
(5, 'Nguyễn Nhật Ánh', '1964-12-21 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `bs_author_book`
--

CREATE TABLE `bs_author_book` (
  `id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `revenue_share` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bs_author_book`
--

INSERT INTO `bs_author_book` (`id`, `author_id`, `book_id`, `revenue_share`) VALUES
(1, 1, 1, '1'),
(2, 2, 2, '0'),
(3, 3, 2, '1'),
(4, 4, 3, '1'),
(6, 4, 5, '1'),
(7, 2, 3, '0'),
(8, 2, 6, '3');

-- --------------------------------------------------------

--
-- Table structure for table `bs_book`
--

CREATE TABLE `bs_book` (
  `id` int(11) NOT NULL,
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `category_id` int(11) NOT NULL,
  `sold_number` int(11) NOT NULL,
  `price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bs_book`
--

INSERT INTO `bs_book` (`id`, `name`, `category_id`, `sold_number`, `price`) VALUES
(1, 'Doremon', 1, 10, '30.00'),
(2, 'Hình học', 2, 100, '15.00'),
(3, 'Chí Phèo', 3, 2, '100.00'),
(4, 'Kính vạn hoa', 3, 5, '50.00'),
(5, 'Lão Hạc', 3, 1, '100.00'),
(6, 'Conan', 4, 0, '35.00');

-- --------------------------------------------------------

--
-- Table structure for table `bs_category`
--

CREATE TABLE `bs_category` (
  `id` int(11) NOT NULL,
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bs_category`
--

INSERT INTO `bs_category` (`id`, `name`) VALUES
(1, 'Truyện hài hước'),
(2, 'Sách giáo khoa'),
(3, 'Tiểu thuyết'),
(4, 'Truyện tranh');

-- --------------------------------------------------------

--
-- Table structure for table `bs_user`
--

CREATE TABLE `bs_user` (
  `id` int(11) NOT NULL,
  `email` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `dob` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bs_user`
--

INSERT INTO `bs_user` (`id`, `email`, `password`, `name`, `dob`) VALUES
(1, 'vietjackteam@gmail.com', '123456', 'KEN PHAM', '1990-05-03 00:00:00'),
(2, 'hoai@gmail.com', 'tieucot', 'TIEU COT', '1997-09-27 00:00:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bs_author`
--
ALTER TABLE `bs_author`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_author_book`
--
ALTER TABLE `bs_author_book`
  ADD PRIMARY KEY (`id`),
  ADD KEY `author_id` (`author_id`,`book_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `bs_book`
--
ALTER TABLE `bs_book`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `bs_category`
--
ALTER TABLE `bs_category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_user`
--
ALTER TABLE `bs_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bs_author`
--
ALTER TABLE `bs_author`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `bs_author_book`
--
ALTER TABLE `bs_author_book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `bs_book`
--
ALTER TABLE `bs_book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `bs_category`
--
ALTER TABLE `bs_category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `bs_user`
--
ALTER TABLE `bs_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bs_author_book`
--
ALTER TABLE `bs_author_book`
  ADD CONSTRAINT `bs_author_book_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `bs_author` (`id`),
  ADD CONSTRAINT `bs_author_book_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `bs_book` (`id`);

--
-- Constraints for table `bs_book`
--
ALTER TABLE `bs_book`
  ADD CONSTRAINT `bs_book_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `bs_category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
