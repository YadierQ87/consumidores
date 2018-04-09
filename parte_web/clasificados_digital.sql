-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-04-2018 a las 19:30:36
-- Versión del servidor: 10.1.10-MariaDB
-- Versión de PHP: 7.0.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `clasificados_digital`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alta_codigo_dvds`
--

CREATE TABLE `alta_codigo_dvds` (
  `id` int(11) NOT NULL,
  `numero_dvd` varchar(80) NOT NULL,
  `fecha_activacion` date NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `alta_codigo_dvds`
--

INSERT INTO `alta_codigo_dvds` (`id`, `numero_dvd`, `fecha_activacion`, `id_usuario`) VALUES
(1, '18425', '2018-03-07', 1),
(2, '144872', '2018-03-22', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aviso_diario_recibido`
--

CREATE TABLE `aviso_diario_recibido` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha_aviso` date NOT NULL,
  `estado` varchar(50) NOT NULL,
  `tipo_aviso` enum('diario_no_recibo','dvd_no_recibo') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra_dvd`
--

CREATE TABLE `compra_dvd` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `cantd_dvd` int(11) NOT NULL,
  `fecha_compra` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `confirmacion_entrega_dvd`
--

CREATE TABLE `confirmacion_entrega_dvd` (
  `id` int(11) NOT NULL,
  `dir_url_photo_persona` varchar(100) NOT NULL,
  `dir_url_photo_casa` varchar(10) NOT NULL,
  `fecha_retirar_dvd` date NOT NULL,
  `hora_retiro_dvd` varchar(20) NOT NULL,
  `nombre_apellidos` varchar(50) NOT NULL,
  `localidad` varchar(50) NOT NULL,
  `num_celular` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consumo_diario`
--

CREATE TABLE `consumo_diario` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `piso` varchar(50) NOT NULL,
  `dpto` varchar(50) NOT NULL,
  `zip_code` varchar(50) NOT NULL,
  `entre_calle1` varchar(80) NOT NULL,
  `entre_calle2` varchar(80) NOT NULL,
  `provincia` varchar(80) NOT NULL,
  `partido` varchar(80) NOT NULL,
  `localidad` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ubicacion_entrega_dvd`
--

CREATE TABLE `ubicacion_entrega_dvd` (
  `id` int(11) NOT NULL,
  `geo_latitud` float NOT NULL,
  `geo_longitud` float NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_confirmacion_entrega` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_clasificados`
--

CREATE TABLE `usuario_clasificados` (
  `id` int(11) NOT NULL,
  `nro_recomendacion` varchar(20) NOT NULL,
  `nombre_apellidos` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `num_celular` varchar(40) NOT NULL,
  `usuario_nick` varchar(40) NOT NULL,
  `passwd` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario_clasificados`
--

INSERT INTO `usuario_clasificados` (`id`, `nro_recomendacion`, `nombre_apellidos`, `email`, `num_celular`, `usuario_nick`, `passwd`) VALUES
(1, '125', 'Yadier Quesada', 'yadierq87@gmail.com', '55778899', 'admin', 'admin'),
(2, '145', 'Jorge Santos', 'jorge@mail', '4455778', 'jorge', 'jorge'),
(34, '1245', 'Antonio', 'Antonio@mail', '1736689', 'antuan', 'picoleto');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alta_codigo_dvds`
--
ALTER TABLE `alta_codigo_dvds`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `aviso_diario_recibido`
--
ALTER TABLE `aviso_diario_recibido`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `compra_dvd`
--
ALTER TABLE `compra_dvd`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `confirmacion_entrega_dvd`
--
ALTER TABLE `confirmacion_entrega_dvd`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `consumo_diario`
--
ALTER TABLE `consumo_diario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ubicacion_entrega_dvd`
--
ALTER TABLE `ubicacion_entrega_dvd`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario_clasificados`
--
ALTER TABLE `usuario_clasificados`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alta_codigo_dvds`
--
ALTER TABLE `alta_codigo_dvds`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `aviso_diario_recibido`
--
ALTER TABLE `aviso_diario_recibido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `compra_dvd`
--
ALTER TABLE `compra_dvd`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `confirmacion_entrega_dvd`
--
ALTER TABLE `confirmacion_entrega_dvd`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `consumo_diario`
--
ALTER TABLE `consumo_diario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `ubicacion_entrega_dvd`
--
ALTER TABLE `ubicacion_entrega_dvd`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `usuario_clasificados`
--
ALTER TABLE `usuario_clasificados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
