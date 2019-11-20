CREATE DATABASE  IF NOT EXISTS `rental` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `rental`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: rental
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `emails`
--

DROP TABLE IF EXISTS `emails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `emails` (
  `to` int(11) NOT NULL COMMENT 'the landlordID of whom the email was sent to',
  `from` varchar(45) NOT NULL COMMENT 'the email or phone number of who sent email',
  `text` longtext NOT NULL,
  KEY `fk_to_idx` (`to`),
  CONSTRAINT `fk_to` FOREIGN KEY (`to`) REFERENCES `landlords` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emails`
--

LOCK TABLES `emails` WRITE;
/*!40000 ALTER TABLE `emails` DISABLE KEYS */;
INSERT INTO `emails` VALUES (1003,'567-378-3333','helllo my name is pendjcod'),(1003,'222-333-5676','hello melvins a fag'),(1003,'654345678','wertre'),(1003,'hgfdgfd','ytrejhgfdhgf'),(1002,'587-289-2222','lemme have ur mansion'),(1001,'420-420-6969','hello can i see ur cool house pls'),(1003,'yahoooo','helo me abent'),(1003,'abnegt','gimme house hdsj		'),(1002,'ur mumjot','I am client server yahoooo'),(1003,'ur moms moms dada','yuhaaaaa	');
/*!40000 ALTER TABLE `emails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `landlords`
--

DROP TABLE IF EXISTS `landlords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `landlords` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `landlordusername` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_landlordusername_idx` (`landlordusername`),
  CONSTRAINT `fk` FOREIGN KEY (`landlordusername`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='table of landlords';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `landlords`
--

LOCK TABLES `landlords` WRITE;
/*!40000 ALTER TABLE `landlords` DISABLE KEYS */;
INSERT INTO `landlords` VALUES (1001,'Ben Dover','ben@gmail.com','benDover'),(1002,'Hugh Mungus','hugh@hotmail.com','yahugh'),(1003,'Avnet Bill','avnet@gmail.com','abn8t'),(1004,'thicc','thicc@uni.ca','thicco');
/*!40000 ALTER TABLE `landlords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `properties`
--

DROP TABLE IF EXISTS `properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `properties` (
  `id` int(11) NOT NULL,
  `type` varchar(45) NOT NULL COMMENT 'the rental type such as Apartment, attached/detached house, townhouse, etc.',
  `bedrooms` int(11) NOT NULL,
  `bathrooms` int(11) NOT NULL,
  `quadrant` varchar(45) NOT NULL COMMENT 'city quadrant like NE',
  `furnished` tinyint(1) NOT NULL COMMENT '1 == furnished, 0 == unfurnished',
  `landlordID` int(11) NOT NULL,
  `state` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_landlord_id_idx` (`landlordID`),
  CONSTRAINT `fk_landlord_id` FOREIGN KEY (`landlordID`) REFERENCES `landlords` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='stores all properties on the system';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `properties`
--

LOCK TABLES `properties` WRITE;
/*!40000 ALTER TABLE `properties` DISABLE KEYS */;
INSERT INTO `properties` VALUES (1,'apartment',3,1,'SE',0,1003,'active'),(2,'attached house',4,1,'NE',1,1002,'active'),(3,'detached house',3,2,'NW',0,1001,'cancelled'),(4,'bungalow',2,1,'NE',1,1003,'suspended'),(5,'townhome',2,2,'SE',0,1001,'active'),(6,'mansion',10,5,'SW',1,1002,'active'),(7,'mansion',30,22,'NW',0,1002,'rented'),(8,'attached house',22,2,'SE',0,1003,'active'),(9,'mansion',22,87,'SW',0,1004,'active'),(10,'detached house',22,33,'NW',0,1004,'active');
/*!40000 ALTER TABLE `properties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscribes`
--

DROP TABLE IF EXISTS `subscribes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `subscribes` (
  `subusername` varchar(45) NOT NULL,
  `housetype` varchar(45) NOT NULL,
  `furnished` varchar(45) NOT NULL,
  `beds` int(11) NOT NULL,
  `baths` int(11) NOT NULL,
  `quadrant` varchar(45) NOT NULL,
  KEY `fk_regrenter_idx` (`subusername`),
  CONSTRAINT `fk_regrenter` FOREIGN KEY (`subusername`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='this is the regrenter''s subscribed searches';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscribes`
--

LOCK TABLES `subscribes` WRITE;
/*!40000 ALTER TABLE `subscribes` DISABLE KEYS */;
INSERT INTO `subscribes` VALUES ('melbin','mansion','Furnished',69,77,'SW'),('pandi','bungalow','Unfurnished',1,2,'NE'),('pandi','any','any',-1,-1,'any');
/*!40000 ALTER TABLE `subscribes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `usertype` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='rental system users';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('2345','23456','23456','23456','regrenter'),('abn8t','1111','anvet@gmail.com','Avnet Bill','landlord'),('asd','asd','asd','asd','landlord'),('benDover','8002','ben@gmail.com','Ben Dover','landlord'),('melbin','2019','melvin@gmail.com','Melvin Wang','regrenter'),('pandi','yu','pendi@yahoo.ca','pandi','regrenter'),('rahman','5870','admin@rental.com','Admin','manager'),('thicco','thick','thicc@uni.ca','thicc','Landlord'),('timmyt','1234','timmy@turner.ca','Timmy Turner','regrenter'),('tyytt','1234','eyyey','ytytyt','landlord'),('yahiuii','hhhh2','ytahhh@abnet.ca','yahiuii','regrenter'),('yahugh','2008','hugh@hotmail.com','Hugh Mungus','landlord');
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

-- Dump completed on 2019-11-19 19:58:07
