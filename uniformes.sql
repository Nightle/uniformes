-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-02-2022 a las 01:53:33
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `uniformes`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario_camisas`
--

CREATE TABLE `inventario_camisas` (
  `talla` varchar(20) NOT NULL,
  `cantidad_cc_red` varchar(20) NOT NULL,
  `cantidad_cc_per` varchar(20) NOT NULL,
  `cantidad_ac_red` varchar(20) NOT NULL,
  `cantidad_ac_v` varchar(20) NOT NULL,
  `cantidad_ac_per` varchar(20) NOT NULL,
  `codigo` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `inventario_camisas`
--

INSERT INTO `inventario_camisas` (`talla`, `cantidad_cc_red`, `cantidad_cc_per`, `cantidad_ac_red`, `cantidad_ac_v`, `cantidad_ac_per`, `codigo`) VALUES
('Talla 10', '4', '3', '6', '4', '6', 10),
('Talla 8', '4', '5', '3', '2', '6', 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario_chaquetas`
--

CREATE TABLE `inventario_chaquetas` (
  `talla` varchar(20) NOT NULL,
  `cantidad` varchar(20) NOT NULL,
  `codigo` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `inventario_chaquetas`
--

INSERT INTO `inventario_chaquetas` (`talla`, `cantidad`, `codigo`) VALUES
('Talla 10', '9', 10),
('Talla 8', '5', 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario_sudaderas`
--

CREATE TABLE `inventario_sudaderas` (
  `talla` varchar(20) NOT NULL,
  `cantidadcc` varchar(40) NOT NULL,
  `cantidadac` varchar(20) NOT NULL,
  `codigo` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `inventario_sudaderas`
--

INSERT INTO `inventario_sudaderas` (`talla`, `cantidadcc`, `cantidadac`, `codigo`) VALUES
('Talla 10', '5', '6', 10),
('Talla 8', '3', '4', 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tallas`
--

CREATE TABLE `tallas` (
  `codigo` int(20) NOT NULL,
  `talla` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tallas`
--

INSERT INTO `tallas` (`codigo`, `talla`) VALUES
(8, 'Talla 8'),
(10, 'Talla 10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `usuario` varchar(90) NOT NULL,
  `contraseña` varchar(90) NOT NULL,
  `descripcion` varchar(90) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`usuario`, `contraseña`, `descripcion`) VALUES
('Admin', '123456', 'el admin');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `inventario_camisas`
--
ALTER TABLE `inventario_camisas`
  ADD PRIMARY KEY (`talla`),
  ADD KEY `codigo` (`codigo`);

--
-- Indices de la tabla `inventario_chaquetas`
--
ALTER TABLE `inventario_chaquetas`
  ADD PRIMARY KEY (`talla`),
  ADD KEY `codigo` (`codigo`);

--
-- Indices de la tabla `inventario_sudaderas`
--
ALTER TABLE `inventario_sudaderas`
  ADD PRIMARY KEY (`talla`),
  ADD KEY `codigo` (`codigo`);

--
-- Indices de la tabla `tallas`
--
ALTER TABLE `tallas`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`usuario`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `inventario_camisas`
--
ALTER TABLE `inventario_camisas`
  ADD CONSTRAINT `inventario_camisas_ibfk_1` FOREIGN KEY (`codigo`) REFERENCES `tallas` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `inventario_chaquetas`
--
ALTER TABLE `inventario_chaquetas`
  ADD CONSTRAINT `inventario_chaquetas_ibfk_1` FOREIGN KEY (`codigo`) REFERENCES `tallas` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `inventario_sudaderas`
--
ALTER TABLE `inventario_sudaderas`
  ADD CONSTRAINT `inventario_sudaderas_ibfk_1` FOREIGN KEY (`codigo`) REFERENCES `tallas` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
