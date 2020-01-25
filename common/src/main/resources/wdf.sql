/*
SQLyog Professional v13.1.1 (64 bit)
MySQL - 8.0.18 : Database - wdf
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wdf` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `wdf`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apartment` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `floor` int(11) DEFAULT NULL,
  `home` varchar(255) DEFAULT NULL,
  `porch` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `address` */

insert  into `address`(`id`,`apartment`,`city`,`floor`,`home`,`porch`,`region`,`street`) values 
(1,'string','string',0,'string','string','string','string'),
(2,'string','string',0,'string','string','string','string');

/*Table structure for table `categories` */

DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `categories` */

insert  into `categories`(`id`,`title`) values 
(1,'5'),
(2,'esim inch');

/*Table structure for table `images` */

DROP TABLE IF EXISTS `images`;

CREATE TABLE `images` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `images` */

insert  into `images`(`id`,`name`) values 
(1,'1579734879231__HAM2512.JPG'),
(2,'1579744585653_DSC03238.jpg'),
(3,'1579744751110_DSC03238.jpg'),
(4,'1579786305663_Screenshot_6.png'),
(5,'1579787291635_Screenshot_6.png'),
(6,'1579793177511_Screenshot_4.png'),
(7,'1579802266361_Screenshot_7.png'),
(9,'5d873b21-7cd4-4a08-b2b5-0d9e3b760cc3_343909.jpg'),
(10,'55bf793d-39fb-4d56-ad26-c9dd8b2eae07_2015-09-21_20.03.31.jpg'),
(11,'c2aebddc-f987-4969-a8bb-8fbc772652e5_343909.jpg'),
(12,'f45edad5-0e74-4333-af04-a7607eba77de_2015-09-21_20.03.31.jpg'),
(13,'fa265e34-5c0c-4f28-83aa-c06b00b905a1_343909.jpg'),
(14,'dc350ae4-2e3b-4b7a-af7a-817c0d005cc6_2015-09-21_20.03.31.jpg'),
(15,'6f52f716-8215-4140-b7b3-69b5a9096fbd_343909.jpg'),
(16,'9268e3c4-83d0-4568-9635-28061120a18e_2015-09-21_20.03.31.jpg'),
(17,'b2216cae-1a97-4919-8077-7a88fa255412_343909.jpg'),
(18,'a65ab108-b031-42ce-8486-f4b3925f8504_2015-09-21_20.03.31.jpg'),
(19,'280f1cf3-613f-4662-ad96-3361d392378b_206166.jpg'),
(20,'fe316352-4043-4780-b3ab-ade6cc25731c_2015-09-21_20.03.31 (1).jpg'),
(21,'1579914118081_71407_1403024246577637_569762628_n.jpg');

/*Table structure for table `material_categories` */

DROP TABLE IF EXISTS `material_categories`;

CREATE TABLE `material_categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `material_categories` */

insert  into `material_categories`(`id`,`title`) values 
(1,'22');

/*Table structure for table `material_category` */

DROP TABLE IF EXISTS `material_category`;

CREATE TABLE `material_category` (
  `material_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  KEY `FKqfxtvmwh23drrh802drknov76` (`category_id`),
  KEY `FKl6eb04ig3ge5gsmkl71rsxjww` (`material_id`),
  CONSTRAINT `FKl6eb04ig3ge5gsmkl71rsxjww` FOREIGN KEY (`material_id`) REFERENCES `materials` (`id`),
  CONSTRAINT `FKqfxtvmwh23drrh802drknov76` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `material_category` */

insert  into `material_category`(`material_id`,`category_id`) values 
(1,1);

/*Table structure for table `material_image` */

DROP TABLE IF EXISTS `material_image`;

CREATE TABLE `material_image` (
  `material_id` bigint(20) NOT NULL,
  `image_id` bigint(20) NOT NULL,
  KEY `FK76hr92v0nf78ar65n347wq22h` (`image_id`),
  KEY `FKlk7sv9kb49lp96wjax4loj6xh` (`material_id`),
  CONSTRAINT `FK76hr92v0nf78ar65n347wq22h` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`),
  CONSTRAINT `FKlk7sv9kb49lp96wjax4loj6xh` FOREIGN KEY (`material_id`) REFERENCES `materials` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `material_image` */

insert  into `material_image`(`material_id`,`image_id`) values 
(1,7);

/*Table structure for table `materials` */

DROP TABLE IF EXISTS `materials`;

CREATE TABLE `materials` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `invoice_price` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `material_category_id` bigint(20) DEFAULT NULL,
  `size_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc2c0nss3srtovxtiqflpsu8ft` (`material_category_id`),
  KEY `FK4myehhg41h77yp4w20goi7lyr` (`size_id`),
  CONSTRAINT `FK4myehhg41h77yp4w20goi7lyr` FOREIGN KEY (`size_id`) REFERENCES `sizes` (`id`),
  CONSTRAINT `FKc2c0nss3srtovxtiqflpsu8ft` FOREIGN KEY (`material_category_id`) REFERENCES `material_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `materials` */

insert  into `materials`(`id`,`title`,`invoice_price`,`price`,`material_category_id`,`size_id`) values 
(1,'sas',444,10,1,NULL);

/*Table structure for table `order_product` */

DROP TABLE IF EXISTS `order_product`;

CREATE TABLE `order_product` (
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  KEY `FKo6helt0ucmegaeachjpx40xhe` (`product_id`),
  KEY `FKl5mnj9n0di7k1v90yxnthkc73` (`order_id`),
  CONSTRAINT `FKl5mnj9n0di7k1v90yxnthkc73` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKo6helt0ucmegaeachjpx40xhe` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `order_product` */

insert  into `order_product`(`order_id`,`product_id`) values 
(5,23),
(5,24),
(6,23),
(6,24),
(7,24),
(8,24),
(10,24),
(11,24);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `deadline` datetime(6) NOT NULL,
  `time` time NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `order_status` enum('NEW','COMPLETED','PERFORMED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'NEW',
  PRIMARY KEY (`id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `orders` */

insert  into `orders`(`id`,`date`,`deadline`,`time`,`user_id`,`order_status`) values 
(2,'2020-01-23','2020-02-02 21:52:42.233000','21:52:42',5,NULL),
(5,'2020-01-23','2020-02-02 22:01:04.904000','22:01:04',NULL,'NEW'),
(6,'2020-01-23','2020-02-02 22:01:26.317000','22:01:26',NULL,'PERFORMED'),
(7,'2020-01-23','2020-02-02 22:32:42.346000','22:32:42',5,'NEW'),
(8,'2020-01-23','2020-02-02 22:35:00.518000','22:35:00',5,'NEW'),
(10,'2020-01-23','2020-02-02 23:32:02.546000','23:32:02',4,'NEW'),
(11,'2020-01-23','2020-02-02 23:59:41.027000','23:59:41',4,'COMPLETED'),
(12,'2020-01-25','2020-01-13 03:01:55.000000','23:11:45',NULL,'NEW');

/*Table structure for table `product_image` */

DROP TABLE IF EXISTS `product_image`;

CREATE TABLE `product_image` (
  `product_id` bigint(20) NOT NULL,
  `image_id` bigint(20) NOT NULL,
  KEY `FKanpsonqgb5uwbw83m3m7phnms` (`image_id`),
  KEY `FK1n91c4vdhw8pa4frngs4qbbvs` (`product_id`),
  CONSTRAINT `FK1n91c4vdhw8pa4frngs4qbbvs` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKanpsonqgb5uwbw83m3m7phnms` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `product_image` */

insert  into `product_image`(`product_id`,`image_id`) values 
(23,1),
(23,2),
(23,3),
(24,17),
(24,18);

/*Table structure for table `product_material` */

DROP TABLE IF EXISTS `product_material`;

CREATE TABLE `product_material` (
  `product_id` bigint(20) NOT NULL,
  `material_id` bigint(20) NOT NULL,
  KEY `FK6uuu5e4lo8i9eprdau4hi2799` (`material_id`),
  KEY `FKarnmjkx8mq6icjq7lrf4ojcx4` (`product_id`),
  CONSTRAINT `FK6uuu5e4lo8i9eprdau4hi2799` FOREIGN KEY (`material_id`) REFERENCES `materials` (`id`),
  CONSTRAINT `FKarnmjkx8mq6icjq7lrf4ojcx4` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `product_material` */

insert  into `product_material`(`product_id`,`material_id`) values 
(24,1),
(23,1);

/*Table structure for table `products` */

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `size_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjtp03phh84ohguj0rhvlk81g7` (`size_id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  CONSTRAINT `FKjtp03phh84ohguj0rhvlk81g7` FOREIGN KEY (`size_id`) REFERENCES `sizes` (`id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `products` */

insert  into `products`(`id`,`count`,`description`,`price`,`title`,`category_id`,`size_id`) values 
(23,555,'nmmm',10,'jk',1,13),
(24,10,'50',20,'io',1,12);

/*Table structure for table `sizes` */

DROP TABLE IF EXISTS `sizes`;

CREATE TABLE `sizes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `height` double DEFAULT NULL,
  `length` double DEFAULT NULL,
  `width` double DEFAULT NULL,
  `square_meter` double DEFAULT NULL,
  `thickness` double DEFAULT NULL,
  `weight` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sizes` */

insert  into `sizes`(`id`,`height`,`length`,`width`,`square_meter`,`thickness`,`weight`) values 
(12,15,0,50,0,0,0),
(13,8,0,1000,0,0,0),
(14,50,0,985,0,0,0);

/*Table structure for table `user_product` */

DROP TABLE IF EXISTS `user_product`;

CREATE TABLE `user_product` (
  `user_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  KEY `FKsu8beo945wpgaux2kwu677srs` (`product_id`),
  KEY `FKctnnp6lvp5tkjb6fm0k0gmhns` (`user_id`),
  CONSTRAINT `FKctnnp6lvp5tkjb6fm0k0gmhns` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKsu8beo945wpgaux2kwu677srs` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_product` */

insert  into `user_product`(`user_id`,`product_id`) values 
(5,23),
(5,24);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `passport_id` varchar(255) DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `surname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_type` enum('ADMIN','USER') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'USER',
  `address_id` bigint(20) DEFAULT NULL,
  `image_id` bigint(20) DEFAULT NULL,
  `is_enable` bit(1) DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKditu6lr4ek16tkxtdsne0gxib` (`address_id`),
  KEY `FK17herqt2to4hyl5q5r5ogbxk9` (`image_id`),
  CONSTRAINT `FK17herqt2to4hyl5q5r5ogbxk9` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`),
  CONSTRAINT `FKditu6lr4ek16tkxtdsne0gxib` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users` */

insert  into `users`(`id`,`email`,`name`,`passport_id`,`password`,`phone_number`,`surname`,`user_type`,`address_id`,`image_id`,`is_enable`,`token`) values 
(4,'string','string','string','$2a$10$uH3sGXRj/hf5cTB4qQ7Ws.99i/CM58cvRGYqUKpfs44kVkoeWj0Zi','string','string','ADMIN',1,21,'','string'),
(5,'ssss','string','string','$2a$10$ZNaRHDmajTjXX3LqSDBIguZtw3tWU5cR9uATxup1jDua5MmGpbTtu','string','string','ADMIN',2,2,'','string');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
