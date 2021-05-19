-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-05-2021 a las 21:39:57
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
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE `alumno` (
  `ID_Alumno` int(11) NOT NULL COMMENT 'Llave primaria',
  `Nombre` varchar(50) NOT NULL COMMENT 'El nombre y el apellido en una sola columna. Me parece que 50 caracteres alcanzan.',
  `Legajo` int(6) NOT NULL COMMENT 'Ó "ID_Alumno". La llave primaria.',
  `Estado` tinyint(1) DEFAULT 1 COMMENT 'Estado del alumno',
  `FechaNacimiento` date NOT NULL COMMENT 'También se puede reemplazar con un número entero para la edad.',
  `Correo` text DEFAULT NULL COMMENT 'No es obligatorio llenar ésto.',
  `Teléfono` text DEFAULT NULL COMMENT 'Tampoco ésto.',
  `FechaInscripción` date DEFAULT NULL COMMENT 'Fecha de inscripción a la universidad',
  `Comentarios` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='No creo que necesitemos más datos para ésta tabla.';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscripcion`
--

CREATE TABLE `inscripcion` (
  `ID_Inscripcion` int(11) NOT NULL COMMENT 'Clave primaria',
  `ID_Alumno` int(11) NOT NULL,
  `ID_Materia` int(11) NOT NULL,
  `FechaInscripcion` date NOT NULL,
  `Nota` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materia`
--

CREATE TABLE `materia` (
  `ID_Materia` int(11) NOT NULL COMMENT 'El ID de la materia, o número de clase.',
  `Nombre` varchar(50) NOT NULL COMMENT '¿50 caracteres alcanzan?',
  `Año` tinyint(1) NOT NULL COMMENT 'Ésto podría omitirse si el primer dígito del ID es el año (ie "201" para una materia del segundo año).',
  `Estado` tinyint(1) NOT NULL COMMENT 'Estado de la materia'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Podríamos añadir un campo más para el nombre del profesor.';

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`ID_Alumno`),
  ADD UNIQUE KEY `Legajo` (`Legajo`);

--
-- Indices de la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  ADD PRIMARY KEY (`ID_Inscripcion`),
  ADD UNIQUE KEY `Id_Alumno` (`ID_Alumno`),
  ADD UNIQUE KEY `Id_Materia` (`ID_Materia`);

--
-- Indices de la tabla `materia`
--
ALTER TABLE `materia`
  ADD PRIMARY KEY (`ID_Materia`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumno`
--
ALTER TABLE `alumno`
  MODIFY `ID_Alumno` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria', AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  MODIFY `ID_Inscripcion` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Clave primaria';

--
-- AUTO_INCREMENT de la tabla `materia`
--
ALTER TABLE `materia`
  MODIFY `ID_Materia` int(11) NOT NULL AUTO_INCREMENT COMMENT 'El ID de la materia, o número de clase.', AUTO_INCREMENT=102;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  ADD CONSTRAINT `Inscripcion Alumno` FOREIGN KEY (`ID_Alumno`) REFERENCES `alumno` (`ID_Alumno`),
  ADD CONSTRAINT `Inscripcion Materia` FOREIGN KEY (`ID_Materia`) REFERENCES `materia` (`ID_Materia`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
