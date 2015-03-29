/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50509
Source Host           : localhost:3306
Source Database       : springmvc

Target Server Type    : MYSQL
Target Server Version : 50509
File Encoding         : 65001

Date: 2015-03-29 23:14:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_child`
-- ----------------------------
DROP TABLE IF EXISTS `t_child`;
CREATE TABLE `t_child` (
  `child_id` int(11) NOT NULL AUTO_INCREMENT,
  `child_name` char(20) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `child_information` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`child_id`),
  KEY `fk_child_user` (`parent_id`),
  CONSTRAINT `fk_child_user` FOREIGN KEY (`parent_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_child
-- ----------------------------
INSERT INTO `t_child` VALUES ('1', 'achild', '1', 'aa\'s kid');
INSERT INTO `t_child` VALUES ('2', 'bchild', '1', 'aa\'s kid');

-- ----------------------------
-- Table structure for `t_company`
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` char(30) DEFAULT NULL,
  `company_information` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_company
-- ----------------------------
INSERT INTO `t_company` VALUES ('1', 'jd', 'jd e-commence');
INSERT INTO `t_company` VALUES ('2', 'taobao', 'ali c2c');
INSERT INTO `t_company` VALUES ('3', 'tmall', 'ali b2c');
INSERT INTO `t_company` VALUES ('4', '163', '163 web');

-- ----------------------------
-- Table structure for `t_position`
-- ----------------------------
DROP TABLE IF EXISTS `t_position`;
CREATE TABLE `t_position` (
  `position_id` int(11) NOT NULL AUTO_INCREMENT,
  `position_name` char(255) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`position_id`),
  KEY `fk_position_company` (`company_id`),
  CONSTRAINT `fk_position_company` FOREIGN KEY (`company_id`) REFERENCES `t_company` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_position
-- ----------------------------
INSERT INTO `t_position` VALUES ('1', 'developer', '1');
INSERT INTO `t_position` VALUES ('2', 'developer', '2');
INSERT INTO `t_position` VALUES ('3', 'developer', '3');
INSERT INTO `t_position` VALUES ('4', 'developer', '4');
INSERT INTO `t_position` VALUES ('5', 'tester', '1');
INSERT INTO `t_position` VALUES ('6', 'tester', '2');
INSERT INTO `t_position` VALUES ('7', 'tester', '3');
INSERT INTO `t_position` VALUES ('8', 'tester', '4');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `mate_id` int(11) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `position_id` int(11) DEFAULT NULL,
  `information` text,
  PRIMARY KEY (`user_id`),
  KEY `fk_user_position` (`position_id`),
  KEY `fk_user_company` (`company_id`),
  KEY `fk_user_user` (`mate_id`),
  CONSTRAINT `fk_user_company` FOREIGN KEY (`company_id`) REFERENCES `t_company` (`company_id`),
  CONSTRAINT `fk_user_position` FOREIGN KEY (`position_id`) REFERENCES `t_position` (`position_id`),
  CONSTRAINT `fk_user_user` FOREIGN KEY (`mate_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'aa', '28', '2', '1', '1', 'aa worker');
INSERT INTO `t_user` VALUES ('2', 'bb', '29', '1', '2', '2', 'bb worker');
INSERT INTO `t_user` VALUES ('5', 'aa', '28', null, '2', '3', 'enginer');
INSERT INTO `t_user` VALUES ('6', 'aa', '28', null, null, null, 'enginer');
INSERT INTO `t_user` VALUES ('7', 'aa', '28', null, null, null, 'enginer');
INSERT INTO `t_user` VALUES ('8', 'aa', '28', null, null, null, 'enginer');
INSERT INTO `t_user` VALUES ('9', 'aa', '28', null, null, null, 'enginer');
INSERT INTO `t_user` VALUES ('10', 'aa', '28', null, null, null, 'enginer');
INSERT INTO `t_user` VALUES ('11', 'aa', '28', null, null, null, 'enginer');
INSERT INTO `t_user` VALUES ('12', 'vv', '28', null, null, null, 'enginer');
INSERT INTO `t_user` VALUES ('13', 'vv', '28', null, null, null, 'enginer');
INSERT INTO `t_user` VALUES ('14', 'vv', '28', null, null, null, 'enginer');
INSERT INTO `t_user` VALUES ('15', 'vv', '28', null, null, null, 'enginer');
INSERT INTO `t_user` VALUES ('16', 'vv', '28', null, null, null, 'enginer');
INSERT INTO `t_user` VALUES ('17', 'vv', '28', null, null, null, 'enginer');
