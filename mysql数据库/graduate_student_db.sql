/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : graduate_student_db

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2017-12-06 23:48:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `username` varchar(20) NOT NULL default '',
  `password` varchar(32) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('a', 'a');

-- ----------------------------
-- Table structure for `t_armystudent`
-- ----------------------------
DROP TABLE IF EXISTS `t_armystudent`;
CREATE TABLE `t_armystudent` (
  `id` int(11) NOT NULL auto_increment COMMENT '记录id',
  `studentNumber` varchar(20) NOT NULL COMMENT '学号',
  `studentName` varchar(20) NOT NULL COMMENT '姓名',
  `specialObj` varchar(20) NOT NULL COMMENT '专业',
  `gradeObj` int(11) NOT NULL COMMENT '年级',
  `yearMonth` varchar(20) NOT NULL COMMENT '年月份',
  `areaInfo` varchar(20) NOT NULL COMMENT '地区',
  `junqu` varchar(30) NOT NULL COMMENT '参军军区',
  `positionName` varchar(20) NOT NULL COMMENT '职位',
  `shouru` varchar(20) NOT NULL COMMENT '收入',
  PRIMARY KEY  (`id`),
  KEY `specialObj` (`specialObj`),
  KEY `gradeObj` (`gradeObj`),
  CONSTRAINT `t_armystudent_ibfk_1` FOREIGN KEY (`specialObj`) REFERENCES `t_specialinfo` (`specialNo`),
  CONSTRAINT `t_armystudent_ibfk_2` FOREIGN KEY (`gradeObj`) REFERENCES `t_gradeinfo` (`gradeId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_armystudent
-- ----------------------------
INSERT INTO `t_armystudent` VALUES ('1', 'STU005', '李晓晓', 'ZY001', '1', '2017-08', '浙江杭州', '杭州军区总部', '工兵', '3800');

-- ----------------------------
-- Table structure for `t_entrepreneurstudent`
-- ----------------------------
DROP TABLE IF EXISTS `t_entrepreneurstudent`;
CREATE TABLE `t_entrepreneurstudent` (
  `id` int(11) NOT NULL auto_increment COMMENT '记录id',
  `studentNumber` varchar(20) NOT NULL COMMENT '学号',
  `studentName` varchar(20) NOT NULL COMMENT '姓名',
  `specialObj` varchar(20) NOT NULL COMMENT '专业',
  `gradeObj` int(11) NOT NULL COMMENT '年级',
  `yearMonth` varchar(20) NOT NULL COMMENT '年月份',
  `areaInfo` varchar(20) NOT NULL COMMENT '地区',
  `entreName` varchar(40) NOT NULL COMMENT '创业名称',
  `companyType` varchar(20) NOT NULL COMMENT '公司性质',
  `personNumber` varchar(20) NOT NULL COMMENT '公司人数',
  PRIMARY KEY  (`id`),
  KEY `specialObj` (`specialObj`),
  KEY `gradeObj` (`gradeObj`),
  CONSTRAINT `t_entrepreneurstudent_ibfk_1` FOREIGN KEY (`specialObj`) REFERENCES `t_specialinfo` (`specialNo`),
  CONSTRAINT `t_entrepreneurstudent_ibfk_2` FOREIGN KEY (`gradeObj`) REFERENCES `t_gradeinfo` (`gradeId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_entrepreneurstudent
-- ----------------------------
INSERT INTO `t_entrepreneurstudent` VALUES ('1', 'STU003', '李卓文', 'ZY001', '1', '2017-10', '四川成都', '美丽家装公司', '私营', '50人');

-- ----------------------------
-- Table structure for `t_getjobstudent`
-- ----------------------------
DROP TABLE IF EXISTS `t_getjobstudent`;
CREATE TABLE `t_getjobstudent` (
  `id` int(11) NOT NULL auto_increment COMMENT '记录id',
  `studentNumber` varchar(30) NOT NULL COMMENT '学号',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `specialObj` varchar(20) NOT NULL COMMENT '专业',
  `gradeObj` int(11) NOT NULL COMMENT '年级',
  `yearMonth` varchar(30) NOT NULL COMMENT '年月份',
  `areaInfo` varchar(20) NOT NULL COMMENT '地区',
  `companyName` varchar(50) NOT NULL COMMENT '就业单位',
  `hangye` varchar(20) NOT NULL COMMENT '行业',
  `positionName` varchar(20) NOT NULL COMMENT '职位名称',
  `companyType` varchar(20) NOT NULL COMMENT '单位性质',
  `conectInfo` varchar(50) NOT NULL COMMENT '单位联系人电话',
  `shouru` varchar(30) NOT NULL COMMENT '收入',
  PRIMARY KEY  (`id`),
  KEY `specialObj` (`specialObj`),
  KEY `gradeObj` (`gradeObj`),
  CONSTRAINT `t_getjobstudent_ibfk_1` FOREIGN KEY (`specialObj`) REFERENCES `t_specialinfo` (`specialNo`),
  CONSTRAINT `t_getjobstudent_ibfk_2` FOREIGN KEY (`gradeObj`) REFERENCES `t_gradeinfo` (`gradeId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_getjobstudent
-- ----------------------------
INSERT INTO `t_getjobstudent` VALUES ('1', 'STU001', '双鱼林', 'ZY001', '1', '2017-09', '四川成都', '天天软件公司', '互联网软件', '软件工程师', '私营', '028-82934934', '5000');
INSERT INTO `t_getjobstudent` VALUES ('2', 'STU008', '王大锤', 'ZY002', '1', '9', '成都', '成都乐筑公司', '装修业', '市场经理', '国营', '028-83984312', '6000');

-- ----------------------------
-- Table structure for `t_gradeinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_gradeinfo`;
CREATE TABLE `t_gradeinfo` (
  `gradeId` int(11) NOT NULL auto_increment COMMENT '记录id',
  `gradeName` varchar(20) NOT NULL COMMENT '年级名称',
  PRIMARY KEY  (`gradeId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_gradeinfo
-- ----------------------------
INSERT INTO `t_gradeinfo` VALUES ('1', '2017级');
INSERT INTO `t_gradeinfo` VALUES ('2', '2018级');

-- ----------------------------
-- Table structure for `t_graduatestudent`
-- ----------------------------
DROP TABLE IF EXISTS `t_graduatestudent`;
CREATE TABLE `t_graduatestudent` (
  `id` int(11) NOT NULL auto_increment COMMENT '记录id',
  `studentNumber` varchar(20) NOT NULL COMMENT '学号',
  `studentName` varchar(20) NOT NULL COMMENT '姓名',
  `specialObj` varchar(20) NOT NULL COMMENT '专业',
  `gradeObj` int(11) NOT NULL COMMENT '年级',
  `yearMonth` varchar(20) NOT NULL COMMENT '年月份',
  `province` varchar(20) NOT NULL COMMENT '省份',
  `schoolName` varchar(30) NOT NULL COMMENT '考研学校',
  `specialName` varchar(20) NOT NULL COMMENT '考研专业',
  PRIMARY KEY  (`id`),
  KEY `specialObj` (`specialObj`),
  KEY `gradeObj` (`gradeObj`),
  CONSTRAINT `t_graduatestudent_ibfk_1` FOREIGN KEY (`specialObj`) REFERENCES `t_specialinfo` (`specialNo`),
  CONSTRAINT `t_graduatestudent_ibfk_2` FOREIGN KEY (`gradeObj`) REFERENCES `t_gradeinfo` (`gradeId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_graduatestudent
-- ----------------------------
INSERT INTO `t_graduatestudent` VALUES ('1', 'STU002', '王忠', 'ZY001', '1', '2017-09', '四川', '电子科技大学', '计算机');

-- ----------------------------
-- Table structure for `t_leaveword`
-- ----------------------------
DROP TABLE IF EXISTS `t_leaveword`;
CREATE TABLE `t_leaveword` (
  `learveId` int(11) NOT NULL auto_increment COMMENT '留言id',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `content` varchar(5000) NOT NULL COMMENT '留言内容',
  `leaveTime` varchar(20) NOT NULL COMMENT '留言时间',
  `studentObj` varchar(30) NOT NULL COMMENT '学生',
  `replyContent` varchar(2000) default NULL COMMENT '回复内容',
  `replyTime` varchar(20) default NULL COMMENT '回复时间',
  PRIMARY KEY  (`learveId`),
  KEY `studentObj` (`studentObj`),
  CONSTRAINT `t_leaveword_ibfk_1` FOREIGN KEY (`studentObj`) REFERENCES `t_student` (`studentNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_leaveword
-- ----------------------------
INSERT INTO `t_leaveword` VALUES ('1', '大家好，找计算机校友', '我是计算机专业的，来找个校友', '2017-11-23 14:15:12', 'STU001', '--', '--');
INSERT INTO `t_leaveword` VALUES ('2', '我是王大锤', '我来参加就业，大家交流下！', '2017-12-05 00:54:38', 'STU008', '你好，大锤好啊！', '2017-12-05 00:55:04');

-- ----------------------------
-- Table structure for `t_otherstudent`
-- ----------------------------
DROP TABLE IF EXISTS `t_otherstudent`;
CREATE TABLE `t_otherstudent` (
  `id` int(11) NOT NULL auto_increment COMMENT '记录id',
  `studentNumber` varchar(20) NOT NULL COMMENT '学号',
  `studentName` varchar(20) NOT NULL COMMENT '姓名',
  `specialObj` varchar(20) NOT NULL COMMENT '专业',
  `gradeObj` int(11) NOT NULL COMMENT '年级',
  `yearMonth` varchar(20) NOT NULL COMMENT '年月份',
  `memo` varchar(2000) default NULL COMMENT '备注',
  PRIMARY KEY  (`id`),
  KEY `specialObj` (`specialObj`),
  KEY `gradeObj` (`gradeObj`),
  CONSTRAINT `t_otherstudent_ibfk_1` FOREIGN KEY (`specialObj`) REFERENCES `t_specialinfo` (`specialNo`),
  CONSTRAINT `t_otherstudent_ibfk_2` FOREIGN KEY (`gradeObj`) REFERENCES `t_gradeinfo` (`gradeId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_otherstudent
-- ----------------------------
INSERT INTO `t_otherstudent` VALUES ('1', 'STU007', '王海波', 'ZY001', '1', '2017-09', '没联系上这个学生');

-- ----------------------------
-- Table structure for `t_servantstudent`
-- ----------------------------
DROP TABLE IF EXISTS `t_servantstudent`;
CREATE TABLE `t_servantstudent` (
  `id` int(11) NOT NULL auto_increment COMMENT '记录id',
  `studentNumber` varchar(20) NOT NULL COMMENT '学号',
  `studentName` varchar(20) NOT NULL COMMENT '姓名',
  `specialObj` varchar(20) NOT NULL COMMENT '专业',
  `gradeObj` int(11) NOT NULL COMMENT '年级',
  `yearMonth` varchar(20) NOT NULL COMMENT '年月份',
  `areaInfo` varchar(20) NOT NULL COMMENT '地区',
  `danwei` varchar(50) NOT NULL COMMENT '就业单位',
  `position` varchar(20) NOT NULL COMMENT '职位',
  `shouru` varchar(20) NOT NULL COMMENT '收入',
  PRIMARY KEY  (`id`),
  KEY `specialObj` (`specialObj`),
  KEY `gradeObj` (`gradeObj`),
  CONSTRAINT `t_servantstudent_ibfk_1` FOREIGN KEY (`specialObj`) REFERENCES `t_specialinfo` (`specialNo`),
  CONSTRAINT `t_servantstudent_ibfk_2` FOREIGN KEY (`gradeObj`) REFERENCES `t_gradeinfo` (`gradeId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_servantstudent
-- ----------------------------
INSERT INTO `t_servantstudent` VALUES ('1', 'STU004', '张蔷薇', 'ZY001', '1', '2017-11', '四川成都', '锦江区人民法院', '前台助理', '4500');

-- ----------------------------
-- Table structure for `t_specialinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_specialinfo`;
CREATE TABLE `t_specialinfo` (
  `specialNo` varchar(20) NOT NULL COMMENT 'specialNo',
  `specialName` varchar(20) NOT NULL COMMENT '专业名称',
  `specialDesc` varchar(2000) NOT NULL COMMENT '专业介绍',
  `bornDate` varchar(20) default NULL COMMENT '开办日期',
  PRIMARY KEY  (`specialNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_specialinfo
-- ----------------------------
INSERT INTO `t_specialinfo` VALUES ('ZY001', '计算机科学与技术', '研究计算机技术', '2017-07-19');
INSERT INTO `t_specialinfo` VALUES ('ZY002', '电子技术', '学习电子信息技术aa', '2017-11-02');

-- ----------------------------
-- Table structure for `t_student`
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `studentNumber` varchar(30) NOT NULL COMMENT 'studentNumber',
  `password` varchar(20) NOT NULL COMMENT '登录密码',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `sex` varchar(4) NOT NULL COMMENT '性别',
  `studentPhoto` varchar(60) NOT NULL COMMENT '学生照片',
  `gradeObj` int(11) NOT NULL COMMENT '年级',
  `specialObj` varchar(20) NOT NULL COMMENT '专业',
  `telephone` varchar(20) NOT NULL COMMENT '联系电话',
  `email` varchar(30) default NULL COMMENT '邮箱',
  `address` varchar(80) NOT NULL COMMENT '地址',
  PRIMARY KEY  (`studentNumber`),
  KEY `gradeObj` (`gradeObj`),
  KEY `specialObj` (`specialObj`),
  CONSTRAINT `t_student_ibfk_1` FOREIGN KEY (`gradeObj`) REFERENCES `t_gradeinfo` (`gradeId`),
  CONSTRAINT `t_student_ibfk_2` FOREIGN KEY (`specialObj`) REFERENCES `t_specialinfo` (`specialNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('STU001', '1234', '双鱼林', '男', 'upload/796f2efe-8f1d-45e6-8bbb-e96ac23a3bf9.jpg', '2', 'ZY001', '13598342934', '2141412@qq.com', '成都红星路');
INSERT INTO `t_student` VALUES ('STU002', '123', '王忠', '男', 'upload/0f9e8444-ac03-4610-8559-8c8a7016dd11.jpg', '1', 'ZY001', '13958303943', 'wangzhong@163.com', '四川南充');
INSERT INTO `t_student` VALUES ('STU003', '123', '李卓文', '女', 'upload/NoImage.jpg', '1', 'ZY001', '15298349343', 'zuowen@163.com', '四川达州');
INSERT INTO `t_student` VALUES ('STU004', '123', '张蔷薇', '女', 'upload/NoImage.jpg', '1', 'ZY001', '13939845982', 'qiangwei@126.com', '四川泸州');
INSERT INTO `t_student` VALUES ('STU005', '123', '李晓晓', '女', 'upload/NoImage.jpg', '1', 'ZY001', '13539843943', 'xiaoxiao@sina.com', '四川达州');
INSERT INTO `t_student` VALUES ('STU006', '123', '张明明', '男', 'upload/bd439a35-9d54-4ce4-ba6c-6145a024b45e.jpg', '1', 'ZY001', '13598349834', 'mingming@163.com', '四川宜宾');
INSERT INTO `t_student` VALUES ('STU007', '123', '王海波', '男', 'upload/NoImage.jpg', '1', 'ZY001', '13598349808', 'haibo@163.com', '四川南充');
INSERT INTO `t_student` VALUES ('STU008', '123', '王大锤', '男', 'upload/ff623495-6fd0-4138-a73e-fcbae3e4c6a5.jpg', '1', 'ZY002', '13958342344', 'dachui@126.com', '四川南充滨江路14号');

-- ----------------------------
-- Table structure for `t_underemployedstudent`
-- ----------------------------
DROP TABLE IF EXISTS `t_underemployedstudent`;
CREATE TABLE `t_underemployedstudent` (
  `id` int(11) NOT NULL auto_increment COMMENT '记录id',
  `studentNumber` varchar(20) NOT NULL COMMENT '学号',
  `studentName` varchar(20) NOT NULL COMMENT '姓名',
  `specialObj` varchar(20) NOT NULL COMMENT '专业',
  `gradeObj` int(11) NOT NULL COMMENT '年级',
  `yearMonth` varchar(20) NOT NULL COMMENT '年月份',
  `reason` varchar(500) NOT NULL COMMENT '原因',
  PRIMARY KEY  (`id`),
  KEY `specialObj` (`specialObj`),
  KEY `gradeObj` (`gradeObj`),
  CONSTRAINT `t_underemployedstudent_ibfk_1` FOREIGN KEY (`specialObj`) REFERENCES `t_specialinfo` (`specialNo`),
  CONSTRAINT `t_underemployedstudent_ibfk_2` FOREIGN KEY (`gradeObj`) REFERENCES `t_gradeinfo` (`gradeId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_underemployedstudent
-- ----------------------------
INSERT INTO `t_underemployedstudent` VALUES ('1', 'STU006', '张明明', 'ZY001', '1', '2017-08', '成绩太差，没找到工作啊啊');
