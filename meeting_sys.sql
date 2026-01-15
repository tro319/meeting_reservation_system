-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: meeting_sys
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `interviewers`
--

DROP TABLE IF EXISTS `interviewers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interviewers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `kana` varchar(30) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `pass` varchar(70) NOT NULL,
  `manager_id` int NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `manager_id` (`manager_id`),
  CONSTRAINT `interviewers_ibfk_1` FOREIGN KEY (`manager_id`) REFERENCES `managers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interviewers`
--

LOCK TABLES `interviewers` WRITE;
/*!40000 ALTER TABLE `interviewers` DISABLE KEYS */;
INSERT INTO `interviewers` VALUES (3,'吉田 匠太郎','よしだ しょうたろう','ktc23a32f0005@edu.kyoto-tech.ac.jp','$2a$10$JzLsZWJOcdu1809.CB2SPu6BenScpWhZaQyl2od0Ie/4C0Kjf90CG',1,'2026-01-14 23:09:03','2026-01-14 23:11:59'),(4,'玉谷 知也','たまたに ともや','ktc23a32f0002@edu.kyoto-tech.ac.jp','$2a$10$eyHWhgAsTmuKubCkcWSE0uNwvC0sZ.4HEwuKSTO.3SEPaVH7VyZyK',1,'2026-01-14 23:12:54','2026-01-14 23:12:54');
/*!40000 ALTER TABLE `interviewers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `managers`
--

DROP TABLE IF EXISTS `managers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `managers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `kana` varchar(30) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `pass` varchar(70) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `managers`
--

LOCK TABLES `managers` WRITE;
/*!40000 ALTER TABLE `managers` DISABLE KEYS */;
INSERT INTO `managers` VALUES (1,'吉田 匠太郎','よしだ しょうたろう','ktc23a32f0005@edu.kyoto-tech.ac.jp','$2a$10$Dr7DM6yyPQMiaqsEVcT/re0nIGLoXikFE/Iw8PhFpZFVWT.mmOUgm','2025-12-05 16:54:06','2025-12-05 16:54:06'),(3,'玉谷 知也','たまたに ともや','ktc23a32f0002@edu.kyoto-tech.ac.jp','$2a$10$A7rfPUWASXl29VAWAdPYeug/2uV.a1.mTWM0bnRMFvZcqsJ4Oj66e','2026-01-14 23:06:58','2026-01-14 23:06:58');
/*!40000 ALTER TABLE `managers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `time_id` int NOT NULL,
  `user_id` int NOT NULL,
  `interviewer_id` int NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `time_id` (`time_id`),
  KEY `user_id` (`user_id`),
  KEY `interviewer_id` (`interviewer_id`),
  CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`time_id`) REFERENCES `times` (`id`),
  CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `reservations_ibfk_3` FOREIGN KEY (`interviewer_id`) REFERENCES `interviewers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (9,'2026-01-15',1,2,3,'2026-01-15 19:16:49','2026-01-15 19:16:49'),(11,'2026-01-20',3,2,3,'2026-01-15 21:21:05','2026-01-15 21:21:05'),(12,'2026-01-21',2,4,3,'2026-01-15 21:24:40','2026-01-15 21:24:40');
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `times`
--

DROP TABLE IF EXISTS `times`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `times` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start` int NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `times`
--

LOCK TABLES `times` WRITE;
/*!40000 ALTER TABLE `times` DISABLE KEYS */;
INSERT INTO `times` VALUES (1,10,'2025-12-05 16:56:29','2025-12-05 16:56:29'),(2,15,'2025-12-05 16:56:36','2025-12-05 16:56:36'),(3,17,'2025-12-05 16:56:43','2025-12-05 16:56:43');
/*!40000 ALTER TABLE `times` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `kana` varchar(50) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `pass` varchar(70) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'よたろ','よたろ','ktc23a32f0005@edu.kyoto-tech.ac.jp','$2a$10$vEAB9lZjuImAQuT0N8.nJe26Pup0Gb5lzjbsSey02HbHnwQi716vO','2025-12-05 16:49:46','2025-12-05 16:49:46'),(4,'ともやん','ともやん','ktc23a32f0002@edu.kyoto-tech.ac.jp','$2a$10$xa3X1qB.QPHonXf1Jkp5DeQZbyDckxpdDIjnTEMNbUmB3/Za0Vsou','2026-01-15 21:27:16','2026-01-15 21:30:58');
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

-- Dump completed on 2026-01-15 22:24:05
