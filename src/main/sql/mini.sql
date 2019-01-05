-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: mini
-- ------------------------------------------------------
-- Server version	5.7.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `firm_inf`
--

DROP TABLE IF EXISTS `firm_inf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `firm_inf` (
  `ID` varchar(50) COLLATE utf8_bin NOT NULL,
  `comecode` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `comcname` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `comcname1` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `Firmcname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CreditCode` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `OrganizerCode` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `Industry` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `Legalname` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `BusinessScope` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `FirmAddress` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ValidStatus` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `InsertTime` datetime DEFAULT NULL,
  `UpdateTime` datetime DEFAULT NULL,
  `TCOL1` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `TCOL2` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `firm_inf`
--

LOCK TABLES `firm_inf` WRITE;
/*!40000 ALTER TABLE `firm_inf` DISABLE KEYS */;
/*!40000 ALTER TABLE `firm_inf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ls_comcode`
--

DROP TABLE IF EXISTS `ls_comcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ls_comcode` (
  `comcode` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `comcname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `comshortname` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `insurername` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `comlevel` varchar(10) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ls_comcode`
--

LOCK TABLES `ls_comcode` WRITE;
/*!40000 ALTER TABLE `ls_comcode` DISABLE KEYS */;
/*!40000 ALTER TABLE `ls_comcode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `series` varchar(64) COLLATE utf8_bin NOT NULL,
  `token` varchar(64) COLLATE utf8_bin NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res_role`
--

DROP TABLE IF EXISTS `res_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `res_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '资源ID',
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色ID',
  `res_status` varchar(3) DEFAULT '1' COMMENT '状态 1：有效， 0：无效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `res_role_unique` (`res_id`,`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='资源角色关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res_role`
--

LOCK TABLES `res_role` WRITE;
/*!40000 ALTER TABLE `res_role` DISABLE KEYS */;
INSERT INTO `res_role` VALUES (1,1,1,'0'),(2,2,1,'1'),(3,2,2,'1'),(4,3,3,'1'),(5,44,1,'1'),(15,43,2,'1'),(16,43,3,'1'),(21,48,2,'1'),(22,48,3,'1'),(25,47,2,'1'),(26,47,3,'1');
/*!40000 ALTER TABLE `res_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_resource`
--

DROP TABLE IF EXISTS `sys_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `res_fid` bigint(20) DEFAULT '0' COMMENT '资源父ID',
  `res_name` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `res_type` varchar(50) DEFAULT NULL COMMENT '资源类型',
  `res_url` varchar(200) DEFAULT NULL COMMENT '资源访问URL',
  `res_status` int(1) DEFAULT '1' COMMENT '资源状态: 1 有效, 0 无效,-1 删除',
  `res_desc` varchar(200) DEFAULT NULL COMMENT '资源描述',
  `res_create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `res_delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COMMENT='访问资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_resource`
--

LOCK TABLES `sys_resource` WRITE;
/*!40000 ALTER TABLE `sys_resource` DISABLE KEYS */;
INSERT INTO `sys_resource` VALUES (1,0,'角色','URL','/role/**',1,'角色','2018-07-16 09:21:30',NULL),(2,0,'资源','URL','/res/**',1,'资源','2018-07-16 09:21:30',NULL),(3,0,'用户','URL','/user/**',1,'用户','2018-07-16 09:21:30',NULL),(10,1,'系统模块2','URL','/user/**',0,'系统模块','2018-12-28 10:38:12',NULL),(11,1,'测试1','URL','、、sad',0,'ss说的','2018-12-28 14:26:34',NULL),(12,2,'资源用户分配','URL','res/assign',0,'资源用户分配','2018-12-28 14:27:42',NULL),(13,2,'说的','URL','说的',0,'说的','2018-12-28 14:29:53',NULL),(41,0,'224','URL','22',1,'2323','2019-01-02 16:04:57',NULL),(42,3,'sd搜嗯嗯嗯','URL','sd',0,'sd','2019-01-02 16:05:29',NULL),(43,2,'ddd','URL','d',0,'d','2019-01-02 16:34:24',NULL),(44,0,'嗯嗯','URL','/user/**',0,'问','2019-01-03 11:29:22',NULL),(45,41,'44','URL','4',0,'4','2019-01-03 13:54:25',NULL),(46,44,'人1','URL','的',0,'的','2019-01-03 13:54:49',NULL),(47,46,'是2','URL','是',0,'是','2019-01-03 13:56:42',NULL),(48,46,'42当度','URL','4',0,'4','2019-01-03 13:57:11',NULL),(49,46,'的1','URL','是',0,'是','2019-01-03 13:58:56',NULL),(50,0,'是的','URL','是d的',0,'是','2019-01-03 17:04:00',NULL),(51,49,'3级资源菜单','URL','是当度第',0,'3说','2019-01-04 09:58:23',NULL),(52,50,'鼠标','URL','是',0,'是','2019-01-04 11:33:00',NULL);
/*!40000 ALTER TABLE `sys_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(200) DEFAULT NULL COMMENT '角色描述',
  `role_status` int(1) DEFAULT '1' COMMENT '角色状态:1 有效, 0 无效',
  `role_create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '角色创建时间',
  `role_delete_time` datetime DEFAULT NULL COMMENT '角色删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'ROLE_ADMIN','管理员角色',1,'2018-07-16 09:21:30',NULL),(2,'ROLE_USER','用户角色',1,'2018-07-16 09:21:30',NULL),(3,'ROLE_DBA','数据库管理员角色',1,'2018-07-16 09:21:30',NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `user_passwd` varchar(500) DEFAULT NULL COMMENT '用户密码',
  `user_status` int(1) DEFAULT '1' COMMENT '用户状态: 1 有效 , 0 无效',
  `user_desc` varchar(200) DEFAULT NULL COMMENT '用户描述',
  `user_create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `user_delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','admin',1,'管理员','2018-07-16 09:21:30',NULL),(2,'dba','dba',1,'数据库管理员','2018-07-16 09:21:30',NULL),(3,'user','$2a$10$pJKdP0V6ak6tOx6cL2rgJOdQgcI3.8mCVKDW/RNU04HXhvClpHYfq',1,'用户','2018-07-16 09:21:30',NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,3),(2,1,1),(3,2,2),(4,3,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mini'
--

--
-- Dumping routines for database 'mini'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-05 22:05:05
