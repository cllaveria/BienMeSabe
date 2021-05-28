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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `USER_NIF` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `USER_IMAGE` varchar(200) DEFAULT NULL,
  `USER_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `USER_SURNAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `USER_PASSWORD` varchar(200) NOT NULL,
  `USER_ALIAS` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `USER_EMAIL` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `USER_PHONE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `USER_TYPE` int NOT NULL,
  `USER_CREATEDAT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `USER_UPDATEDAT` timestamp NULL DEFAULT NULL,
  `USER_ACTIVE` int NOT NULL DEFAULT '1',
  `token` varchar(255) DEFAULT NULL,
  `token_end_validity_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'',NULL,'Raul','Ramos','8e5aa8e6e17f55b693dc355c7beded18','Starlord','sergio@ioc.es','650545473',1,'2021-03-17 20:46:04','2021-04-19 17:04:03',1,NULL,NULL),(2,'',NULL,'Admin','','8e5aa8e6e17f55b693dc355c7beded18','Admin','bienmesabedaw@gmail.com',NULL,3,'2021-05-24 13:15:17','2021-05-24 13:15:17',0,NULL,NULL),(3,'',NULL,'Sergio','Asensio Ruiz','8e5aa8e6e17f55b693dc355c7beded18','SergioAsensio','serasensio@gmail.com','',2,'2021-05-16 16:13:16','2021-05-24 12:15:22',1,NULL,NULL),(4,'',NULL,'User1','','8e5aa8e6e17f55b693dc355c7beded18','user1','user1@ioc.es',NULL,1,'2021-05-28 14:45:33','2021-05-28 14:45:33',0,NULL,NULL),(5,'',NULL,'User2','','8e5aa8e6e17f55b693dc355c7beded18','user2','user2@ioc.es',NULL,1,'2021-05-28 14:46:06','2021-05-28 14:46:06',0,NULL,NULL),(68,'C88643952',NULL,'Nutricionista1','','8e5aa8e6e17f55b693dc355c7beded18','nutricionista1','nutricionista1@ioc.es',NULL,2,'2021-05-28 14:47:26','2021-05-28 14:47:26',0,NULL,NULL),(69,'Y5965024B',NULL,'Nutricionista2','','8e5aa8e6e17f55b693dc355c7beded18','nutricionista2','nutricionista2@ioc.es',NULL,2,'2021-05-28 14:48:13','2021-05-28 14:48:13',0,NULL,NULL),(70,'66258030K',NULL,'Nutricionista3','','8e5aa8e6e17f55b693dc355c7beded18','nutricionista3','nutricionista3@ioc.es',NULL,2,'2021-05-28 14:52:06','2021-05-28 14:52:06',0,NULL,NULL),(74,'',NULL,'Sergio','','8e5aa8e6e17f55b693dc355c7beded18','UserIOC','userioc123@ioc.es',NULL,1,'2021-05-28 16:49:59','2021-05-28 16:49:59',0,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-28 21:04:19
