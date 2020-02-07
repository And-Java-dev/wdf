/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.28-log : Database - wdf
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wdf` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `address` */

/*Table structure for table `categories` */

DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `categories` */

insert  into `categories`(`id`,`title`) values (1,'doors'),(2,'windows'),(3,'furnitures');

/*Table structure for table `images` */

DROP TABLE IF EXISTS `images`;

CREATE TABLE `images` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `images` */

insert  into `images`(`id`,`name`) values (1,'success.jpg');

/*Table structure for table `material_categories` */

DROP TABLE IF EXISTS `material_categories`;

CREATE TABLE `material_categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `material_categories` */

insert  into `material_categories`(`id`,`title`) values (1,'tree'),(2,'laminate');

/*Table structure for table `material_category` */

DROP TABLE IF EXISTS `material_category`;

CREATE TABLE `material_category` (
  `material_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  KEY `FKqfxtvmwh23drrh802drknov76` (`category_id`),
  KEY `FKl6eb04ig3ge5gsmkl71rsxjww` (`material_id`),
  CONSTRAINT `FKl6eb04ig3ge5gsmkl71rsxjww` FOREIGN KEY (`material_id`) REFERENCES `materials` (`id`),
  CONSTRAINT `FKqfxtvmwh23drrh802drknov76` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `material_category` */

insert  into `material_category`(`material_id`,`category_id`) values (1,1);

/*Table structure for table `material_image` */

DROP TABLE IF EXISTS `material_image`;

CREATE TABLE `material_image` (
  `material_id` bigint(20) NOT NULL,
  `image_id` bigint(20) NOT NULL,
  KEY `FK76hr92v0nf78ar65n347wq22h` (`image_id`),
  KEY `FKlk7sv9kb49lp96wjax4loj6xh` (`material_id`),
  CONSTRAINT `FK76hr92v0nf78ar65n347wq22h` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`),
  CONSTRAINT `FKlk7sv9kb49lp96wjax4loj6xh` FOREIGN KEY (`material_id`) REFERENCES `materials` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `material_image` */

insert  into `material_image`(`material_id`,`image_id`) values (1,1);

/*Table structure for table `materials` */

DROP TABLE IF EXISTS `materials`;

CREATE TABLE `materials` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `invoice_price` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `view` int(11) DEFAULT NULL,
  `material_category_id` bigint(20) DEFAULT NULL,
  `size_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc2c0nss3srtovxtiqflpsu8ft` (`material_category_id`),
  KEY `FK4myehhg41h77yp4w20goi7lyr` (`size_id`),
  CONSTRAINT `FK4myehhg41h77yp4w20goi7lyr` FOREIGN KEY (`size_id`) REFERENCES `sizes` (`id`),
  CONSTRAINT `FKc2c0nss3srtovxtiqflpsu8ft` FOREIGN KEY (`material_category_id`) REFERENCES `material_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `materials` */

insert  into `materials`(`id`,`invoice_price`,`price`,`rating`,`title`,`view`,`material_category_id`,`size_id`) values (1,12000,2000,3.3,'door material',19,1,NULL),(2,10000,4500,4.2,'laminat',22,2,NULL);

/*Table structure for table `order_product` */

DROP TABLE IF EXISTS `order_product`;

CREATE TABLE `order_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) DEFAULT NULL,
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl5mnj9n0di7k1v90yxnthkc73` (`order_id`),
  KEY `FKo6helt0ucmegaeachjpx40xhe` (`product_id`),
  CONSTRAINT `FKl5mnj9n0di7k1v90yxnthkc73` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKo6helt0ucmegaeachjpx40xhe` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `order_product` */

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `order_products_size` int(11) DEFAULT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `time` time DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `orders` */

/*Table structure for table `product_image` */

DROP TABLE IF EXISTS `product_image`;

CREATE TABLE `product_image` (
  `product_id` bigint(20) NOT NULL,
  `image_id` bigint(20) NOT NULL,
  KEY `FKanpsonqgb5uwbw83m3m7phnms` (`image_id`),
  KEY `FK1n91c4vdhw8pa4frngs4qbbvs` (`product_id`),
  CONSTRAINT `FK1n91c4vdhw8pa4frngs4qbbvs` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKanpsonqgb5uwbw83m3m7phnms` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `product_image` */

insert  into `product_image`(`product_id`,`image_id`) values (1,1);

/*Table structure for table `product_material` */

DROP TABLE IF EXISTS `product_material`;

CREATE TABLE `product_material` (
  `product_id` bigint(20) NOT NULL,
  `material_id` bigint(20) NOT NULL,
  KEY `FK6uuu5e4lo8i9eprdau4hi2799` (`material_id`),
  KEY `FKarnmjkx8mq6icjq7lrf4ojcx4` (`product_id`),
  CONSTRAINT `FK6uuu5e4lo8i9eprdau4hi2799` FOREIGN KEY (`material_id`) REFERENCES `materials` (`id`),
  CONSTRAINT `FKarnmjkx8mq6icjq7lrf4ojcx4` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `product_material` */

insert  into `product_material`(`product_id`,`material_id`) values (1,1),(1,2);

/*Table structure for table `products` */

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `view` int(11) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `size_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  KEY `FKjtp03phh84ohguj0rhvlk81g7` (`size_id`),
  CONSTRAINT `FKjtp03phh84ohguj0rhvlk81g7` FOREIGN KEY (`size_id`) REFERENCES `sizes` (`id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `products` */

insert  into `products`(`id`,`count`,`description`,`price`,`rating`,`title`,`view`,`category_id`,`size_id`) values (1,100,'brown wooden door',180000,3.8,'Door',12,1,1);

/*Table structure for table `sizes` */

DROP TABLE IF EXISTS `sizes`;

CREATE TABLE `sizes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `height` double DEFAULT NULL,
  `length` double DEFAULT NULL,
  `square_meter` double DEFAULT NULL,
  `thickness` double DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `width` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `sizes` */

insert  into `sizes`(`id`,`height`,`length`,`square_meter`,`thickness`,`weight`,`width`) values (1,2.2,NULL,NULL,NULL,NULL,0.9);

/*Table structure for table `user_product` */

DROP TABLE IF EXISTS `user_product`;

CREATE TABLE `user_product` (
  `user_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  KEY `FKsu8beo945wpgaux2kwu677srs` (`product_id`),
  KEY `FKctnnp6lvp5tkjb6fm0k0gmhns` (`user_id`),
  CONSTRAINT `FKctnnp6lvp5tkjb6fm0k0gmhns` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKsu8beo945wpgaux2kwu677srs` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_product` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `is_enable` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `passport_id` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_type` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `image_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKditu6lr4ek16tkxtdsne0gxib` (`address_id`),
  KEY `FK17herqt2to4hyl5q5r5ogbxk9` (`image_id`),
  CONSTRAINT `FK17herqt2to4hyl5q5r5ogbxk9` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`),
  CONSTRAINT `FKditu6lr4ek16tkxtdsne0gxib` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `users` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
