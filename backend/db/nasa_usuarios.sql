# SQL-Front 5.1  (Build 4.16)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: db405154247.db.1and1.com    Database: db405154247
# ------------------------------------------------------
# Server version 5.1.73-log

#
# Source for table nasa_usuarios
#

DROP TABLE IF EXISTS `nasa_usuarios`;
CREATE TABLE `nasa_usuarios` (
  `nusu_id` int(11) NOT NULL AUTO_INCREMENT,
  `nusu_phoneid` varchar(255) COLLATE latin1_german2_ci DEFAULT NULL,
  `nusu_nombre` varchar(255) COLLATE latin1_german2_ci DEFAULT NULL COMMENT 'Nombre',
  `nusu_password` varchar(255) COLLATE latin1_german2_ci DEFAULT NULL,
  `nusu_nacionalidad` varchar(255) COLLATE latin1_german2_ci DEFAULT NULL,
  `nusu_posx` decimal(15,5) DEFAULT NULL COMMENT 'posicion X',
  `nusu_posy` decimal(15,5) DEFAULT NULL COMMENT 'posicion y',
  `nusu_lastPosChange` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'última modificacion de posicion',
  `nusu_feccre` datetime DEFAULT NULL COMMENT 'Fecha de creación del usuario',
  `nusu_location` tinyint(3) DEFAULT NULL,
  PRIMARY KEY (`nusu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_german2_ci COMMENT='Usuarios';

#
# Dumping data for table nasa_usuarios
#

LOCK TABLES `nasa_usuarios` WRITE;
/*!40000 ALTER TABLE `nasa_usuarios` DISABLE KEYS */;
INSERT INTO `nasa_usuarios` VALUES (0,'locations',NULL,NULL,NULL,NULL,NULL,'2014-04-12 14:05:51',NULL,NULL);
INSERT INTO `nasa_usuarios` VALUES (3,'156745121565',NULL,NULL,NULL,NULL,NULL,'2014-04-12 18:01:15',NULL,1);
INSERT INTO `nasa_usuarios` VALUES (7,'15674515',NULL,NULL,NULL,NULL,NULL,'2014-04-12 18:05:52',NULL,1);
INSERT INTO `nasa_usuarios` VALUES (8,'0',NULL,NULL,NULL,NULL,NULL,'2014-04-12 18:05:59',NULL,0);
INSERT INTO `nasa_usuarios` VALUES (9,'2dc2e73f8d2f41ff',NULL,NULL,NULL,NULL,NULL,'2014-04-13 17:46:35',NULL,0);
INSERT INTO `nasa_usuarios` VALUES (10,'5ef447d071199ba',NULL,NULL,NULL,NULL,NULL,'2014-04-13 16:59:21',NULL,1);
/*!40000 ALTER TABLE `nasa_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
