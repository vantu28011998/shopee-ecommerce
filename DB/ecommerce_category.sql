-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  `category_thumbnail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,NULL,NULL,NULL,NULL,NULL,'Thời trang nam','https://cf.shopee.vn/file/687f3967b7c2fe6a134a2c11894eea4b_tn'),(2,NULL,NULL,NULL,NULL,NULL,'Điện Thoại & Phụ Kiện','https://cf.shopee.vn/file/31234a27876fb89cd522d7e3db1ba5ca_tn'),(3,NULL,NULL,NULL,NULL,NULL,'Thời Trang Nữ','https://cf.shopee.vn/file/75ea42f9eca124e9cb3cde744c060e4d_tn'),(4,NULL,NULL,NULL,NULL,NULL,'Mẹ & Bé','https://cf.shopee.vn/file/099edde1ab31df35bc255912bab54a5e_tn'),(5,NULL,NULL,NULL,NULL,NULL,'Thiết bị điện tử','https://cf.shopee.vn/file/978b9e4cb61c611aaaf58664fae133c5_tn'),(6,NULL,NULL,NULL,NULL,NULL,'Nhà cửa & Đời sống','https://cf.shopee.vn/file/24b194a695ea59d384768b7b471d563f_tn'),(7,NULL,NULL,NULL,NULL,NULL,'Máy tính & Laptop','https://cf.shopee.vn/file/c3f3edfaa9f6dafc4825b77d8449999d_tn'),(8,NULL,NULL,NULL,NULL,NULL,'Sức khỏe & Sắc đe','https://cf.shopee.vn/file/bba68b7dc2d664748dd075d500049d72_tn'),(9,NULL,NULL,NULL,NULL,NULL,'Máy ảnh & Máy quay phim','https://cf.shopee.vn/file/ec14dd4fc238e676e43be2a911414d4d_tn'),(10,NULL,NULL,NULL,NULL,NULL,'Giày dép nữ','https://cf.shopee.vn/file/48630b7c76a7b62bc070c9e227097847_tn'),(11,NULL,NULL,NULL,NULL,NULL,'Đồng hồ','https://cf.shopee.vn/file/86c294aae72ca1db5f541790f7796260_tn'),(12,NULL,NULL,NULL,NULL,NULL,'Túi ví','https://cf.shopee.vn/file/fa6ada2555e8e51f369718bbc92ccc52_tn'),(13,NULL,NULL,NULL,NULL,NULL,'Giày dép nam','https://cf.shopee.vn/file/74ca517e1fa74dc4d974e5d03c3139de_tn'),(14,NULL,NULL,NULL,NULL,NULL,'Phụ Kiện Thời Trang','https://cf.shopee.vn/file/8e71245b9659ea72c1b4e737be5cf42e_tn'),(15,NULL,NULL,NULL,NULL,NULL,'Thiết Bị Điện Gia Dụng','https://cf.shopee.vn/file/7abfbfee3c4844652b4a8245e473d857_tn'),(16,NULL,NULL,NULL,NULL,NULL,'Bách Hóa Online','https://cf.shopee.vn/file/c432168ee788f903f1ea024487f2c889_tn'),(17,NULL,NULL,NULL,NULL,NULL,'Thể Thao & Du Lịch','https://cf.shopee.vn/file/6cb7e633f8b63757463b676bd19a50e4_tn'),(18,NULL,NULL,NULL,NULL,NULL,'Voucher & Dịch Vụ','https://cf.shopee.vn/file/b0f78c3136d2d78d49af71dd1c3f38c1_tn'),(19,NULL,NULL,NULL,NULL,NULL,'Ô tô - Xe máy - Xe đạp','https://cf.shopee.vn/file/3fb459e3449905545701b418e8220334_tn'),(20,NULL,NULL,NULL,NULL,NULL,'Nhà Sách Online','https://cf.shopee.vn/file/36013311815c55d303b0e6c62d6a8139_tn'),(21,NULL,NULL,NULL,NULL,NULL,'Đồ Chơi','https://cf.shopee.vn/file/ce8f8abc726cafff671d0e5311caa684_tn'),(22,NULL,NULL,NULL,NULL,NULL,'Giặt giũ & Chăm sóc nhà cửa','https://cf.shopee.vn/file/cd8e0d2e6c14c4904058ae20821d0763_tn'),(23,NULL,NULL,NULL,NULL,NULL,'Chăm sóc thú cưng','https://cf.shopee.vn/file/cdf21b1bf4bfff257efe29054ecea1ec_tn'),(24,NULL,NULL,NULL,NULL,NULL,'Thời trang trẻ em','https://cf.shopee.vn/file/4540f87aa3cbe99db739f9e8dd2cdaf0_tn'),(25,NULL,NULL,NULL,NULL,NULL,'Sản phẩm khác','https://cf.shopee.vn/file/12039f0db7c3f025fb0c57b3490efad2_tn');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-29 15:38:12
