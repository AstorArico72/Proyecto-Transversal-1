-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-05-2021 a las 01:22:30
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 8.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `universidadgrupo1`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `id_alumno` int(6) NOT NULL COMMENT 'Clave primaria',
  `legajo` int(6) NOT NULL COMMENT 'Legajo del alumno en la universidad',
  `dni` int(11) NOT NULL COMMENT 'Documento del alumno',
  `apellidos` varchar(50) NOT NULL COMMENT 'Apellido/s del alumno',
  `nombres` varchar(50) NOT NULL COMMENT 'Nombre/s del Alumno',
  `edad` tinyint(3) DEFAULT NULL COMMENT 'Edad del alumno',
  `nacimiento` date DEFAULT NULL COMMENT 'Fecha de nacimiento del alumno',
  `domicilio` varchar(250) DEFAULT NULL COMMENT 'Domicilio del alumno',
  `contacto` varchar(20) DEFAULT NULL COMMENT 'Teléfono de contacto del alumno',
  `fecha_inscripcion` date DEFAULT NULL COMMENT 'Fecha de primera inscripción',
  `estado` tinyint(1) DEFAULT 1 COMMENT 'Estados del alumno respecto a la universidad'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Tabla de alumnos';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursada`
--

CREATE TABLE `cursada` (
  `id_cursada` int(6) NOT NULL COMMENT 'Clave primaria',
  `id_alumno` int(6) NOT NULL COMMENT 'Relación con Alumnos',
  `id_materia` int(6) NOT NULL COMMENT 'Relación con Materias',
  `nota` double DEFAULT NULL COMMENT 'Última nota del Alumno en la Materia'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Tabla de relaciones y cursada de alumnos con materias';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materias`
--

CREATE TABLE `materias` (
  `id_materia` int(6) NOT NULL COMMENT 'Clave primaria',
  `nombre` varchar(50) NOT NULL COMMENT 'Nombre de la Materia',
  `año` tinyint(1) NOT NULL COMMENT 'Año al que pertenece, ejemplo: de primer año sería 1, etc',
  `docente` varchar(50) DEFAULT NULL COMMENT 'Nombre del docente',
  `aula` varchar(50) DEFAULT NULL COMMENT 'Dirección o link del aula virtual'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Tabla de materias';

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`id_alumno`),
  ADD UNIQUE KEY `legajo` (`legajo`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `cursada`
--
ALTER TABLE `cursada`
  ADD PRIMARY KEY (`id_cursada`),
  ADD KEY `cursada_alumnos` (`id_alumno`),
  ADD KEY `cursada_materias` (`id_materia`);

--
-- Indices de la tabla `materias`
--
ALTER TABLE `materias`
  ADD PRIMARY KEY (`id_materia`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  MODIFY `id_alumno` int(6) NOT NULL AUTO_INCREMENT COMMENT 'Clave primaria';

--
-- AUTO_INCREMENT de la tabla `cursada`
--
ALTER TABLE `cursada`
  MODIFY `id_cursada` int(6) NOT NULL AUTO_INCREMENT COMMENT 'Clave primaria';

--
-- AUTO_INCREMENT de la tabla `materias`
--
ALTER TABLE `materias`
  MODIFY `id_materia` int(6) NOT NULL AUTO_INCREMENT COMMENT 'Clave primaria';

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cursada`
--
ALTER TABLE `cursada`
  ADD CONSTRAINT `cursada_alumnos` FOREIGN KEY (`id_alumno`) REFERENCES `alumnos` (`id_alumno`),
  ADD CONSTRAINT `cursada_materias` FOREIGN KEY (`id_materia`) REFERENCES `materias` (`id_materia`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
