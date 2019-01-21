DROP DATABASE IF EXISTS template;
CREATE DATABASE IF NOT EXISTS template DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE template;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `account` varchar(45) DEFAULT NULL COMMENT '账号',
  `password` varchar(45) DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) DEFAULT NULL COMMENT 'md5密码盐',
  `name` varchar(45) DEFAULT NULL COMMENT '名字',
  `sex` int(11) DEFAULT 0 COMMENT '性别（1：男 2：女）',
  `email` varchar(45) DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话',
  `roleid` int(11) DEFAULT NULL COMMENT '角色id',
  `parentid` varchar(255) DEFAULT NULL COMMENT '上级用户id',
  `deptid` int(11) DEFAULT 0 COMMENT '部门id',
  `status` int(11) DEFAULT 0 COMMENT '状态(1：启用  2：冻结  3：删除）',
  `deleted`  int(1) DEFAULT 0 COMMENT '是否被删除',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT 0 COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of user  pw 1234.guo.56
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'http://news.cjn.cn/gjxw/201606/W020160622541157378945.jpg', 'admin',     '0b9e162d13f24aa906c930a949e319ae', '8pgby', '猫哥', '1', 'aemail@163.com', '13421754955', 1, '0','0', '1',  0, '2018-12-17 08:49:53', null);
INSERT INTO `user` VALUES ('2', 'http://news.cjn.cn/gjxw/201606/W020160622541157378945.jpg', 'proxy',     '0b9e162d13f24aa906c930a949e319ae', '8pgby', '狗兄', '1', 'bemail@126.com', '13421754955', 3, '0','0', '1',  0, '2018-12-17 08:49:53', null);
INSERT INTO `user` VALUES ('3', 'http://news.cjn.cn/gjxw/201606/W020160622541157378945.jpg', 'advert',    '0b9e162d13f24aa906c930a949e319ae', '8pgby', '龟叔', '1', 'cemail@126.com', '13421754955', 2, '0','0', '1',  0, '2018-12-17 08:49:53', null);

-- ------------------------`----
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `roleid` int(11) DEFAULT NULL COMMENT '序号',
  `pid` int(11) DEFAULT NULL COMMENT '父角色id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `deptid` int(11) DEFAULT NULL COMMENT '部门名称',
  `permission` varchar(255) DEFAULT NULL COMMENT '用户权限',
  `tips` varchar(255) DEFAULT NULL COMMENT '提示',
  `version` int(11) DEFAULT 0 COMMENT '保留字段(暂时没用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';


-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', 1, '0', '管理员', '11', 'admin',  'admin',   null);
INSERT INTO `user_role` VALUES ('2', 2, '1', '广告员', '22', 'advert', 'advert',  null);
INSERT INTO `user_role` VALUES ('3', 3, '1', '代理员', '33', 'proxy',  'proxy',   null);

-- ----------------------------
-- 兼容emoji表情存储
-- ----------------------------

-- 数据库 --
ALTER DATABASE template CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 表 --
ALTER TABLE user CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 字段 --
ALTER TABLE user CHANGE name name VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;



SET FOREIGN_KEY_CHECKS = 1;