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
-- Table structure for table `recipeingredients`
--

DROP TABLE IF EXISTS `recipeingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipeingredients` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `RECIPE_ID` bigint NOT NULL,
  `INGREDIENT_ID` bigint NOT NULL,
  `INGREDIENT_QTY` decimal(10,4) NOT NULL,
  `INGREDIENT_UNITY` varchar(50) NOT NULL,
  `INGREDIENT_KCAL` decimal(10,6) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKrvds1km6mp0ieskuo3gff32yx` (`INGREDIENT_ID`),
  KEY `FK4p02ktak8ey7o5ntlif7mto1t` (`RECIPE_ID`),
  CONSTRAINT `FK4p02ktak8ey7o5ntlif7mto1t` FOREIGN KEY (`RECIPE_ID`) REFERENCES `recipe` (`id`),
  CONSTRAINT `FKrvds1km6mp0ieskuo3gff32yx` FOREIGN KEY (`INGREDIENT_ID`) REFERENCES `ingredients` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipeingredients`
--

LOCK TABLES `recipeingredients` WRITE;
/*!40000 ALTER TABLE `recipeingredients` DISABLE KEYS */;
INSERT INTO `recipeingredients` VALUES (1,1,10,50.0000,'G',70.410000),(8,1,11,50.0000,'G',132.965000);
/*!40000 ALTER TABLE `recipeingredients` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `recipeingredients_BEFORE_INSERT` BEFORE INSERT ON `recipeingredients` FOR EACH ROW BEGIN
SET NEW.INGREDIENT_KCAL = ((SELECT KCALX100G FROM INGREDIENTS WHERE ID= NEW.INGREDIENT_ID) * NEW.INGREDIENT_QTY)/100;
UPDATE RECIPE SET RECIPE_KCAL = (RECIPE.RECIPE_KCAL + ((SELECT KCALX100G FROM INGREDIENTS WHERE ID= NEW.INGREDIENT_ID) * NEW.INGREDIENT_QTY)/100) WHERE RECIPE.ID = NEW.RECIPE_ID;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `recipeingredients_BEFORE_UPDATE` BEFORE UPDATE ON `recipeingredients` FOR EACH ROW BEGIN
SET NEW.INGREDIENT_KCAL = ((SELECT KCALX100G FROM INGREDIENTS WHERE ID= NEW.INGREDIENT_ID) * NEW.INGREDIENT_QTY)/100;
UPDATE RECIPE SET RECIPE_KCAL = (RECIPE.RECIPE_KCAL + ((SELECT KCALX100G FROM INGREDIENTS WHERE ID= NEW.INGREDIENT_ID) * NEW.INGREDIENT_QTY)/100) WHERE RECIPE.ID = NEW.RECIPE_ID;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `recipeingredients_AFTER_DELETE` AFTER DELETE ON `recipeingredients` FOR EACH ROW BEGIN
UPDATE RECIPE SET RECIPE_KCAL = (RECIPE.RECIPE_KCAL - ((SELECT KCALX100G FROM INGREDIENTS WHERE ID= OLD.INGREDIENT_ID) * OLD.INGREDIENT_QTY)/100) WHERE RECIPE.ID = OLD.RECIPE_ID;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-11 17:07:59
