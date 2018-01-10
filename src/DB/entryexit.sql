drop database if exists entryexit;

create database entryexit default character set utf8;
use entryexit;
/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : entryexit

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2015-02-11 14:47:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for clerk
-- ----------------------------
DROP TABLE IF EXISTS `clerk`;
CREATE TABLE `clerk` (
  `clerk_id` int(11) NOT NULL AUTO_INCREMENT,
  `clerk_username` varchar(25) NOT NULL,
  `clerk_password` varchar(15) NOT NULL,
  `department_id` int(11) NOT NULL,
  PRIMARY KEY (`clerk_id`),
  KEY `dept_id` (`department_id`),
  CONSTRAINT `clerk_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clerk
-- ----------------------------
INSERT INTO `clerk` VALUES ('1', 'hyf', 'hyf', '2');
INSERT INTO `clerk` VALUES ('2', 'jack', '1234', '4');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(50) DEFAULT NULL,
  `department_address` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '深圳罗湖分局', '罗湖区林荫大道北266号');
INSERT INTO `department` VALUES ('2', '深圳福田分局', '福田区华强北路266号');
INSERT INTO `department` VALUES ('3', '深圳南山分局', '南山区环北大道166号');
INSERT INTO `department` VALUES ('4', '深圳盐田公安分局', '深圳盐田某大道天桥底3');

-- ----------------------------
-- Table structure for department_service
-- ----------------------------
DROP TABLE IF EXISTS `department_service`;
CREATE TABLE `department_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dept_id` (`department_id`),
  KEY `serv_id` (`service_id`),
  CONSTRAINT `department_service_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`) ON DELETE CASCADE,
  CONSTRAINT `department_service_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `service` (`service_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department_service
-- ----------------------------
INSERT INTO `department_service` VALUES ('1', '2', '2');
INSERT INTO `department_service` VALUES ('2', '2', '1');
INSERT INTO `department_service` VALUES ('3', '3', '3');
INSERT INTO `department_service` VALUES ('4', '3', '2');
INSERT INTO `department_service` VALUES ('5', '3', '1');
INSERT INTO `department_service` VALUES ('6', '4', '4');
INSERT INTO `department_service` VALUES ('7', '4', '6');
INSERT INTO `department_service` VALUES ('8', '1', '6');

-- ----------------------------
-- Table structure for prebook
-- ----------------------------
DROP TABLE IF EXISTS `prebook`;
CREATE TABLE `prebook` (
  `prebook_id` int(11) NOT NULL AUTO_INCREMENT,
  `passport_id` varchar(25) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `department_id` int(11) DEFAULT NULL,
  `service_id` int(11) DEFAULT NULL,
  `appointment_date` varchar(90) DEFAULT NULL,
  `appointment_time` varchar(20) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `verification` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`prebook_id`),
  KEY `department` (`department_id`),
  KEY `service` (`service_id`),
  CONSTRAINT `prebook_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`),
  CONSTRAINT `prebook_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `service` (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prebook
-- ----------------------------
INSERT INTO `prebook` VALUES ('11', '440785197809128522', '13423432', '1', '1', '2015-02-05 星期四', '10:30-11:00', '1', '20150205143537');
INSERT INTO `prebook` VALUES ('12', '440785197809128522', '13423432', '1', '1', '2015-02-05 星期四', '10:30-11:00', '1', '20150205143651');
INSERT INTO `prebook` VALUES ('13', '440785197809128522', '13423432', '1', '1', '2015-02-05 星期四', '10:30-11:00', '0', '20150205143651');
INSERT INTO `prebook` VALUES ('14', '440785197809128522', '13423432', '1', '1', '2015-02-05 星期四', '10:30-11:00', '0', '20150205143739');
INSERT INTO `prebook` VALUES ('15', '440785197809128522', '13423432', '1', '1', '2015-02-05 星期四', '10:30-11:00', '1', '20150205143814');

-- ----------------------------
-- Table structure for service
-- ----------------------------
DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `service_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_name` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of service
-- ----------------------------
INSERT INTO `service` VALUES ('1', '个人港澳自由行');
INSERT INTO `service` VALUES ('2', '香港移民定居');
INSERT INTO `service` VALUES ('3', '港澳护照台湾');
INSERT INTO `service` VALUES ('4', '商务签注及备案业务');
INSERT INTO `service` VALUES ('6', '普通业务（除定居）');
INSERT INTO `service` VALUES ('8', '商务、护照、旅游');
INSERT INTO `service` VALUES ('9', '非粤籍个人游');

-- ----------------------------
-- Table structure for super_user
-- ----------------------------
DROP TABLE IF EXISTS `super_user`;
CREATE TABLE `super_user` (
  `super_id` int(11) NOT NULL AUTO_INCREMENT,
  `super_name` varchar(25) NOT NULL,
  `super_pwd` varchar(15) NOT NULL,
  PRIMARY KEY (`super_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of super_user
-- ----------------------------
INSERT INTO `super_user` VALUES ('1', 'admin', 'admin');
