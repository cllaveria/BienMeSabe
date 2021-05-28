-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: bienmesabe
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `nutricionistdegree`
--

DROP TABLE IF EXISTS `nutricionistdegree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nutricionistdegree` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `degree_description` varchar(255) DEFAULT NULL,
  `degree_name` varchar(255) DEFAULT NULL,
  `degree_scool` varchar(255) DEFAULT NULL,
  `degree_year` int DEFAULT NULL,
  `nutricionist_id` bigint DEFAULT NULL,
  `recipe_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlmqk81pnvr4qdelxh598euyv` (`nutricionist_id`),
  KEY `FKi2bto78do1oydc4g8ighs4a8i` (`recipe_id`),
  CONSTRAINT `FKi2bto78do1oydc4g8ighs4a8i` FOREIGN KEY (`recipe_id`) REFERENCES `nutricionists` (`ID`),
  CONSTRAINT `FKlmqk81pnvr4qdelxh598euyv` FOREIGN KEY (`nutricionist_id`) REFERENCES `nutricionists` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nutricionistdegree`
--

LOCK TABLES `nutricionistdegree` WRITE;
/*!40000 ALTER TABLE `nutricionistdegree` DISABLE KEYS */;
INSERT INTO `nutricionistdegree` VALUES (1,'Graduado Universitario en Nutrición Humana y Dietética en el IOC, en la promoción 2020','Graduado Universitario en Nutrición Humana y Dietética','IOC',2020,3,NULL);
/*!40000 ALTER TABLE `nutricionistdegree` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-28 21:04:18
