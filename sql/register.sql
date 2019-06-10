/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50617
Source Host           : 127.0.0.1:3306
Source Database       : register

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2018-06-23 00:20:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `username` varchar(40) DEFAULT NULL,
  `groupname` varchar(20) DEFAULT NULL,
  `friendname` varchar(40) NOT NULL,
  `phonenumber` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `QQ` varchar(15) DEFAULT NULL,
  `wechat` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`friendname`),
  KEY `friend_ibfk_1` (`username`),
  CONSTRAINT `friend_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES ('huangzyi', 'h', 'dejwnef', '14156559', 'Male', '15614646884', 'f84f89r');
INSERT INTO `friend` VALUES ('huangzyi', 'h', 'fnuf', '118596566', 'Female', '251561561', '1dsd41d');
INSERT INTO `friend` VALUES ('huangzyi', 'h', 'fsi', '516814988', 'Male', '89449894', 'jenf4896e');
INSERT INTO `friend` VALUES ('huangzyi', 'fuie', 'fuei', null, 'Female', null, null);
INSERT INTO `friend` VALUES ('huangzyi', 'h', 'tiorejteri', '849949488', 'Male', '848944985', '45645484');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('fnijf', 'fieofje');
INSERT INTO `user` VALUES ('gtng', '123');
INSERT INTO `user` VALUES ('hjfjief', '4864465');
INSERT INTO `user` VALUES ('huangzyi', '123');
INSERT INTO `user` VALUES ('test', 'test');
INSERT INTO `user` VALUES ('test2', 'test');
INSERT INTO `user` VALUES ('test3', 'test');
INSERT INTO `user` VALUES ('tfr', 'tc');
INSERT INTO `user` VALUES ('uir', 'ufi');
