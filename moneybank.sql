-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: moneybank
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `adminlogin`
--

DROP TABLE IF EXISTS `adminlogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adminlogin` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adminlogin`
--

LOCK TABLES `adminlogin` WRITE;
/*!40000 ALTER TABLE `adminlogin` DISABLE KEYS */;
INSERT INTO `adminlogin` VALUES ('Amal','5678'),('Janith','7890'),('Kamal','1234');
/*!40000 ALTER TABLE `adminlogin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currentacc`
--

DROP TABLE IF EXISTS `currentacc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currentacc` (
  `IniName` varchar(50) DEFAULT NULL,
  `FullName` varchar(100) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `NIC` varchar(20) NOT NULL,
  `Purpose` varchar(100) DEFAULT NULL,
  `UserName` varchar(50) DEFAULT NULL,
  `Action` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`NIC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currentacc`
--

LOCK TABLES `currentacc` WRITE;
/*!40000 ALTER TABLE `currentacc` DISABLE KEYS */;
INSERT INTO `currentacc` VALUES ('V Jayawardana','Vikasitha Jayawardana','Melegoda Galle','1996584502','Apply Business Loan','Vikasitha',NULL),('S P A Jayawardana','Shanuka Pasindu Jayawardana','Paragahawatta Angulugaha','199984572V','Apply Check Book','Pasindu',NULL),('S C A Jayawardana','Sasindu Chandupa Abesekara Jayawardana','Meepawala Galle','200224103177','Apply Check Book','Sasindu',NULL);
/*!40000 ALTER TABLE `currentacc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `savingacc`
--

DROP TABLE IF EXISTS `savingacc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `savingacc` (
  `IniName` varchar(255) DEFAULT NULL,
  `FullName` varchar(255) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `NIC` varchar(20) NOT NULL,
  `Purpose` varchar(255) DEFAULT NULL,
  `UserName` varchar(255) DEFAULT NULL,
  `Action` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`NIC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `savingacc`
--

LOCK TABLES `savingacc` WRITE;
/*!40000 ALTER TABLE `savingacc` DISABLE KEYS */;
INSERT INTO `savingacc` VALUES ('S C A Jayawardana','Sasindu Chandupa Abesekara Jayawardana','192, GreenView','-1452733303','Save Money','sasii',NULL),('M M D A Ranathunga','Dihani Anudi Ranathunga','Pahanya Weligama Matara','-1639349735','Apply Vehicle Loan','Anu',NULL),('K K U Shriyani','Katipe Kankanamge Uresha Shriyani','192, Green view, Meepawala, Poddala','-553227126','Apply Gold Loan','Uresha',NULL),('M M Y Ranathunga','Yenuli Ranathunga','Weligama GAlle','2005156520V','Save Money','Yenu',NULL);
/*!40000 ALTER TABLE `savingacc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbllogin`
--

DROP TABLE IF EXISTS `tbllogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbllogin` (
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `action` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbllogin`
--

LOCK TABLES `tbllogin` WRITE;
/*!40000 ALTER TABLE `tbllogin` DISABLE KEYS */;
INSERT INTO `tbllogin` VALUES ('sasindu','111',NULL),('anu','111',NULL),('sasii','555',NULL),('Sasi','1010',NULL),('sasi','1212',NULL),('sasii','1234',NULL),('dada','22',NULL),('ppp','2020',NULL),('Anu','5050',NULL),('Uresha','1230',NULL),('Yenu','50',NULL),('Shyama','230',NULL),('Sasindu','111',NULL),('Vikasitha','150',NULL);
/*!40000 ALTER TABLE `tbllogin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `transaction_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Balance` decimal(10,2) DEFAULT NULL,
  `LastTransactionAccNo` int DEFAULT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'Anu','2024-08-17 13:38:54',496750.00,520136021),(2,'Uresha','2024-08-18 03:48:43',500000.00,NULL),(3,'Shyama','2024-08-18 04:35:15',600000.00,NULL),(4,'Sasindu','2024-08-18 04:46:17',800000.00,244187851),(5,'Vikasitha','2024-08-19 13:41:37',198200.00,1510545054);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-31 13:36:00
