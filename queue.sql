-- phpMyAdmin SQL Dump
-- version 3.2.3
-- http://www.phpmyadmin.net
--
-- ȣ��Ʈ: localhost
-- ó���� �ð�: 16-06-04 02:28 
-- ���� ����: 5.1.41
-- PHP ����: 5.2.12

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- �����ͺ��̽�: `ticket`
--

-- --------------------------------------------------------

--
-- ���̺� ���� `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `name` varchar(20) NOT NULL,
  `age` int(3) NOT NULL DEFAULT '0',
  `seat` varchar(20) NOT NULL DEFAULT '0',
  `q1` int(1) NOT NULL DEFAULT '0',
  `q2` int(1) NOT NULL DEFAULT '0',
  `status` int(1) NOT NULL DEFAULT '0',
  `ready` int(1) NOT NULL DEFAULT '0',
  `time` varchar(30) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=euckr;

--
-- ���̺��� ���� ������ `student`
--

INSERT INTO `student` (`name`, `age`, `seat`, `q1`, `q2`, `status`, `ready`, `time`) VALUES
('a', 12, '13', 0, 0, 0, 0, '2016-06-04 02:09:14'),
('uy', 5, '56', 0, 0, 0, 0, '2016-06-04 02:06:08'),
('pp', 2, '13', 0, 0, 0, 0, '2016-06-04 01:49:30'),
('as', 13, '13', 0, 0, 0, 0, '2016-06-04 02:12:24'),
('hj', 45, '34', 0, 0, 0, 0, '2016-06-04 02:13:40');

-- --------------------------------------------------------

--
-- ���̺� ���� `teacher`
--

CREATE TABLE IF NOT EXISTS `teacher` (
  `no` int(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `age` int(3) NOT NULL,
  `available` int(1) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM DEFAULT CHARSET=euckr;

--
-- ���̺��� ���� ������ `teacher`
--

INSERT INTO `teacher` (`no`, `name`, `age`, `available`) VALUES
(1, 'bail', 25, 1),
(2, 'cril', 30, 0),
(3, 'obri', 27, 0),
(4, 'queen', 26, 0);