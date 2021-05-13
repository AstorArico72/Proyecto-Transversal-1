-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 13-05-2021 a las 17:24:11
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
-- Base de datos: `Universidad`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Alumno`
--

CREATE TABLE `Alumno` (
  `Nombre` varchar(50) NOT NULL COMMENT 'El nombre y el apellido en una sola columna. Me parece que 50 caracteres alcanzan.',
  `Legajo` int(6) NOT NULL COMMENT 'Ó "ID_Alumno". La llave primaria.',
  `Aprobado` tinyint(1) NOT NULL,
  `FechaNacimiento` date NOT NULL COMMENT 'También se puede reemplazar con un número entero para la edad.',
  `Correo` text DEFAULT NULL COMMENT 'No es obligatorio llenar ésto.',
  `Teléfono` text DEFAULT NULL COMMENT 'Tampoco ésto.',
  `FechaInscripción` date NOT NULL,
  `Comentarios` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='No creo que necesitemos más datos para ésta tabla.';

--
-- Volcado de datos para la tabla `Alumno`
--

INSERT INTO `Alumno` (`Nombre`, `Legajo`, `Aprobado`, `FechaNacimiento`, `Correo`, `Teléfono`, `FechaInscripción`, `Comentarios`) VALUES
('Jay Smith', 73229, 0, '2001-04-01', NULL, NULL, '2019-03-01', 'Primera muestra de datos.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Inscripción`
--

CREATE TABLE `Inscripción` (
  `Legajo` int(6) NOT NULL COMMENT 'Exportado de Alumno.',
  `ID_Materia` int(3) NOT NULL COMMENT 'Exportado de Materia.',
  `FechaInscripción` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `Inscripción`
--

INSERT INTO `Inscripción` (`Legajo`, `ID_Materia`, `FechaInscripción`) VALUES
(73229, 101, '2019-03-04');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Materia`
--

CREATE TABLE `Materia` (
  `ID_Materia` int(3) NOT NULL COMMENT 'El ID de la materia, o número de clase.',
  `Nombre` varchar(50) NOT NULL COMMENT '¿50 caracteres alcanzan?',
  `Año` tinyint(1) NOT NULL COMMENT 'Ésto podría omitirse si el primer dígito del ID es el año (ie "201" para una materia del segundo año).'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Podríamos añadir un campo más para el nombre del profesor.';

--
-- Volcado de datos para la tabla `Materia`
--

INSERT INTO `Materia` (`ID_Materia`, `Nombre`, `Año`) VALUES
(101, 'Matemáticas I', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Alumno`
--
ALTER TABLE `Alumno`
  ADD PRIMARY KEY (`Legajo`);

--
-- Indices de la tabla `Inscripción`
--
ALTER TABLE `Inscripción`
  ADD KEY `El legajo aquí es el legajo allá` (`Legajo`),
  ADD KEY `La ID de la materia aquí es la ID de la materia allá.` (`ID_Materia`);

--
-- Indices de la tabla `Materia`
--
ALTER TABLE `Materia`
  ADD PRIMARY KEY (`ID_Materia`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Inscripción`
--
ALTER TABLE `Inscripción`
  ADD CONSTRAINT `El legajo aquí es el legajo allá` FOREIGN KEY (`Legajo`) REFERENCES `Alumno` (`Legajo`),
  ADD CONSTRAINT `La ID de la materia aquí es la ID de la materia allá.` FOREIGN KEY (`ID_Materia`) REFERENCES `Materia` (`ID_Materia`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
