/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.9 : Database - python_drivervigi_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`python_drivervigi_db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `python_drivervigi_db`;

/*Table structure for table `adddetails` */

DROP TABLE IF EXISTS `adddetails`;

CREATE TABLE `adddetails` (
  `details_id` int(11) NOT NULL AUTO_INCREMENT,
  `place` varchar(1000) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `latitude` varchar(1000) DEFAULT NULL,
  `longitude` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`details_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `adddetails` */

insert  into `adddetails`(`details_id`,`place`,`type`,`latitude`,`longitude`) values 
(1,'kochi','Ai Camra','9.981981981981981','76.27169421925028'),
(2,'kochi','Atm','9.981981981981981','76.27169421925028');

/*Table structure for table `ambulance` */

DROP TABLE IF EXISTS `ambulance`;

CREATE TABLE `ambulance` (
  `ambulance_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1000) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `contact_no` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ambulance_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `ambulance` */

insert  into `ambulance`(`ambulance_id`,`name`,`place`,`contact_no`,`email`,`latitude`,`longitude`) values 
(1,'ambulance testing','kochi','9526610862','shemi96ven@gmail.com','9.980490284426859','76.30047697894288');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

/*Data for the table `emergency` */

insert  into `emergency`(`emergency_id`,`login_id`,`first_name`,`last_name`,`type`,`phone`,`email`) values 
(12,17,'anna1','r','Ambulance1','2323233232','anna1@gmail.com'),
(19,37,'anna1','xsaceeee','xascds','02323233232','annasd1@gmail.com');

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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`user_name`,`password`,`usertype`) values 
(1,'admin','admin','admin'),
(14,'u','u','user'),
(15,'empuser1','1234','emergency'),
(16,'anu','1234','emergency'),
(17,'anna','1234','emergency'),
(18,'nourinazer','12345','emergency'),
(19,'n','n','emergency'),
(32,'d','dxsad','emergency'),
(35,'scanak','','emergency'),
(36,'asadas','sadasc','emergency'),
(37,'u','b','emergency'),
(38,'avhgrd','gfds','user');

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `date_time` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

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

/*Table structure for table `notification` */

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `notification_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `details_id` int(11) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`notification_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `notification` */

insert  into `notification`(`notification_id`,`user_id`,`details_id`,`latitude`,`longitude`,`type`) values 
(1,8,1,'9.981981981981981','76.27169421925028','Ai Camra'),
(2,8,1,'9.981981981981981','76.27169421925028','Ai Camra'),
(3,8,1,'9.981981981981981','76.27169421925028','Ai Camra');

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

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
(20,31,'','','','',''),
(21,37,'aneesha','najiha','0987','0987','nanolian@gmail.com'),
(22,38,'ffds','fds','1234','5155447251','fesrttuy@gmil.com');

/*Table structure for table `speed` */

DROP TABLE IF EXISTS `speed`;

CREATE TABLE `speed` (
  `speed_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `speed` varchar(100) DEFAULT NULL,
  `mode` varchar(100) DEFAULT NULL,
  `brightness` varchar(100) DEFAULT NULL,
  `touch` varchar(100) DEFAULT NULL,
  `callblock` varchar(100) DEFAULT NULL,
  `automessage` varchar(100) DEFAULT NULL,
  `message` varchar(100) DEFAULT NULL,
  `messagetype` varchar(100) DEFAULT NULL,
  `bluetooth` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`speed_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `speed` */

insert  into `speed`(`speed_id`,`user_id`,`speed`,`mode`,`brightness`,`touch`,`callblock`,`automessage`,`message`,`messagetype`,`bluetooth`) values 
(1,8,'0-40','SILENT','35','On','On','NO','hhhhg','ALL','On');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
