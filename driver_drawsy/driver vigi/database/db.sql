/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.6.12-log : Database - drivervigi_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`drivervigi_db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `drivervigi_db`;

/*Table structure for table `detection` */

DROP TABLE IF EXISTS `detection`;

CREATE TABLE `detection` (
  `detection_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `date_time` varchar(100) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`detection_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `disaster_news` */

insert  into `disaster_news`(`news_id`,`datetime`,`place`,`latitude`,`longitude`,`disaster_type`,`description`) values 
(4,'now()','kozhikode','9.997089332190317','76.28730196826173','flood','heavy flood'),
(5,'now()','ernamkulam','9.97792684783173','76.28305435180664','earthquake','heavy earthquake'),
(6,'2023-10-25 10:58:30','ernamkulam','9.97792684783173','76.28305435180664','earthquake','heavy earthquake');

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
  PRIMARY KEY (`emergency_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `emergencies` */

insert  into `emergencies`(`emergency_id`,`user_id`,`emergency`,`date`,`latitude`,`longitude`,`status`) values 
(5,'8','www','2023-10-24','9.977980240986346','76.28305435180664','pending'),
(6,'8','wesd','2023-10-24','9.9','76.','pending'),
(7,'8','www','2023-10-24','9.986718105298767','76.29924959852606','pending'),
(9,'8','accident','2023-10-24','9.98747887603121','76.28148264601141','pending'),
(10,'8','accident','2023-10-24','9.98747887603121','76.28148264601141','pending'),
(11,'8','www','2023-10-24','9.988293036447752','76.28811836242676','pending'),
(12,'8','www','2023-10-24','9.988293036447752','76.28811836242676','pending'),
(13,'8','www','2023-10-24','9.988293036447752','76.28811836242676','pending'),
(14,'8','accident','2023-12-07','9.997089332190317','76.28661532275392','pending');

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

/*Data for the table `emergency` */

insert  into `emergency`(`emergency_id`,`login_id`,`first_name`,`last_name`,`type`,`phone`,`email`) values 
(12,17,'anna1','r','Ambulance1','2323233232','anna1@gmail.com'),
(19,36,'anna1','xsaceeee','xascds','02323233232','annasd1@gmail.com');

/*Table structure for table `emergency_status` */

DROP TABLE IF EXISTS `emergency_status`;

CREATE TABLE `emergency_status` (
  `status_id` int(11) NOT NULL AUTO_INCREMENT,
  `emergency_id` varchar(100) DEFAULT NULL,
  `update_status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `emergency_status` */

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `usertype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`user_name`,`password`,`usertype`) values 
(1,'admin','adm','admin'),
(14,'nourinazer','12345','user'),
(15,'empuser1','1234','emergency'),
(16,'anu','1234','emergency'),
(17,'anna','1234','emergency'),
(18,'nourinazer','12345','emergency'),
(19,'nourinazer','norin','emergency'),
(20,'noo','','admin'),
(21,'','','admin'),
(22,'','','admin'),
(23,'','','admin'),
(24,'','','admin'),
(25,'','','admin'),
(26,'nnn','','admin'),
(27,'nnn','','admin'),
(28,'nnn','','admin'),
(29,'nnn','','admin'),
(30,'nnn','','admin'),
(31,'nnn','','admin'),
(32,'d','dxsad','emergency'),
(33,'','','emergency'),
(34,'','','emergency'),
(35,'scanak','','emergency'),
(36,'asadas','sadasc','emergency');

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `date_time` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

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
(12,1,'not well','2023-12-07 10:54:01','pending');

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

/*Data for the table `register` */

insert  into `register`(`user_id`,`login_id`,`first_name`,`last_name`,`vehicle_no`,`phone`,`email`) values 
(1,1,'adegf','fcdsb','5713','08075457580','nourinpk2003@gmail.com'),
(8,14,'nourin','pk','5317','5317','nourinpk2003@gmail.com'),
(9,20,'','','','',''),
(10,21,'','','','',''),
(11,22,'','','','',''),
(12,23,'','','','',''),
(13,24,'','','','',''),
(14,25,'','','','',''),
(15,26,'','','','',''),
(16,27,'','','','',''),
(17,28,'','','','',''),
(18,29,'','','','',''),
(19,30,'','','','',''),
(20,31,'','','','','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
