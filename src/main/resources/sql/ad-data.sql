/*
Navicat MySQL Data Transfer

Source Server         : ubuntu
Source Server Version : 50730
Source Host           : 172.16.188.99:3306
Source Database       : ad-data

Target Server Type    : MYSQL
Target Server Version : 50730
File Encoding         : 65001

Date: 2020-08-18 09:48:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ad_creative`
-- ----------------------------
DROP TABLE IF EXISTS `ad_creative`;
CREATE TABLE `ad_creative` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(48) NOT NULL COMMENT '创意名称',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '物料类型(图片, 视频)',
  `material_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '物料子类型(图片: bmp, jpg 等等)',
  `height` int(10) NOT NULL DEFAULT '0' COMMENT '高度',
  `width` int(10) NOT NULL DEFAULT '0' COMMENT '宽度',
  `size` bigint(20) NOT NULL DEFAULT '0' COMMENT '物料大小, 单位是 KB',
  `duration` int(10) NOT NULL DEFAULT '0' COMMENT '持续时长, 只有视频才不为 0',
  `audit_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '审核状态',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '标记当前记录所属用户',
  `url` varchar(256) NOT NULL COMMENT '物料地址',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='创意表';

-- ----------------------------
-- Records of ad_creative
-- ----------------------------

-- ----------------------------
-- Table structure for `ad_plan`
-- ----------------------------
DROP TABLE IF EXISTS `ad_plan`;
CREATE TABLE `ad_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '标记当前记录所属用户',
  `plan_name` varchar(48) NOT NULL COMMENT '推广计划名称',
  `plan_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '推广计划状态',
  `start_date` datetime NOT NULL COMMENT '推广计划开始时间；',
  `end_date` datetime NOT NULL COMMENT '推广计划结束时间；',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推广计划表';

-- ----------------------------
-- Records of ad_plan
-- ----------------------------

-- ----------------------------
-- Table structure for `ad_unit`
-- ----------------------------
DROP TABLE IF EXISTS `ad_unit`;
CREATE TABLE `ad_unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `plan_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '关联推广计划 id',
  `unit_name` varchar(48) NOT NULL COMMENT '推广单元名称',
  `unit_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '推广单元状态',
  `position_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '广告位类型(开屏, 贴片, 中贴, 暂停帖, 后贴)',
  `budget` bigint(20) NOT NULL COMMENT '预算',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推广单元表';

-- ----------------------------
-- Records of ad_unit
-- ----------------------------

-- ----------------------------
-- Table structure for `ad_unit_district`
-- ----------------------------
DROP TABLE IF EXISTS `ad_unit_district`;
CREATE TABLE `ad_unit_district` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `unit_id` bigint(20) NOT NULL COMMENT '推广单元 id',
  `province` varchar(30) NOT NULL COMMENT '省',
  `city` varchar(30) NOT NULL COMMENT '市',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推广单元地域 Feature';

-- ----------------------------
-- Records of ad_unit_district
-- ----------------------------

-- ----------------------------
-- Table structure for `ad_unit_it`
-- ----------------------------
DROP TABLE IF EXISTS `ad_unit_it`;
CREATE TABLE `ad_unit_it` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `unit_id` bigint(20) NOT NULL COMMENT '推广单元 id',
  `it_tag` varchar(30) NOT NULL COMMENT '兴趣标签',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推广单元兴趣 Feature';

-- ----------------------------
-- Records of ad_unit_it
-- ----------------------------

-- ----------------------------
-- Table structure for `ad_unit_keyword`
-- ----------------------------
DROP TABLE IF EXISTS `ad_unit_keyword`;
CREATE TABLE `ad_unit_keyword` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `unit_id` bigint(20) NOT NULL COMMENT '推广单元 id',
  `keyword` varchar(30) NOT NULL COMMENT '关键词',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推广单元关键词 Feature';

-- ----------------------------
-- Records of ad_unit_keyword
-- ----------------------------

-- ----------------------------
-- Table structure for `ad_user`
-- ----------------------------
DROP TABLE IF EXISTS `ad_user`;
CREATE TABLE `ad_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `username` varchar(128) NOT NULL DEFAULT '' COMMENT '用户名',
  `token` varchar(256) NOT NULL DEFAULT '' COMMENT '给用户生成的 token',
  `user_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户状态',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of ad_user
-- ----------------------------

-- ----------------------------
-- Table structure for `creative_unit`
-- ----------------------------
DROP TABLE IF EXISTS `creative_unit`;
CREATE TABLE `creative_unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creative_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创意 id',
  `unit_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '推广单元 id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='创意和推广单元关联表';

-- ----------------------------
-- Records of creative_unit
-- ----------------------------

-- ----------------------------
-- Table structure for `demo_user`
-- ----------------------------
DROP TABLE IF EXISTS `demo_user`;
CREATE TABLE `demo_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of demo_user
-- ----------------------------
