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
-- Table structure for table `recipesteps`
--

DROP TABLE IF EXISTS `recipesteps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipesteps` (
  `STEP_ID` bigint NOT NULL AUTO_INCREMENT,
  `RECIPE_ID` bigint NOT NULL,
  `STEP_DESCRIPTION` varchar(1000) NOT NULL,
  `step_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`STEP_ID`),
  KEY `FKlm7xl8bcrq3of6wrfq0kv65xy` (`RECIPE_ID`),
  CONSTRAINT `FKlm7xl8bcrq3of6wrfq0kv65xy` FOREIGN KEY (`RECIPE_ID`) REFERENCES `recipe` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipesteps`
--

LOCK TABLES `recipesteps` WRITE;
/*!40000 ALTER TABLE `recipesteps` DISABLE KEYS */;
INSERT INTO `recipesteps` VALUES (1,1,'PONER ACEITE EN LA SARTÉN',NULL),(2,1,'ENCENDER EL FUEGO',NULL),(3,1,'ESPERAR A QUE EL ACEITE SE CALIENTE',NULL),(4,1,'CASCAR EL HUEVO',NULL),(5,1,'INTRODUCIR EL HUEVO EN LA SARTÉN',NULL),(6,1,'SALPICAR EL HUEVO CON EL ACEITE',NULL),(7,1,'RETIRAR EL HUEVO, APAGAR EL FUEGO Y PRESENTAR',NULL),(8,2,'Freímos las patatas','/media/huevos-estrellados-paso-1.jpg'),(9,2,'Añadimos el jamón a las patatas','/media/huevos-estrellados-paso-2.jpg'),(10,2,'Freímos los huevos','/media/huevos-estrellados-paso-3.jpg'),(11,2,'Rompemos los huevos estrellados','/media/huevos-estrellados-paso-4.jpg');
/*!40000 ALTER TABLE `recipesteps` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-02 22:59:13
