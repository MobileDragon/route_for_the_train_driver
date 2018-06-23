-- phpMyAdmin SQL Dump
-- version 3.2.1
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Фев 28 2017 г., 15:38
-- Версия сервера: 5.0.45
-- Версия PHP: 5.2.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `ItineraryMachinist`
--

-- --------------------------------------------------------

--
-- Структура таблицы `admins`
--

CREATE TABLE `admins` (
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `station_id` int(4) NOT NULL,
  `id` int(4) NOT NULL,
  PRIMARY KEY  (`login`),
  KEY `station_id` (`station_id`)
) ENGINE=MyISAM DEFAULT CHARSET=cp1251;

--
-- Дамп данных таблицы `admins`
--

INSERT INTO `admins` VALUES('pupkin', '12345', 1, 40);
INSERT INTO `admins` VALUES('junior', '12345', 2, 45);
INSERT INTO `admins` VALUES('krutoi', '12345', 3, 46);
INSERT INTO `admins` VALUES('wing', '1111', 4, 49);
INSERT INTO `admins` VALUES('jeck', '12345', 5, 53);

-- --------------------------------------------------------

--
-- Структура таблицы `alsn`
--

CREATE TABLE `alsn` (
  `itinerary_id` int(12) default NULL,
  `distance` int(4) default NULL,
  `alsn` varchar(3) default NULL,
  `piket` int(2) default NULL,
  `num_section` int(2) default NULL,
  `stoplight` varchar(1) default NULL,
  `N` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`N`),
  KEY `itinerary_id` (`itinerary_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=cp1251 AUTO_INCREMENT=44 ;

--
-- Дамп данных таблицы `alsn`
--

INSERT INTO `alsn` VALUES(13, 3, '2', 2, 2, '4', 43);
INSERT INTO `alsn` VALUES(NULL, 9, '9', 9, 9, '9', 40);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 8);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 9);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 10);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 11);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 12);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 13);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 14);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 15);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 16);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 17);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 18);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 19);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 20);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 21);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 22);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 23);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 24);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 25);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 26);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 27);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 28);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 29);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 30);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 31);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 32);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 33);
INSERT INTO `alsn` VALUES(NULL, 1, '1', 1, 1, '1', 34);
INSERT INTO `alsn` VALUES(13, 3, '2', 2, 2, '4', 42);

-- --------------------------------------------------------

--
-- Структура таблицы `avatars`
--

CREATE TABLE `avatars` (
  `passport_id` char(12) NOT NULL,
  `itinerary_id` int(12) NOT NULL auto_increment,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(26) NOT NULL,
  `admin_id` int(4) NOT NULL,
  PRIMARY KEY  (`login`),
  UNIQUE KEY `itinerary_id` (`itinerary_id`),
  KEY `passport_id` (`passport_id`),
  KEY `admin_id` (`admin_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=cp1251 AUTO_INCREMENT=14 ;

--
-- Дамп данных таблицы `avatars`
--

INSERT INTO `avatars` VALUES('oe805542', 1, '2017-03-09', '0000-00-00', 'asdfsd', 'sdfweew', 53);
INSERT INTO `avatars` VALUES('oe507y35', 2, '2017-03-02', '2017-04-02', 'retre', 'dfsgdfggs', 53);
INSERT INTO `avatars` VALUES('oe805542', 3, '2017-03-02', '2017-04-02', 'r1235', 'werqwer', 53);
INSERT INTO `avatars` VALUES('oe408353', 4, '2017-03-01', '2017-04-01', 'zmey', '12345', 53);
INSERT INTO `avatars` VALUES('oe805542', 5, '2017-03-02', '2017-04-02', 'zmey1', 'werew', 53);
INSERT INTO `avatars` VALUES('oe702353', 6, '2017-03-02', '2017-04-02', 'tyu', 'hjhfjjfg', 40);
INSERT INTO `avatars` VALUES('oe805542', 7, '2017-03-02', '2017-04-02', 'dfh', 'gfhfd', 53);
INSERT INTO `avatars` VALUES('oe805542', 8, '2017-03-02', '2017-04-02', 'hghru', 'ruuffk', 53);
INSERT INTO `avatars` VALUES('oe805542', 9, '2017-03-03', '2017-04-03', 'eruurt', 'ghdgfhd', 53);
INSERT INTO `avatars` VALUES('oe503542', 10, '2017-03-02', '2017-04-02', 'qwet', 'ewrt', 40);
INSERT INTO `avatars` VALUES('oe702353', 11, '2017-03-02', '2017-04-02', 'rebbit', 'ertewr', 40);
INSERT INTO `avatars` VALUES('oe503542', 12, '2017-03-02', '2017-04-02', 'ython', 'dfghdf', 40);
INSERT INTO `avatars` VALUES('oe503542', 13, '2017-02-15', '2017-03-15', 'test', 'test', 40);

-- --------------------------------------------------------

--
-- Структура таблицы `itineraries`
--

CREATE TABLE `itineraries` (
  `identification` int(12) NOT NULL,
  `id` int(12) NOT NULL auto_increment,
  `train_id` int(6) NOT NULL,
  `station_start` char(40) NOT NULL,
  `station_end` char(40) NOT NULL,
  `time_start` datetime NOT NULL,
  `time_end` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `station_end` (`station_end`),
  KEY `train_id` (`train_id`),
  KEY `station_start` (`station_start`)
) ENGINE=MyISAM  DEFAULT CHARSET=cp1251 AUTO_INCREMENT=3 ;

--
-- Дамп данных таблицы `itineraries`
--

INSERT INTO `itineraries` VALUES(12, 1, 2, 'voronej', 'tyhda', '2017-04-02 02:02:17', '2017-04-02 02:44:44');
INSERT INTO `itineraries` VALUES(13, 2, 2, 'voronej', 'tyhda', '2017-03-15 02:03:58', '2017-03-15 02:45:22');

-- --------------------------------------------------------

--
-- Структура таблицы `machinists`
--

CREATE TABLE `machinists` (
  `fio` char(40) NOT NULL,
  `passport_id` char(12) NOT NULL,
  `station_id` int(4) NOT NULL,
  `itinerary_id` int(12) default NULL,
  PRIMARY KEY  (`passport_id`),
  KEY `station_id` (`station_id`),
  KEY `itinerary_id` (`itinerary_id`)
) ENGINE=MyISAM DEFAULT CHARSET=cp1251;

--
-- Дамп данных таблицы `machinists`
--

INSERT INTO `machinists` VALUES('Иван Игоревич', 'oe204235', 1, NULL);
INSERT INTO `machinists` VALUES('Семен Ивасиевич', 'oe503542', 1, NULL);
INSERT INTO `machinists` VALUES('Анатолий Сергеевичь', 'oe702353', 1, NULL);
INSERT INTO `machinists` VALUES('Петр Горыновичь', 'oe507y35', 5, NULL);
INSERT INTO `machinists` VALUES('Каденюк Евтеевич', 'oe805542', 5, NULL);
INSERT INTO `machinists` VALUES('Змей Химерович', 'oe408353', 5, NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `rail_lines`
--

CREATE TABLE `rail_lines` (
  `id` int(8) NOT NULL,
  `length` int(4) NOT NULL,
  `start_id` varchar(8) NOT NULL,
  `end_id` varchar(8) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `start_id` (`start_id`),
  KEY `end_id` (`end_id`)
) ENGINE=MyISAM DEFAULT CHARSET=cp1251;

--
-- Дамп данных таблицы `rail_lines`
--

INSERT INTO `rail_lines` VALUES(1, 40, 'vilka2', 'vilka1');
INSERT INTO `rail_lines` VALUES(3, 70, 'vilka1', 'vilka12');
INSERT INTO `rail_lines` VALUES(4, 50, 'vilka2', 'vilka3');
INSERT INTO `rail_lines` VALUES(2, 40, 'vilka8', 'vilka2');
INSERT INTO `rail_lines` VALUES(5, 20, 'vilka3', 'vilka7');
INSERT INTO `rail_lines` VALUES(6, 40, 'vilka3', 'vilka10');
INSERT INTO `rail_lines` VALUES(7, 10, 'vilka11', 'vilka12');
INSERT INTO `rail_lines` VALUES(8, 25, 'vilka10', 'vilka13');
INSERT INTO `rail_lines` VALUES(9, 30, 'vilka9', 'vilka10');
INSERT INTO `rail_lines` VALUES(10, 70, 'vilka13', 'vilka8');
INSERT INTO `rail_lines` VALUES(11, 1, 'vilka12', 'vilka13');
INSERT INTO `rail_lines` VALUES(12, 1, 'vilka1', 'vilka8');
INSERT INTO `rail_lines` VALUES(18, 3, 'vilka7', 'vilka11');
INSERT INTO `rail_lines` VALUES(20, 6, 'vilka11', 'vilka9');
INSERT INTO `rail_lines` VALUES(72, 7, 'vilka90', 'vilka9');
INSERT INTO `rail_lines` VALUES(70, 2, 'vilka7', 'vilka90');

-- --------------------------------------------------------

--
-- Структура таблицы `stations`
--

CREATE TABLE `stations` (
  `id` int(4) NOT NULL,
  `name` char(40) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=cp1251;

--
-- Дамп данных таблицы `stations`
--

INSERT INTO `stations` VALUES(1, 'voronej');
INSERT INTO `stations` VALUES(2, 'Роздос');
INSERT INTO `stations` VALUES(3, 'verdichev');
INSERT INTO `stations` VALUES(4, 'tyhda');
INSERT INTO `stations` VALUES(5, 'snegowsk');

-- --------------------------------------------------------

--
-- Структура таблицы `stations_position`
--

CREATE TABLE `stations_position` (
  `lines_id` int(8) NOT NULL,
  `stations_id` int(4) NOT NULL,
  `distance` int(4) NOT NULL,
  KEY `lines_id` (`lines_id`),
  KEY `stations_id` (`stations_id`)
) ENGINE=MyISAM DEFAULT CHARSET=cp1251;

--
-- Дамп данных таблицы `stations_position`
--

INSERT INTO `stations_position` VALUES(2, 1, 0);
INSERT INTO `stations_position` VALUES(1, 1, 40);
INSERT INTO `stations_position` VALUES(12, 1, 0);
INSERT INTO `stations_position` VALUES(10, 1, 70);
INSERT INTO `stations_position` VALUES(3, 1, 0);
INSERT INTO `stations_position` VALUES(5, 2, 20);
INSERT INTO `stations_position` VALUES(72, 2, 0);
INSERT INTO `stations_position` VALUES(18, 2, 0);
INSERT INTO `stations_position` VALUES(9, 3, 30);
INSERT INTO `stations_position` VALUES(11, 4, 0);
INSERT INTO `stations_position` VALUES(10, 4, 0);
INSERT INTO `stations_position` VALUES(3, 4, 70);
INSERT INTO `stations_position` VALUES(7, 4, 10);
INSERT INTO `stations_position` VALUES(8, 4, 25);
INSERT INTO `stations_position` VALUES(1, 5, 0);
INSERT INTO `stations_position` VALUES(4, 5, 0);
INSERT INTO `stations_position` VALUES(2, 5, 40);

-- --------------------------------------------------------

--
-- Структура таблицы `trains`
--

CREATE TABLE `trains` (
  `id` int(6) NOT NULL,
  `model` char(20) NOT NULL,
  `last_repair` date default NULL,
  `mileage_repair` int(7) NOT NULL,
  `station_id` int(4) default NULL,
  `itinerary_id` int(12) default NULL,
  `home_depot` varchar(7) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `station_id` (`station_id`),
  KEY `itinerary_id` (`itinerary_id`)
) ENGINE=MyISAM DEFAULT CHARSET=cp1251;

--
-- Дамп данных таблицы `trains`
--

INSERT INTO `trains` VALUES(1, 'EL10', '0000-00-00', 2000000, 1, NULL, 'tch1');
INSERT INTO `trains` VALUES(2, 'EL20', '0000-00-00', 2000000, 1, NULL, 'tch4');
INSERT INTO `trains` VALUES(3, 'OPE1', '0000-00-00', 1500000, 1, NULL, 'tch5');
INSERT INTO `trains` VALUES(4, 'OPE1A', '0000-00-00', 1500000, 5, NULL, 'tch6');
INSERT INTO `trains` VALUES(5, 'NP1', '0000-00-00', 3000000, 5, NULL, 'tch8');

-- --------------------------------------------------------

--
-- Структура таблицы `way`
--

CREATE TABLE `way` (
  `itinerary_id` int(12) NOT NULL,
  `lines_id` int(8) NOT NULL,
  `numder` int(3) NOT NULL,
  `station_visit` int(4) NOT NULL,
  KEY `itinerary_id` (`itinerary_id`),
  KEY `lines_id` (`lines_id`),
  KEY `station_visit` (`station_visit`)
) ENGINE=MyISAM DEFAULT CHARSET=cp1251;

--
-- Дамп данных таблицы `way`
--


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
