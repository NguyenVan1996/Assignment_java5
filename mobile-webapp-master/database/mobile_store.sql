CREATE DATABASE  IF NOT EXISTS `mobile_store` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mobile_store`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mobile_store
-- ------------------------------------------------------
-- Server version	8.0.12

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
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `brand` (
  `BRAND_ID` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `BRAND_NAME` varchar(45) NOT NULL,
  `LOGO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`BRAND_ID`),
  UNIQUE KEY `BRAND_NAME_UNIQUE` (`BRAND_NAME`),
  KEY `BRAND_ID` (`BRAND_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'Apple','/images/brands/Apple.png'),(2,'Samsung','/images/brands/Samsung.png'),(3,'Oppo','/images/brands/Oppo.png'),(4,'Sony','/images/brands/Sony.png'),(5,'Xiaomi','/images/brands/Xiaomi.png');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order` (
  `ORDER_ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) unsigned NOT NULL,
  `ORDER_STATUS_ID` tinyint(3) unsigned NOT NULL,
  `CUSTOMER_NAME` varchar(45) NOT NULL,
  `PHONE_NUMBER` varchar(15) NOT NULL,
  `SHIPPING_ADDRESS` varchar(255) NOT NULL,
  `AMOUNT` int(11) unsigned NOT NULL,
  `CREATED_TIME` datetime DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_TIME` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ORDER_ID`),
  KEY `ORDER_ID` (`ORDER_ID`),
  KEY `fk_Order_User1_idx` (`USER_ID`),
  KEY `fk_Order_Order_Status1_idx` (`ORDER_STATUS_ID`),
  CONSTRAINT `fk_Order_Order_Status1` FOREIGN KEY (`ORDER_STATUS_ID`) REFERENCES `order_status` (`order_status_id`),
  CONSTRAINT `fk_Order_User1` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,2,5,'Nguyễn Đức Mạnh','091231234','Số 8, ngõ 299, phố Dịch Vọng, Hà Nội',16980000,'2018-10-04 15:43:37','2018-10-04 15:57:55','ADMIN'),(2,1,5,'Đỗ Duy Mậu','0969216532','số 95, Cổ Nhuế, Hà Nội',34790000,'2018-10-04 15:45:47','2018-10-04 15:57:55','ADMIN'),(3,1,5,'Việt Hà','012931231','Phố Cổ, Hà Nội',8490000,'2018-10-04 15:54:40','2018-10-04 15:57:55','ADMIN'),(4,1,5,'Duy Tuấn','012319231','Số 10, Đường Nguyễn Khang, Cầu Giấy, Hà Nội',8650000,'2018-10-04 15:55:19','2018-10-04 15:57:55','ADMIN'),(5,1,5,'Linh Phạm','012931231','Số 20, Mai Dịch, Hà Nội',11990000,'2018-10-04 15:56:01','2018-10-04 15:57:55','ADMIN');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_line`
--

DROP TABLE IF EXISTS `order_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order_line` (
  `ODER_LINE_ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ORDER_ID` int(11) unsigned NOT NULL,
  `PRODUCT_ID` int(11) unsigned NOT NULL,
  `QUANTITY` smallint(6) NOT NULL,
  PRIMARY KEY (`ODER_LINE_ID`),
  KEY `ODER_LINE_ID` (`ODER_LINE_ID`),
  KEY `fk_Order_Line_Order1_idx` (`ORDER_ID`),
  KEY `fk_Order_Line_Product1_idx` (`PRODUCT_ID`),
  CONSTRAINT `fk_Order_Line_Order1` FOREIGN KEY (`ORDER_ID`) REFERENCES `order` (`order_id`),
  CONSTRAINT `fk_Order_Line_Product1` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_line`
--

LOCK TABLES `order_line` WRITE;
/*!40000 ALTER TABLE `order_line` DISABLE KEYS */;
INSERT INTO `order_line` VALUES (1,1,6,2),(2,2,1,1),(3,3,6,1),(4,4,4,1),(5,5,9,1);
/*!40000 ALTER TABLE `order_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order_status` (
  `ORDER_STATUS_ID` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `ORDER_STATUS_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`ORDER_STATUS_ID`),
  UNIQUE KEY `ORDER_STATUS_NAME_UNIQUE` (`ORDER_STATUS_NAME`),
  KEY `ORDER_STATUS_ID` (`ORDER_STATUS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
INSERT INTO `order_status` VALUES (1,'Chờ xác nhận'),(5,'Hoàn tất'),(6,'Hủy'),(4,'Đã thanh toán'),(2,'Đã xác nhận'),(3,'Đang giao hàng');
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product` (
  `PRODUCT_ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `BRAND_ID` tinyint(3) unsigned NOT NULL,
  `PRODUCT_NAME` varchar(255) NOT NULL,
  `QUANTITY_IN_STOCK` int(11) unsigned NOT NULL DEFAULT '0',
  `PRICE` int(11) unsigned NOT NULL,
  `PRODUCT_UNIT` varchar(45) DEFAULT 'chiếc',
  `THUMBNAIL` varchar(255) DEFAULT NULL,
  `SHORT_DESCRIPTION` varchar(255) DEFAULT NULL,
  `WARRANTY` varchar(45) DEFAULT NULL,
  `VIEW` int(11) unsigned DEFAULT '0',
  `ENABLED` tinyint(1) NOT NULL,
  `CREATED_TIME` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PRODUCT_ID`),
  UNIQUE KEY `PRODUCT_NAME_UNIQUE` (`PRODUCT_NAME`),
  KEY `PRODUCT_ID` (`PRODUCT_ID`),
  KEY `fk_Product_Brand1_idx` (`BRAND_ID`),
  CONSTRAINT `fk_Product_Brand1` FOREIGN KEY (`BRAND_ID`) REFERENCES `brand` (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,1,'iPhone X 256GB Gray',12,34790000,'Chiếc','/images/products/apple/iphone-x-256gb.png','iPhone X mang trên mình thiết kế hoàn toàn mới với màn hình Super Retina viền cực mỏng và trang bị nhiều công nghệ hiện đại như nhận diện khuôn mặt Face ID, sạc pin nhanh và sạc không dây cùng khả năng chống nước bụi cao cấp.','12 tháng',7737,1,'2018-09-24 08:27:39'),(2,1,'iPhone X 64GB Silver',32,29990000,'Chiếc','/images/products/apple/iphone-x-64gb-silver.png','iPhone X là cụm từ được rất nhiều người mong chờ muốn biết và tìm kiếm trên Google bởi đây là chiếc điện thoại mà Apple kỉ niệm 10 năm iPhone đầu tiên được bán ra.','12 tháng',124,1,'2018-09-24 08:27:39'),(3,2,'Samsung Galaxy A6 (2018)',55,5490000,'Chiếc','/images/products/samsung/samsung-galaxy-a6-2018.png','Samsung Galaxy A6 (2018) là chiếc smartphone tầm trung vừa được ra mắt của Samsung bên cạnh chiếc Samsung Galaxy A6+ (2018).','12 tháng',53,1,'2018-09-24 08:27:39'),(4,2,'Samsung Note 5',33,8650000,'Chiếc','/images/products/samsung/samsung-galaxy-note-5.png','Samsung Galaxy Note 9 mang trong mình hàng hoạt các thay đổi đột phá với điểm nhấn đặc biệt đến từ chiếc bút S-Pen thần thánh cùng một viên pin dung lượng khổng lồ tới 4.000 mAh.','12 tháng',663,1,'2018-09-24 08:27:39'),(5,2,'Samsung Galaxy J6',43,4790000,'Chiếc','/images/products/samsung/samsung-galaxy-j6-2018.png','Trong phân khúc smartphone tầm trung, Samsung Galaxy J6 là cái tên tiếp theo được nhắc đến với một thiết kế đẹp, hiệu năng tốt và có màn hình 18.5:9 thời thượng.','12 tháng',13,1,'2018-09-24 08:27:39'),(6,3,'OPPO F9 6GB',66,8490000,'Chiếc','/images/products/oppo/oppo-f9-6gb.png','Là chiếc điện thoại OPPO được nâng cấp cấu hình, cụ thể là RAM lên tới 6 GB, OPPO F9 6GB còn trang bị nhiều tính năng đột phá như sở hữu công nghệ sạc VOOC mới nhất, mặt lưng chuyển màu độc đáo.','12 tháng',5236,1,'2018-09-24 08:27:39'),(7,3,'OPPO Find X',99,20990000,'Chiếc','/images/products/oppo/oppo-find-x-2.png','OPPO Find X tạo nên một cú hích lớn trong làng smartphone hiện nay khi mang trong mình nhiều tính năng đột phá mà nổi bật nhất đến từ thiết kế sáng tạo và một hiệu năng đỉnh cao.','12 tháng',552,1,'2018-09-24 08:27:39'),(8,3,'OPPO F7 128GB',56,8990000,'Chiếc','/images/products/oppo/oppo-f7-128gb-den.png','Tiếp nối sự thành công của OPPO F5 thì OPPO tiếp tục giới thiệu OPPO F7 128GB với mức giá tương đương nhưng mang trong mình thiết kế hoàn toàn mới cũng nhiều cải tiến đáng giá.','12 tháng',563,1,'2018-09-24 08:27:39'),(9,4,'Sony Xperia XZ2',44,11990000,'Chiếc','/images/products/sony/sony-xperia-xz2.png','Tiếp nối sự thành công của OPPO F5 thì OPPO tiếp tục giới thiệu OPPO F7 128GB với mức giá tương đương nhưng mang trong mình thiết kế hoàn toàn mới cũng nhiều cải tiến đáng giá.','12 tháng',235,1,'2018-09-24 08:27:39'),(10,4,'Sony Xperia XZ Dual',43,9990000,'Chiếc','/images/products/sony/sony-xperia-xz2.png','Sony Xperia XZ Dual với thiết kế cực đẹp, cùng camera chất lượng hơn, nhiều tính năng tiện ích hơn.','12 tháng',534,1,'2018-09-24 08:27:39'),(11,4,'Sony Xperia XZ1',65,8990000,'Chiếc','/images/products/sony/sony-xperia-xz1-xanh.png','Sony Xperia XZ1 là mẫu flagship kế tiếp của Sony tiếp nối sự thành công của chiếc Xperia XZs đã ra mắt trước đó với những nâng cấp nhẹ về mặt cấu hình và thiết kế.','12 tháng',1235,1,'2018-09-24 08:27:39'),(12,5,'Xiaomi Mi 8',12,12990000,'Chiếc','/images/products/xiaomi/xiaomi-mi-8-black.png','Xiaomi Mi 8 sẽ là cái tên được nhắc đến nhiều trong gia đình Xiaomi khi mang trong mình đầy đủ những gì gọi là cao cấp đến từ vẻ đẹp bên ngoài cũng như phần cứng mạnh mẽ bên trong.','12 tháng',5345,1,'2018-09-24 08:27:39'),(13,5,'Xiaomi Mi A2',34,6690000,'Chiếc','/images/products/xiaomi/xiaomi-a2.png','Tiếp nối sự thành công của Xiaomi Mi A1 thì Xiaomi tiếp tục giới thiệu tới người dùng phiên bản kế nhiệm là chiếc Xiaomi Mi A2 với nâng cấp mạnh mẽ về cấu hình cũng như camera.','12 tháng',123,1,'2018-09-24 08:27:39'),(14,5,'Xiaomi Redmi Note 5',77,5690000,'Chiếc','/images/products/xiaomi/xiaomi-redmi-note-5-pro.png','Xiaomi Redmi Note 5 là smartphone thứ ba trong phân khúc tầm trung - giá rẻ của Xiaomi sở hữu màn hình tỉ lệ mới 18:9.','12 tháng',1235,1,'2018-09-24 08:27:39');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_spec`
--

DROP TABLE IF EXISTS `product_spec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product_spec` (
  `PROD_SPEC_ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `PRODUCT_ID` int(11) unsigned NOT NULL,
  `SPECIFICATION_ID` int(11) unsigned NOT NULL,
  PRIMARY KEY (`PROD_SPEC_ID`),
  KEY `PROD_SPEC_ID` (`PROD_SPEC_ID`),
  KEY `fk_Product_spec_Product1_idx` (`PRODUCT_ID`),
  KEY `fk_product_spec_specification1_idx` (`SPECIFICATION_ID`),
  CONSTRAINT `fk_Product_spec_Product1` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`product_id`),
  CONSTRAINT `fk_product_spec_specification1` FOREIGN KEY (`SPECIFICATION_ID`) REFERENCES `specification` (`specification_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_spec`
--

LOCK TABLES `product_spec` WRITE;
/*!40000 ALTER TABLE `product_spec` DISABLE KEYS */;
INSERT INTO `product_spec` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,2,1),(12,2,2),(13,2,3),(14,2,4),(15,2,5),(16,2,6),(17,2,7),(18,2,8),(19,2,9),(20,2,10),(21,3,1),(22,3,2),(23,3,3),(24,3,4),(25,3,5),(26,3,6),(27,3,7),(28,3,8),(29,3,9),(30,3,10),(31,4,1),(32,4,2),(33,4,3),(34,4,4),(35,4,5),(36,4,6),(37,4,7),(38,4,8),(39,4,9),(40,4,10),(41,5,1),(42,5,2),(43,5,3),(44,5,4),(45,5,5),(46,5,6),(47,5,7),(48,5,8),(49,5,9),(50,5,10),(51,6,1),(52,6,2),(53,6,3),(54,6,4),(55,6,5),(56,6,6),(57,6,7),(58,6,8),(59,6,9),(60,6,10),(61,7,1),(62,7,2),(63,7,3),(64,7,4),(65,7,5),(66,7,6),(67,7,7),(68,7,8),(69,7,9),(70,7,10);
/*!40000 ALTER TABLE `product_spec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_spec_detail`
--

DROP TABLE IF EXISTS `product_spec_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product_spec_detail` (
  `PROD_SPEC_DETAIL_ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `PROD_SPEC_ID` int(11) unsigned NOT NULL,
  `PROD_SPEC_NAME` varchar(255) NOT NULL,
  `PROD_SPEC_VALUE` varchar(1000) NOT NULL,
  PRIMARY KEY (`PROD_SPEC_DETAIL_ID`),
  KEY `PROD_SPEC_DETAIL_ID` (`PROD_SPEC_DETAIL_ID`),
  KEY `fk_Product_Spec_Detail_Product_Spec1_idx` (`PROD_SPEC_ID`),
  CONSTRAINT `fk_Product_Spec_Detail_Product_Spec1` FOREIGN KEY (`PROD_SPEC_ID`) REFERENCES `product_spec` (`prod_spec_id`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_spec_detail`
--

LOCK TABLES `product_spec_detail` WRITE;
/*!40000 ALTER TABLE `product_spec_detail` DISABLE KEYS */;
INSERT INTO `product_spec_detail` VALUES (1,1,'Công nghệ màn hình','OLED'),(2,1,'Độ phân giải','1125 x 2436 Pixels'),(3,1,'Màn hình rộng','5.8\"'),(4,1,'Mặt kính cảm ứng','Kính oleophobic (ion cường lực)'),(5,2,'Độ phân giải','2 camera 12 MP'),(6,2,'Quay phim','Quay phim 4K 2160p@60fps'),(7,2,'Đèn Flash','	4 đèn LED (2 tông màu)'),(8,2,'Chụp ảnh nâng cao','	Chụp ảnh xóa phông, Lấy nét dự đoán, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Chống rung quang học (OIS)'),(9,3,'Độ phân giải','7 MP'),(10,3,'Videocall','Có'),(11,3,'Thông tin khác','	Nhận diện khuôn mặt, Quay video Full HD, Camera góc rộng, Selfie ngược sáng HDR'),(12,4,'Hệ điều hành','iOS 11'),(13,4,'Chipset (hãng SX CPU)','Apple A11 Bionic 6 nhân'),(14,4,'Tốc độ CPU','2.39 GHz'),(15,4,'Chip đồ họa (GPU)','Apple GPU 3 nhân'),(16,5,'RAM','3 GB'),(17,5,'Bộ nhớ trong','256 GB'),(18,5,'Bộ nhớ còn lại (khả dụng)','Khoảng 249 GB'),(19,5,'Thẻ nhớ ngoài','Không'),(20,6,'Mạng di động','3G, 4G LTE Cat 16'),(21,6,'SIM','1 Nano SIM'),(22,6,'Wifi','Wi-Fi 802.11 a/b/g/n/ac, Dual-band, Wi-Fi hotspot'),(23,6,'GPS','A-GPS, GLONASS'),(24,6,'Bluetooth','EDR, LE, A2DP, v5.0'),(25,6,'Cổng kết nối/sạc','Lightning'),(26,6,'Jack tai nghe','Không'),(27,6,'Kết nối khác','NFC, OTG'),(28,7,'Thiết kế','Nguyên khối'),(29,7,'Chất liệu','Khung kim loại + mặt kính cường lực'),(30,7,'Kích thước','Dài 143.6 mm - Ngang 70.9 mm - Dày 7.7 mm'),(31,7,'Trọng lượng','174 g'),(32,8,'Dung lượng pin','2716 mAh'),(33,8,'Loại pin','Pin chuẩn Li-Ion'),(34,8,'Công nghệ pin','Tiết kiệm pin, Sạc pin nhanh, Sạc pin không dây'),(35,9,'Bảo mật nâng cao','Nhận diện khuôn mặt Face ID'),(36,9,'Tính năng đặc biệt','3D Touch'),(37,9,'Ghi âm','Có, microphone chuyên dụng chống ồn'),(38,9,'Radio','Không'),(39,9,'Xem phim','H.265, 3GP, MP4, AVI, WMV, H.263, H.264(MPEG4-AVC)'),(40,9,'Nghe nhạc','Lossless, Midi, MP3, WAV, WMA, WMA9, AAC, AAC+, AAC++, eAAC+'),(41,10,'Thời điểm ra mắt','09/2017'),(42,11,'Công nghệ màn hình','OLED'),(43,11,'Độ phân giải','1125 x 2436 Pixels'),(44,11,'Màn hình rộng','5.8\"'),(45,11,'Mặt kính cảm ứng','Kính oleophobic (ion cường lực)'),(46,12,'Độ phân giải','2 camera 12 MP'),(47,12,'Quay phim','Quay phim 4K 2160p@60fps'),(48,12,'Đèn Flash','4 đèn LED (2 tông màu)'),(49,12,'Chụp ảnh nâng cao','Chụp ảnh xóa phông, Lấy nét dự đoán, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Chống rung quang học (OIS)'),(50,13,'Độ phân giải','7 MP'),(51,13,'Videocall','Có'),(52,13,'Thông tin khác','Selfie ngược sáng HDR, Quay video Full HD, Nhận diện khuôn mặt, Camera góc rộng'),(53,14,'Hệ điều hành','iOS 11'),(54,14,'Chipset (hãng SX CPU)','Apple A11 Bionic 6 nhân'),(55,14,'Tốc độ CPU','2.39 GHz'),(56,14,'Chip đồ họa (GPU)','Apple GPU 3 nhân'),(57,15,'RAM','3 GB'),(58,15,'Bộ nhớ trong','64 GB'),(59,15,'Bộ nhớ còn lại (khả dụng)','Khoảng 55 GB'),(60,15,'Thẻ nhớ ngoài','Không'),(61,16,'Mạng di động','3G, 4G LTE Cat 16'),(62,16,'SIM','1 Nano SIM'),(63,16,'Wifi','Wi-Fi 802.11 a/b/g/n/ac, Dual-band, Wi-Fi hotspot'),(64,16,'GPS','A-GPS, GLONASS'),(65,16,'Bluetooth','EDR, LE, A2DP, v5.0'),(66,16,'Cổng kết nối/sạc','Lightning'),(67,16,'Jack tai nghe','Không'),(68,17,'Kết nối khác','NFC, OTG'),(69,17,'Thiết kế','Nguyên khối'),(70,17,'Chất liệu','Khung kim loại + mặt kính cường lực'),(71,17,'Kích thước','Dài 143.6 mm - Ngang 70.9 mm - Dày 7.7 mm'),(72,17,'Trọng lượng','174 g'),(73,18,'Dung lượng pin','2716 mAh'),(74,18,'Loại pin','Pin chuẩn Li-Ion'),(75,18,'Công nghệ pin','Tiết kiệm pin, Sạc pin nhanh, Sạc pin không dây'),(76,19,'Bảo mật nâng cao','Nhận diện khuôn mặt Face ID'),(77,19,'Tính năng đặc biệt','3D Touch'),(78,19,'Ghi âm','Có, microphone chuyên dụng chống ồn'),(79,19,'Xem phim','	H.265, 3GP, MP4, AVI, WMV, H.263, H.264(MPEG4-AVC)'),(80,19,'Nghe nhạc','	Lossless, Midi, MP3, WAV, WMA, WMA9, AAC, AAC+, AAC++, eAAC+'),(81,19,'Radio','Không'),(82,20,'Thời điểm ra mắt','09/2017'),(83,21,'Công nghệ màn hình','Super AMOLED'),(84,21,'Độ phân giải','HD+ (720 × 1480 Pixels)'),(85,21,'Màn hình rộng','5.6\"'),(86,21,'Mặt kính cảm ứng','Mặt kính cong 2.5D'),(87,22,'Độ phân giải','16 MP'),(88,22,'Quay phim','Quay phim FullHD 1080p@30fps'),(89,22,'Đèn Flash','Có'),(90,22,'Chụp ảnh nâng cao','Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Beautify'),(91,23,'Độ phân giải','16 MP'),(92,23,'Videocall','Có'),(93,23,'Thông tin khác','Nhận diện khuôn mặt, Chế độ làm đẹp, Quay video Full HD, Đèn Flash trợ sáng, Camera góc rộng, Sticker AR (biểu tượng thực tế ảo), Tự động lấy nét'),(94,24,'Hệ điều hành','Android 8.0 (Oreo)'),(95,24,'Chipset (hãng SX CPU)','Exynos 7870 8 nhân 64-bit'),(96,24,'Tốc độ CPU','1.6 GHz'),(97,24,'Chip đồ họa (GPU)','Mali-T830'),(98,25,'RAM','3 GB'),(99,25,'Bộ nhớ trong','32 GB'),(100,25,'Bộ nhớ còn lại (khả dụng)','Khoảng 21 GB'),(101,25,'Bộ nhớ còn lại (khả dụng)','MicroSD, hỗ trợ tối đa 256 GB'),(102,26,'Mạng di động','3G, 4G LTE Cat 6'),(103,26,'SIM','2 Nano SIM'),(104,26,'Wifi','Wi-Fi 802.11 b/g/n, Wi-Fi Direct, Wi-Fi hotspot'),(105,26,'GPS','A-GPS, GLONASS'),(106,26,'Bluetooth','A2DP, LE, EDR, v4.2'),(107,26,'Cổng kết nối/sạc','Micro USB'),(108,26,'Jack tai nghe','3.5 mm'),(109,26,'Kết nối khác','OTG'),(110,27,'Thiết kế','Nguyên khối'),(111,27,'Chất liệu','Kim loại'),(112,27,'Kích thước','Dài 149.9 mm - Ngang 70.8 mm - Dày 7.7 mm'),(113,27,'Trọng lượng','162 g'),(114,28,'Dung lượng pin','3000 mAh'),(115,28,'Loại pin','Pin chuẩn Li-Ion'),(116,28,'Công nghệ pin','Tiết kiệm pin'),(117,29,'Bảo mật nâng cao','Mở khóa bằng vân tay, Mở khóa bằng khuôn mặt'),(118,29,'Tính năng đặc biệt','Mặt kính 2.5D'),(119,29,'Ghi âm','Có'),(120,29,'Radio','Có'),(121,29,'Xem phim','3GP, MP4, AVI, WMV'),(122,29,'Nghe nhạc','Midi, AMR, MP3, WAV, WMA, AAC, OGG, FLAC'),(123,30,'Thời điểm ra mắt','05/2018'),(124,31,'Công nghệ màn hình','Super AMOLED'),(125,31,'Độ phân giải','HD+ (720 × 1480 Pixels)'),(126,31,'Màn hình rộng','5.6\"'),(127,31,'Mặt kính cảm ứng','Mặt kính cong 2.5D'),(128,32,'Độ phân giải','16 MP'),(129,32,'Quay phim','Quay phim FullHD 1080p@30fps'),(130,32,'Đèn Flash','Có'),(131,32,'Chụp ảnh nâng cao','Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Beautify'),(132,33,'Độ phân giải','16 MP'),(133,33,'Videocall','Có'),(134,33,'Thông tin khác','Nhận diện khuôn mặt, Chế độ làm đẹp, Quay video Full HD, Đèn Flash trợ sáng, Camera góc rộng, Sticker AR (biểu tượng thực tế ảo), Tự động lấy nét'),(135,34,'Hệ điều hành','Android 8.0 (Oreo)'),(136,34,'Chipset (hãng SX CPU)','Exynos 7870 8 nhân 64-bit'),(137,34,'Tốc độ CPU','1.6 GHz'),(138,34,'Chip đồ họa (GPU)','Mali-T830'),(139,35,'RAM','3 GB'),(140,35,'Bộ nhớ trong','32 GB'),(141,35,'Bộ nhớ còn lại (khả dụng)','Khoảng 21 GB'),(142,35,'Bộ nhớ còn lại (khả dụng)','MicroSD, hỗ trợ tối đa 256 GB'),(143,36,'Mạng di động','3G, 4G LTE Cat 6'),(144,36,'SIM','2 Nano SIM'),(145,36,'Wifi','Wi-Fi 802.11 b/g/n, Wi-Fi Direct, Wi-Fi hotspot'),(146,36,'GPS','A-GPS, GLONASS'),(147,36,'Bluetooth','A2DP, LE, EDR, v4.2'),(148,36,'Cổng kết nối/sạc','Micro USB'),(149,36,'Jack tai nghe','3.5 mm'),(150,36,'Kết nối khác','OTG'),(151,37,'Thiết kế','Nguyên khối'),(152,37,'Chất liệu','Kim loại'),(153,37,'Kích thước','Dài 149.9 mm - Ngang 70.8 mm - Dày 7.7 mm'),(154,37,'Trọng lượng','162 g'),(155,38,'Dung lượng pin','3000 mAh'),(156,38,'Loại pin','Pin chuẩn Li-Ion'),(157,38,'Công nghệ pin','Tiết kiệm pin'),(158,39,'Bảo mật nâng cao','Mở khóa bằng vân tay, Mở khóa bằng khuôn mặt'),(159,39,'Tính năng đặc biệt','Mặt kính 2.5D'),(160,39,'Ghi âm','Có'),(161,39,'Radio','Có'),(162,39,'Xem phim','3GP, MP4, AVI, WMV'),(163,39,'Nghe nhạc','Midi, AMR, MP3, WAV, WMA, AAC, OGG, FLAC'),(164,40,'Thời điểm ra mắt','05/2018'),(165,41,'Công nghệ màn hình','Super AMOLED'),(166,41,'Độ phân giải','HD+ (720 × 1480 Pixels)'),(167,41,'Màn hình rộng','5.6\"'),(168,41,'Mặt kính cảm ứng','Mặt kính cong 2.5D'),(169,42,'Độ phân giải','13 MP'),(170,42,'Quay phim','Quay phim FullHD 1080p@30fps'),(171,42,'Đèn Flash','Có'),(172,42,'Chụp ảnh nâng cao','Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama'),(173,43,'Độ phân giải','8 MP'),(174,43,'Videocall','Hỗ trợ VideoCall thông qua ứng dụng'),(175,43,'Thông tin khác','Chế độ làm đẹp, Nhận diện khuôn mặt, Đèn Flash trợ sáng, Tự động lấy nét'),(176,44,'Hệ điều hành','Android 8.0 (Oreo)'),(177,44,'Chipset (hãng SX CPU)','Exynos 7870 8 nhân 64-bit'),(178,44,'Tốc độ CPU','1.6 GHz'),(179,44,'Chip đồ họa (GPU)','Mali-T830'),(180,45,'RAM','3 GB'),(181,45,'Bộ nhớ trong','32 GB'),(182,45,'Bộ nhớ còn lại (khả dụng)','Khoảng 21 GB'),(183,45,'Bộ nhớ còn lại (khả dụng)','MicroSD, hỗ trợ tối đa 256 GB'),(184,46,'Mạng di động','3G, 4G LTE Cat 6'),(185,46,'SIM','2 Nano SIM'),(186,46,'Wifi','Wi-Fi 802.11 b/g/n, Wi-Fi Direct, Wi-Fi hotspot'),(187,46,'GPS','A-GPS, GLONASS'),(188,46,'Bluetooth','A2DP, LE, EDR, v4.2'),(189,46,'Cổng kết nối/sạc','Micro USB'),(190,46,'Jack tai nghe','3.5 mm'),(191,46,'Kết nối khác','OTG'),(192,47,'Thiết kế','Nguyên khối'),(193,47,'Chất liệu','Kim loại'),(194,47,'Kích thước','Dài 149.9 mm - Ngang 70.8 mm - Dày 7.7 mm'),(195,47,'Trọng lượng','162 g'),(196,48,'Dung lượng pin','3000 mAh'),(197,48,'Loại pin','Pin chuẩn Li-Ion'),(198,48,'Công nghệ pin','Tiết kiệm pin'),(199,49,'Bảo mật nâng cao','Mở khóa bằng vân tay, Mở khóa bằng khuôn mặt'),(200,49,'Tính năng đặc biệt','Mặt kính 2.5D'),(201,49,'Ghi âm','Có'),(202,49,'Radio','Có'),(203,49,'Xem phim','3GP, MP4, AVI, WMV'),(204,49,'Nghe nhạc','Midi, AMR, MP3, WAV, WMA, AAC, OGG, FLAC'),(205,50,'Thời điểm ra mắt','05/2018');
/*!40000 ALTER TABLE `product_spec_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `ROLE_ID` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `ROLE_NAME_UNIQUE` (`ROLE_NAME`),
  KEY `ROLE_ID` (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specification`
--

DROP TABLE IF EXISTS `specification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `specification` (
  `SPECIFICATION_ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `SPECIFICATION_NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`SPECIFICATION_ID`),
  UNIQUE KEY `SPECIFICATION_NAME_UNIQUE` (`SPECIFICATION_NAME`),
  KEY `SPECIFICATION_IDX` (`SPECIFICATION_ID`) /*!80000 INVISIBLE */
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specification`
--

LOCK TABLES `specification` WRITE;
/*!40000 ALTER TABLE `specification` DISABLE KEYS */;
INSERT INTO `specification` VALUES (5,'Bộ nhớ & Lưu trữ'),(2,'Camera sau'),(3,'Camera trước'),(4,'Hệ điều hành - CPU'),(6,'Kết nối'),(1,'Màn hình'),(7,'Thiết kế & Trọng lượng'),(10,'Thông tin khác'),(8,'Thông tin pin & Sạc'),(9,'Tiện ích');
/*!40000 ALTER TABLE `specification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `USER_ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(128) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `FULL_NAME` varchar(45) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `PHONE_NUMBER` varchar(15) NOT NULL,
  `BIRTHDATE` date DEFAULT NULL,
  `GENDER` tinyint(1) DEFAULT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  `CREATED_TIME` datetime DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_TIME` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USERNAME_UNIQUE` (`USERNAME`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`),
  UNIQUE KEY `PHONE_NUMBER_UNIQUE` (`PHONE_NUMBER`),
  KEY `USER_ID` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'default','$2a$10$6VHwMEvlqlwrQqO8Zz2cL.cn0zx85bVJtB1/VnS2tzR4EP9sCVDzK','default@anvanmobile.com',NULL,NULL,'000000000',NULL,NULL,0,'2018-09-13 22:17:52','2018-10-05 04:23:43'),(2,'admin','$2a$10$prp0B1bOxdTPPuh9RnMBkOBLMJfDM8EDN6HwP0.p6HpeoYRENRe/m','admin@anvanmobile.com',NULL,NULL,'0912831231',NULL,NULL,1,'2018-09-13 22:19:38','2018-10-05 04:23:43'),(3,'manhnd','$2a$10$HsQ2DMugUnPdCo09BmG74eliYz6jaPT3Oq0GdNfeuYr6xUwZ6ajge','manh.yoshi@gmail.com',NULL,NULL,'0912831232',NULL,NULL,1,'2018-09-13 22:20:19','2018-10-05 04:23:43'),(4,'hadhv','$2a$10$mcq9ysVbS5T009lArPrrCOCQpLmzGQFSg5T7kUqo5n7AvLGr.BgFq','hadhv@gmail.com',NULL,NULL,'0912831233',NULL,NULL,1,'2018-10-03 03:16:07','2018-10-05 04:23:24'),(5,'maudd','$2a$10$Msu4hKskD.om3S5PNeWv9ezRl.vUNL4/0SW4eRsF5KB.mkJJgRZRS','maudd@gmail.com',NULL,NULL,'0912831234',NULL,NULL,1,'2018-10-03 03:16:07','2018-10-05 04:23:24'),(6,'linhpv','$2a$10$aCfZjamRcssNxemA.CWXRua8N0ZlQS7vF614mNls.UGuZN1jofz9C','linhpv@gmail.com',NULL,NULL,'0912831235',NULL,NULL,1,'2018-10-03 03:16:07','2018-10-05 04:23:24'),(7,'tuannd','$2a$10$WQK9iM4SLnM.hWebBhrtIeok/T8r5X6nYIixTiOClT39fyVtDBSK2','tuannd@gmail.com',NULL,NULL,'0912831236',NULL,NULL,1,'2018-10-03 03:16:07','2018-10-05 04:23:24'),(8,'duongdk','$2a$10$P00WVANkp2FaOe6olFygrOONUGamC.dqH6uTPF3YSdpt5zXk.U8c6','duongdk@gmail.com',NULL,NULL,'0912831237',NULL,NULL,1,'2018-10-03 03:16:07','2018-10-05 04:23:24');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_role` (
  `USER_ID` int(11) unsigned NOT NULL,
  `ROLE_ID` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`),
  KEY `fk_role_user_role_idx` (`ROLE_ID`),
  CONSTRAINT `fk_role_user_role` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`role_id`),
  CONSTRAINT `fk_user_user_role` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (2,1),(4,1),(1,2),(2,2),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-05  4:24:54
