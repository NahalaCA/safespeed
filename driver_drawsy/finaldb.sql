/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.28-MariaDB : Database - python_drivervigi_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`python_drivervigi_db` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

USE `python_drivervigi_db`;

/*Table structure for table `detection` */

DROP TABLE IF EXISTS `detection`;

CREATE TABLE `detection` (
  `detection_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `date_time` varchar(100) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`detection_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `detection` */

insert  into `detection`(`detection_id`,`user_id`,`date_time`,`image`) values 
(1,8,'2023-10-25 10:58:30','img1.jpg'),
(2,1,'2023-10-25 10:58:30','C:\\Users\\rinsi\\OneDrive\\Desktop\\driver vigi\\static\\assets\\img\\img1.png');

/*Table structure for table `disaster_news` */

DROP TABLE IF EXISTS `disaster_news`;

CREATE TABLE `disaster_news` (
  `news_id` int(11) NOT NULL AUTO_INCREMENT,
  `datetime` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  `disaster_type` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `disaster_news` */

insert  into `disaster_news`(`news_id`,`datetime`,`place`,`latitude`,`longitude`,`disaster_type`,`description`) values 
(4,'now()','kozhikode','9.997089332190317','76.28730196826173','flood','heavy flood'),
(5,'now()','ernamkulam','9.97792684783173','76.28305435180664','earthquake','heavy earthquake'),
(6,'2023-10-25 10:58:30','ernamkulam','9.97792684783173','76.28305435180664','earthquake','heavy earthquake'),
(7,'2023-12-30 11:01:36','chavakkad','9.988194374329936','76.28130466651174','flood','heavy'),
(8,'2023-12-30 11:02:07','kacheripady','9.988194374329936','76.28130466651174','flood','heavy');

/*Table structure for table `emergencies` */

DROP TABLE IF EXISTS `emergencies`;

CREATE TABLE `emergencies` (
  `emergency_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) DEFAULT NULL,
  `emergency` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`emergency_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `emergencies` */

insert  into `emergencies`(`emergency_id`,`user_id`,`emergency`,`date`,`latitude`,`longitude`,`status`,`image`) values 
(5,'8','www','2023-10-24','9.977980240986346','76.28305435180664','pending',NULL),
(6,'8','wesd','2023-10-24','9.9','76.','pending',NULL),
(7,'8','www','2023-10-24','9.986718105298767','76.29924959852606','pending',NULL),
(9,'8','accident','2023-10-24','9.98747887603121','76.28148264601141','pending',NULL),
(10,'8','accident','2023-10-24','9.98747887603121','76.28148264601141','pending',NULL),
(11,'8','www','2023-10-24','9.988293036447752','76.28811836242676','pending',NULL),
(12,'8','www','2023-10-24','9.988293036447752','76.28811836242676','pending',NULL),
(13,'8','www','2023-10-24','9.988293036447752','76.28811836242676','pending',NULL),
(14,'8','accident','2023-12-07','9.997089332190317','76.28661532275392','pending',NULL),
(15,'21','bb','2024-01-01','','','pending','static/images/d29cd0b0-07e2-4765-9cba-6b6a434814411704094071826.png'),
(16,'21','jnn','2024-01-01','','','solved','static/images/f15bc709-faba-40c0-b2da-06b632506eea1704094920029.png'),
(17,'21','yggg','2024-01-01','9.9763321','76.2862525','solved','static/images/ed6b10b2-d9a4-43e1-8e4e-4a0855db05c31704095188661.png'),
(18,'21','nn','2024-01-01','9.9763311','76.2862629','solved','static/images/dc5055d3-1dff-41ff-9b61-dcaf4e441ab21704095224691.png');

/*Table structure for table `emergency` */

DROP TABLE IF EXISTS `emergency`;

CREATE TABLE `emergency` (
  `emergency_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`emergency_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `emergency` */

/*Table structure for table `emergency_status` */

DROP TABLE IF EXISTS `emergency_status`;

CREATE TABLE `emergency_status` (
  `status_id` int(11) NOT NULL AUTO_INCREMENT,
  `emergency_id` varchar(100) DEFAULT NULL,
  `update_status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `emergency_status` */

/*Table structure for table `heade_detection` */

DROP TABLE IF EXISTS `heade_detection`;

CREATE TABLE `heade_detection` (
  `head_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `detection` varchar(100) DEFAULT NULL,
  `head_date` varchar(100) DEFAULT NULL,
  `cur_time` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`head_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `heade_detection` */

insert  into `heade_detection`(`head_id`,`user_id`,`detection`,`head_date`,`cur_time`) values 
(48,1,'drowsiness detected on this user','2024-01-06','10:00'),
(49,1,'drowsiness detected on this user','2024-01-06','10:00');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `usertype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `login` */

insert  into `login`(`login_id`,`user_name`,`password`,`usertype`) values 
(1,'admin','admin','admin'),
(3,'u','u','user');

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `date_time` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `message` */

insert  into `message`(`message_id`,`user_id`,`description`,`status`,`date_time`) values 
(1,0,'2023-10-05T13:22','1','pending'),
(2,0,'2023-10-19T13:23','8','pending'),
(3,1,'uuu','2023-10-26T13:27','pending'),
(4,1,'uuu','pending','2023-10-26T13:27'),
(5,1,'fff','pending','2023-10-20T13:25'),
(6,1,'not well','pending','2023-10-10T11:30'),
(7,1,'not well','pending','2023-10-28T11:58'),
(8,1,'not well','pending','2023-10-20T11:59'),
(9,8,' ddfsdfsxgfxc','2023-10-25 14:25:06','pending'),
(10,8,'not well','2023-12-07 10:25:42','pending'),
(11,8,'hhhhh','2023-12-07 10:50:29','pending'),
(12,21,'not well','2023-12-07 10:54:01','pending'),
(13,NULL,NULL,NULL,NULL);

/*Table structure for table `register` */

DROP TABLE IF EXISTS `register`;

CREATE TABLE `register` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `vehicle_no` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `register` */

insert  into `register`(`user_id`,`login_id`,`first_name`,`last_name`,`vehicle_no`,`phone`,`email`) values 
(1,3,'Akhila','kr','kl23h5678','9961816659','akhilaraveeendran@gmail.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
