-- phpMyAdmin SQL Dump
-- version 2.11.8.1deb5+lenny9
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 22-11-2011 a las 10:01:42
-- Versión del servidor: 5.0.51
-- Versión de PHP: 5.2.6-1+lenny13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `series`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `capitulos`
--

CREATE TABLE IF NOT EXISTS `capitulos` (
  `id` int(11) NOT NULL auto_increment,
  `idtemporada` int(11) NOT NULL,
  `numero` varchar(50) collate utf8_unicode_ci NOT NULL,
  `titulo` varchar(255) collate utf8_unicode_ci NOT NULL,
  `fecha` varchar(20) collate utf8_unicode_ci NOT NULL,
  `url` varchar(255) collate utf8_unicode_ci NOT NULL,
  `terminado` tinyint(4) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `capitulos`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fichero`
--

CREATE TABLE IF NOT EXISTS `fichero` (
  `id` int(11) NOT NULL auto_increment,
  `idcapitulo` int(11) NOT NULL,
  `servidor` varchar(50) collate utf8_unicode_ci default NULL,
  `autor` varchar(100) collate utf8_unicode_ci NOT NULL,
  `audio` varchar(50) collate utf8_unicode_ci NOT NULL,
  `subtitulos` varchar(50) collate utf8_unicode_ci NOT NULL,
  `calidad` varchar(30) collate utf8_unicode_ci NOT NULL,
  `formato` varchar(100) collate utf8_unicode_ci NOT NULL,
  `url` varchar(255) collate utf8_unicode_ci NOT NULL,
  `urlfinal` varchar(255) collate utf8_unicode_ci default NULL,
  `terminado` tinyint(4) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `fichero`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `indice`
--

CREATE TABLE IF NOT EXISTS `indice` (
  `id` varchar(3) collate utf8_unicode_ci NOT NULL,
  `terminado` tinyint(4) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcar la base de datos para la tabla `indice`
--

INSERT INTO `indice` (`id`, `terminado`) VALUES
('a', 0),
('b', 0),
('c', 0),
('d', 0),
('e', 0),
('f', 0),
('g', 0),
('h', 0),
('i', 0),
('j', 0),
('k', 0),
('l', 0),
('m', 0),
('n', 0),
('o', 0),
('p', 0),
('q', 0),
('r', 0),
('s', 0),
('t', 0),
('u', 0),
('v', 0),
('w', 0),
('x', 0),
('y', 0),
('z', 0),
('0-9', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `series`
--

CREATE TABLE IF NOT EXISTS `series` (
  `id` int(11) NOT NULL auto_increment,
  `nombre` varchar(100) collate utf8_unicode_ci NOT NULL,
  `descripcion` text collate utf8_unicode_ci,
  `fecha` year(4) default NULL,
  `pagina` varchar(255) collate utf8_unicode_ci default NULL,
  `url` varchar(255) collate utf8_unicode_ci NOT NULL,
  `terminado` tinyint(4) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `series`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `temporadas`
--

CREATE TABLE IF NOT EXISTS `temporadas` (
  `id` int(11) NOT NULL auto_increment,
  `idserie` int(11) NOT NULL,
  `nombre` varchar(100) collate utf8_unicode_ci default NULL,
  `numero` varchar(10) collate utf8_unicode_ci default NULL,
  `fecha` varchar(20) collate utf8_unicode_ci default NULL,
  `url` varchar(255) collate utf8_unicode_ci default NULL,
  `terminado` tinyint(4) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `temporadas`
--

