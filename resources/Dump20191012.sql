CREATE DATABASE  IF NOT EXISTS `examples` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `examples`;
-- MySQL dump 10.13  Distrib 5.7.27
--
-- Host: localhost    Database: examples
-- ------------------------------------------------------
-- Server version	5.7.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `someTable`
--

DROP TABLE IF EXISTS `someTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `someTable` (
  `uid` varchar(255) NOT NULL,
  `timestamp` date NOT NULL,
  `name` varchar(255) NOT NULL,
  `text` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`uid`,`timestamp`,`name`,`description`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `someTable`
--

LOCK TABLES `someTable` WRITE;
/*!40000 ALTER TABLE `someTable` DISABLE KEYS */;
INSERT INTO `someTable` VALUES ('9218fe7e-36d8-439c-bd43-3155f68eaec9','2019-10-12','Article','lorem ipsum','this could use for an article maybe');
INSERT INTO `someTable` VALUES ('9218fe7e-36d8-439c-bd42354t3gwfas3-3155f68eaec9','2018-10-12','Nice1','lorem ipsum','this could use for an article maybe');
INSERT INTO `someTable` VALUES ('9218fe7e33256d8-439c-bd43-3155f68eaec9','2019-10-12','Dummy','lorem ipsum','this could use for an article maybe');
INSERT INTO `someTable` VALUES ('9218fe7e-36d21352523q48-439c-bd43-3155f68eaec9','2016-10-12','data','lorem ipsum','this could use for an article maybe');

/*!40000 ALTER TABLE `someTable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-12  0:46:09
