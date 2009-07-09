-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	4.1.22-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema ingresosgastos
--

CREATE DATABASE IF NOT EXISTS ingresosgastos;
USE ingresosgastos;

--
-- Definition of table `ingresosgastos`
--

DROP TABLE IF EXISTS `ingresosgastos`;
CREATE TABLE `ingresosgastos` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `fecha` datetime default NULL,
  `tipo` varchar(45) NOT NULL default '',
  `cantidad` varchar(45) NOT NULL default '',
  `concepto` text NOT NULL,
  PRIMARY KEY  USING BTREE (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ingresosgastos`
--

/*!40000 ALTER TABLE `ingresosgastos` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingresosgastos` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
