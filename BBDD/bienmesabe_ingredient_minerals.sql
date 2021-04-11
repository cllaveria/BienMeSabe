-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bienmesabe
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
-- Table structure for table `ingredient_minerals`
--

DROP TABLE IF EXISTS `ingredient_minerals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredient_minerals` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `INGREDIENT_ID` bigint DEFAULT NULL,
  `MINERAL_QTY` decimal(10,6) DEFAULT NULL,
  `MINERAL_ID` bigint NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK4dctsndfdb2fm4uxj2f1tyf5w` (`INGREDIENT_ID`),
  CONSTRAINT `FK4dctsndfdb2fm4uxj2f1tyf5w` FOREIGN KEY (`INGREDIENT_ID`) REFERENCES `ingredients` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient_minerals`
--

LOCK TABLES `ingredient_minerals` WRITE;
/*!40000 ALTER TABLE `ingredient_minerals` DISABLE KEYS */;
INSERT INTO `ingredient_minerals` VALUES (5,10,0.216000,2),(6,10,0.002200,4),(7,10,0.002000,7),(8,10,0.000001,10),(9,11,0.003600,4),(10,11,0.025000,5),(11,11,0.260000,1);
/*!40000 ALTER TABLE `ingredient_minerals` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-11 17:07:59
