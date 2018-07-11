DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  `res_name` VARCHAR(50) DEFAULT NULL COMMENT '资源名称',
  `res_type` VARCHAR(50) DEFAULT NULL COMMENT '资源类型',
  `res_url` VARCHAR(200) DEFAULT NULL COMMENT '资源访问URL',
  `res_status` INT(1) DEFAULT '1' COMMENT '资源状态: 1 有效, 0 无效',
  `res_desc` VARCHAR(200) DEFAULT NULL COMMENT '资源描述',
  `res_create_time` DATETIME DEFAULT NOW() COMMENT '创建时间',
  `res_delete_time` DATETIME DEFAULT NULL COMMENT '删除时间'
)  ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT '访问资源表';

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` (res_name, res_type, res_url, res_desc)VALUES ('','URL', '/adminPage.jsp', '管理员页面');
INSERT INTO `resource` (res_name, res_type, res_url, res_desc) VALUES ( '', 'URL', '/index.jsp', '');
INSERT INTO `resource` (res_name, res_type, res_url, res_desc)VALUES ( '', 'URL', '/home.jsp', '测试页面');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  `role_name` VARCHAR(50) DEFAULT NULL COMMENT '角色名称',
  `role_desc` VARCHAR(200) DEFAULT NULL COMMENT '角色描述',
  `role_status` INT(1) DEFAULT 1 COMMENT '角色状态:1 有效, 0 无效',
  `role_create_time` DATETIME DEFAULT NOW() COMMENT '角色创建时间',
  `role_delete_time` DATETIME DEFAULT NULL COMMENT '角色删除时间'
)  ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT '角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` (role_name, role_desc) VALUES ('ROLE_ADMIN', '管理员角色');
INSERT INTO `role` (role_name, role_desc) VALUES ('ROLE_USER', '用户角色');
INSERT INTO `role` (role_name, role_desc)VALUES ('ROLE_DBA', '数据库管理员角色');

-- ----------------------------
-- Table structure for res_role
-- ----------------------------
DROP TABLE IF EXISTS `res_role`;
CREATE TABLE `res_role` (
  `id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  `res_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '资源ID',
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '资源角色关系表';

-- ----------------------------
-- Records of res_role
-- ----------------------------
INSERT INTO `res_role` (res_id, role_id) VALUES ('1', '1');
INSERT INTO `res_role` (res_id, role_id) VALUES ('2', '1');
INSERT INTO `res_role` (res_id, role_id) VALUES ('2', '2');
INSERT INTO `res_role` (res_id, role_id) VALUES ('3', '3');

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
  `id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  `user_name` VARCHAR(50) DEFAULT NULL COMMENT '用户名',
  `user_passwd` VARCHAR(50) DEFAULT NULL COMMENT '用户密码',
  `user_status` INT(1) DEFAULT 1 COMMENT '用户状态: 1 有效 , 0 无效',
  `user_desc` VARCHAR(200) DEFAULT NULL COMMENT '用户描述',
  `user_create_time` DATETIME DEFAULT NOW() COMMENT '添加时间',
  `user_delete_time` DATETIME DEFAULT NULL COMMENT '删除时间'
)  ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT '用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` (user_name, user_passwd, user_status, user_desc)VALUES ('admin', 'admin', '1', '管理员');
INSERT INTO `user` (user_name, user_passwd, user_status, user_desc) VALUES ( 'user', 'user', '1', '用户');
INSERT INTO `user` (user_name, user_passwd, user_status, user_desc) VALUES ('dba', 'dba', '1', '数据库管理员');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  `user_id` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `role_id` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '角色ID'
)  ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT '用户角色关系表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` (user_id, role_id) VALUES ('1', '1');
INSERT INTO `user_role` (user_id, role_id) VALUES ('1', '2');
INSERT INTO `user_role` (user_id, role_id) VALUES ('2', '2');
INSERT INTO `user_role` (user_id, role_id) VALUES ('3', '3');
