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
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `NAME` varchar(75) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TYPE` int DEFAULT NULL,
  `IMAGE` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PREPARATION_VIDEO` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `USER_ID` bigint NOT NULL,
  `RECIPE_CREATEDAT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `RECIPE_ACTIVE` bit(1) NOT NULL DEFAULT b'0',
  `RECIPE_DINNERS` int DEFAULT NULL,
  `RECIPE_KCAL` decimal(32,6) NOT NULL DEFAULT '0.000000',
  `RECIPE_DIFFICULT` int DEFAULT '0',
  `RECIPE_TIME` int DEFAULT '0',
  `RECIPE_ASSESSMENT` int DEFAULT NULL,
  `recipe_ending_description` varchar(255) DEFAULT NULL,
  `recipe_init_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (1,'Huevo frito',2,'/media/huevos-fritos.jpg',NULL,1,'2021-04-10 15:47:20',_binary '',1,414.605000,0,5,5,NULL,NULL),(2,'Huevos estrellados',2,'/media/huevos-estrellados.jpg',NULL,2,'2021-04-12 20:00:44',_binary '',1,400.000000,2,15,1,'Espero que esta receta haya sido de tu agrado y que la disfrutéis tanto como yo compartiéndola.','Receta fácil y sencilla para hacer unos huevos estrellados con jamón que dejaran boquiabiertos a tus comensales.'),(3,'Chocolate a la taza',2,'/media/chocolate-a-la-taza.jpg',NULL,3,'2021-04-13 20:00:44',_binary '',1,531.860000,4,3,0,NULL,''),(4,'Huevo escalfados',1,'/media/huevos-escalfados.jpeg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,5,2,NULL,NULL),(5,'Salmón al horno',3,'/media/salmon-al-horno.jpg',NULL,3,'2021-04-18 15:47:20',_binary '',1,140.820000,3,25,5,NULL,NULL),(6,'Ensalada de aguacate y mozzarella',1,'/media/ensalada-de-aguacate-y-mozzarella.jpg',NULL,2,'2021-04-18 15:47:20',_binary '',1,140.820000,1,10,3,NULL,NULL),(7,'Piruletas crujientes de parmesano',1,'/media/piruletas-crujientes-de-parmesano.jpg',NULL,2,'2021-04-18 15:47:20',_binary '',1,140.820000,2,15,5,NULL,NULL),(8,'Huevo escalfados',1,'/media/entrantes.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(9,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(10,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(11,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(12,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(13,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(14,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(15,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(16,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(17,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(18,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(19,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(20,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(21,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(22,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(23,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(24,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(25,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(26,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(27,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(28,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(29,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL),(30,'Huevo escalfados',1,'/media/huevos-fritos.jpg',NULL,1,'2021-04-18 15:47:20',_binary '',1,140.820000,1,0,2,NULL,NULL);
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-04 19:44:48
