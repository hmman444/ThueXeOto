-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: thuexe
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,NULL,'2025-05-01 08:00:00.000000',_binary '',_binary '\0','123 Nguyễn Huệ, Phường Bến Nghé, Quận 1','2025-05-10 10:00:00.000000',_binary '','456 Lê Lợi, Phường Bến Thành, Quận 1','2025-05-01 08:00:00.000000','Reviewed',2000000,'2025-05-11 11:00:00.000000',12,1),(2,NULL,'2025-05-05 09:00:00.000000',_binary '\0',_binary '','789 đường Lý Thường Kiệt, phường 6, Quận 11','2025-05-14 18:00:00.000000',_binary '\0','123 đường Tôn Đản, phường 4, Quận 4','2025-05-05 09:00:00.000000','Reviewed',1500000,'2025-05-15 09:00:00.000000',20,4),(3,NULL,'2025-05-12 15:00:00.000000',_binary '',_binary '\0','123 đường Nguyễn Đình Chiểu, phường 6, Quận 3','2025-05-16 18:00:00.000000',_binary '','456 đường Cao Thắng, phường 5, Quận 6','2025-05-12 15:00:00.000000','InProgress',3200000,'2025-05-14 18:00:00.000000',26,2),(4,NULL,'2025-05-13 14:00:00.000000',_binary '\0',_binary '','789 đường Nguyễn Văn Linh, phường Tân Phong, Quận 7','2025-05-18 12:00:00.000000',_binary '\0','123 đường Lý Thường Kiệt, phường 6, Quận 11','2025-05-13 14:00:00.000000','InProgress',2500000,'2025-05-15 14:00:00.000000',33,3),(5,NULL,'2025-05-16 08:00:00.000000',_binary '',_binary '\0','456 đường Hồng Bàng, phường 1, Quận 5','2025-05-20 10:00:00.000000',_binary '','123 đường Nguyễn Văn Linh, phường Linh Chiểu, TP Thủ Đức','2025-05-16 08:00:00.000000','Pending',1800000,'2025-05-16 08:00:00.000000',28,5),(6,NULL,'2025-05-14 10:00:00.000000',_binary '\0',_binary '','789 đường Hoàng Diệu, phường Linh Đông, TP Thủ Đức','2025-05-17 12:00:00.000000',_binary '\0','456 đường Tân Kỳ Tân Quý, phường Tân Sơn Nhì, Quận Tân Phú','2025-05-14 10:00:00.000000','InProgress',3500000,'2025-05-15 12:00:00.000000',34,4),(7,NULL,'2025-05-15 09:00:00.000000',_binary '',_binary '\0','123 đường Quang Trung, phường 8, Quận Gò Vấp','2025-05-16 18:00:00.000000',_binary '','456 đường Nguyễn Hữu Cảnh, phường 22, Quận Bình Thạnh','2025-05-15 09:00:00.000000','InProgress',2200000,'2025-05-15 09:00:00.000000',19,1),(8,NULL,'2025-05-11 16:00:00.000000',_binary '\0',_binary '','789 đường Nguyễn Đình Chiểu, phường 6, Quận 3','2025-05-12 18:00:00.000000',_binary '\0','123 đường Lý Thường Kiệt, phường 6, Quận 11','2025-05-11 16:00:00.000000','Completed',1500000,'2025-05-12 18:00:00.000000',23,2),(9,NULL,'2025-05-17 14:00:00.000000',_binary '',_binary '\0','456 đường 3/2, phường 12, Quận 10','2025-05-21 10:00:00.000000',_binary '','123 đường Hoàng Diệu, phường Linh Đông, TP Thủ Đức','2025-05-17 14:00:00.000000','Pending',2800000,'2025-05-17 14:00:00.000000',35,5),(10,NULL,'2025-05-10 08:00:00.000000',_binary '\0',_binary '','789 đường Cao Thắng, phường 5, Quận 6','2025-05-14 12:00:00.000000',_binary '\0','456 đường 3/2, phường 12, Quận 10','2025-05-10 08:00:00.000000','Reviewed',1300000,'2025-05-14 12:00:00.000000',22,3),(11,NULL,'2025-05-12 08:30:00.000000',_binary '',_binary '\0','123 đường Võ Văn Ngân, phường Linh Chiểu, TP Thủ Đức','2025-05-14 15:00:00.000000',_binary '','456 đường Điện Biên Phủ, phường 25, Quận Bình Thạnh','2025-05-12 08:30:00.000000','Reviewed',2500000,'2025-05-14 16:00:00.000000',32,1),(12,NULL,'2025-05-13 10:00:00.000000',_binary '\0',_binary '','789 đường Nguyễn Hữu Cảnh, phường 22, Quận Bình Thạnh','2025-05-16 12:00:00.000000',_binary '\0','123 đường Nguyễn Đình Chiểu, phường 6, Quận 3','2025-05-13 10:00:00.000000','Reviewed',3000000,'2025-05-15 10:00:00.000000',31,2),(13,NULL,'2025-05-14 09:00:00.000000',_binary '',_binary '\0','123 đường Quang Trung, phường 8, Quận Gò Vấp','2025-05-15 20:00:00.000000',_binary '','456 đường 3/2, phường 12, Quận 10','2025-05-14 09:00:00.000000','InProgress',3200000,'2025-05-15 18:00:00.000000',35,4),(14,NULL,'2025-05-16 08:00:00.000000',_binary '\0',_binary '','789 đường Nguyễn Huệ, phường Bến Nghé, Quận 1','2025-05-20 12:00:00.000000',_binary '\0','123 đường Nguyễn Văn Linh, phường Tân Phong, Quận 7','2025-05-16 08:00:00.000000','Pending',1500000,'2025-05-16 08:00:00.000000',33,3),(15,NULL,'2025-05-10 12:00:00.000000',_binary '',_binary '\0','456 đường Đỗ Xuân Hợp, phường Phước Long A, Quận 9','2025-05-12 14:00:00.000000',_binary '','123 đường Hoàng Diệu, phường Linh Đông, TP Thủ Đức','2025-05-10 12:00:00.000000','Reviewed',1800000,'2025-05-12 14:00:00.000000',29,5),(16,NULL,'2025-05-11 11:00:00.000000',_binary '\0',_binary '','789 đường Hồng Bàng, phường 1, Quận 5','2025-05-13 09:00:00.000000',_binary '\0','123 đường Võ Văn Ngân, phường Linh Chiểu, TP Thủ Đức','2025-05-11 11:00:00.000000','Reviewed',2700000,'2025-05-13 09:00:00.000000',28,4),(17,NULL,'2025-05-13 16:00:00.000000',_binary '',_binary '\0','123 đường Nguyễn Văn Linh, phường Tân Phong, Quận 7','2025-05-17 18:00:00.000000',_binary '','456 đường 3/2, phường 12, Quận 10','2025-05-13 16:00:00.000000','InProgress',3400000,'2025-05-15 18:00:00.000000',25,1),(18,NULL,'2025-05-18 08:00:00.000000',_binary '\0',_binary '','789 đường Lê Văn Việt, phường Hiệp Phú, Quận 9','2025-05-22 12:00:00.000000',_binary '\0','123 đường 3/2, phường 12, Quận 10','2025-05-18 08:00:00.000000','Pending',1600000,'2025-05-18 08:00:00.000000',30,3),(19,NULL,'2025-05-14 10:00:00.000000',_binary '',_binary '\0','123 đường Tân Kỳ Tân Quý, phường Tân Sơn Nhì, Quận Tân Phú','2025-05-16 14:00:00.000000',_binary '','456 đường Cao Thắng, phường 5, Quận 6','2025-05-14 10:00:00.000000','InProgress',2900000,'2025-05-15 12:00:00.000000',34,5),(20,NULL,'2025-05-15 09:00:00.000000',_binary '\0',_binary '','789 đường Điện Biên Phủ, phường 25, Quận Bình Thạnh','2025-05-17 18:00:00.000000',_binary '\0','123 đường Hoàng Diệu, phường Linh Đông, TP Thủ Đức','2025-05-15 09:00:00.000000','Pending',3200000,'2025-05-15 09:00:00.000000',32,2),(24,NULL,'2025-05-15 11:23:12.917812',_binary '',_binary '','abcd, Quận 1','2025-05-17 06:30:00.000000',_binary '','456 Lê Lợi, Phường Bến Thành, Quận 1','2025-05-16 04:26:00.000000','Confirmed',899416.6666666666,'2025-05-15 11:23:12.917812',2,1);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-15 13:13:44
