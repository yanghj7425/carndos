DROP TABLE IF EXISTS `resc`;
CREATE TABLE `resc` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `res_type` varchar(50) DEFAULT NULL,
  `res_string` varchar(200) DEFAULT NULL,
  `descn` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resc
-- ----------------------------
INSERT INTO `resc` VALUES ('1', '', 'URL', '/adminPage.jsp', '管理员页面');
INSERT INTO `resc` VALUES ('2', '', 'URL', '/index.jsp', '');
INSERT INTO `resc` VALUES ('3', null, 'URL', '/test.jsp', '测试页面');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20)  PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `descn` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_ADMIN', '管理员角色');
INSERT INTO `role` VALUES ('2', 'ROLE_USER', '用户角色');
INSERT INTO `role` VALUES ('3', 'ROLE_TEST', '测试角色');

-- ----------------------------
-- Table structure for resc_role
-- ----------------------------
DROP TABLE IF EXISTS `resc_role`;
CREATE TABLE `resc_role` (
  `resc_id` bigint(20) NOT NULL DEFAULT '0',
  `role_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`resc_id`,`role_id`),
  KEY `fk_resc_role_role` (`role_id`),
  CONSTRAINT `fk_resc_role_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `fk_resc_role_resc` FOREIGN KEY (`resc_id`) REFERENCES `resc` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resc_role
-- ----------------------------
INSERT INTO `resc_role` VALUES ('1', '1');
INSERT INTO `resc_role` VALUES ('2', '1');
INSERT INTO `resc_role` VALUES ('2', '2');
INSERT INTO `resc_role` VALUES ('3', '3');

-- ----------------------------
-- Table structure for t_c3p0
-- ----------------------------
DROP TABLE IF EXISTS `t_c3p0`;
CREATE TABLE `t_c3p0` (
  `a` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_c3p0
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `descn` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '1', '管理员');
INSERT INTO `user` VALUES ('2', 'user', 'user', '1', '用户');
INSERT INTO `user` VALUES ('3', 'test', 'test', '1', '测试');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `role_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_user_role_role` (`role_id`),
  CONSTRAINT `fk_user_role_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `fk_user_role_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('1', '2');
INSERT INTO `user_role` VALUES ('2', '2');
INSERT INTO `user_role` VALUES ('3', '3');
