/*
Navicat MySQL Data Transfer

Source Server         : 47.106.146.227_3306
Source Server Version : 80025
Source Host           : 47.106.146.227:3306
Source Database       : tenant

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-04-26 19:18:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_employee
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee` (
  `femployee_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `ftenant_id` bigint unsigned DEFAULT '0' COMMENT '租户id',
  `femployee_account` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '员工账号',
  `femployee_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '员工姓名',
  `femployee_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `femployee_mobile_country_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '86' COMMENT '手机号码国际区号',
  `femployee_mobile` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `fdirector_employee_id` bigint unsigned DEFAULT '0' COMMENT '上级领导id',
  `femployee_email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '邮箱',
  `flast_login_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最近登录时间',
  `femployee_head_picture_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '头像地址',
  `fis_use` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '是否启用，0否，1是，默认1',
  `fcreate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fcreate_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `fmodify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `fmodify_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  PRIMARY KEY (`femployee_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='员工表';

-- ----------------------------
-- Table structure for t_employee_position
-- ----------------------------
DROP TABLE IF EXISTS `t_employee_position`;
CREATE TABLE `t_employee_position` (
  `fid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'fid',
  `ftenant_id` bigint unsigned DEFAULT '0' COMMENT '租户id',
  `femployee_id` bigint unsigned DEFAULT '0' COMMENT '员工id',
  `femployee_position_id` bigint unsigned DEFAULT '0' COMMENT '岗位id',
  `femployee_position_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '岗位名称',
  `fcreate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fcreate_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `fmodify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `fmodify_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='员工岗位表';

-- ----------------------------
-- Table structure for t_employee_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_employee_role_relation`;
CREATE TABLE `t_employee_role_relation` (
  `fid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'fid',
  `ftenant_id` bigint unsigned DEFAULT '0' COMMENT '租户id',
  `frole_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '角色id',
  `frole_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `femployee_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '员工id',
  `fcreate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fcreate_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `fmodify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `fmodify_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=399 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='员工角色关联表';

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `fmenu_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `fmenu_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
  `fmenu_level` tinyint unsigned DEFAULT '1' COMMENT '菜单级别，1代表1级，2代表2级，',
  `fsort_no` bigint unsigned DEFAULT '0' COMMENT '排序值',
  `fparent_menu_id` bigint unsigned DEFAULT '0' COMMENT '上级菜单id',
  `fis_last_menu` tinyint unsigned DEFAULT '0' COMMENT '是否是末级菜单，0否，1是',
  `fis_use` tinyint unsigned DEFAULT '1' COMMENT '是否启用，0否，1是，默认1',
  `fmenu_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '前端菜单渲染code 唯一不能重复修改不能启用只能新建',
  `fcreate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fcreate_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `fmodify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `fmodify_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  PRIMARY KEY (`fmenu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
-- Table structure for t_menu_permission_selection
-- ----------------------------
DROP TABLE IF EXISTS `t_menu_permission_selection`;
CREATE TABLE `t_menu_permission_selection` (
  `fid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'fid',
  `ftenant_id` bigint unsigned DEFAULT '0' COMMENT '租户id',
  `frole_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '角色id',
  `fmenu_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '末级菜单id',
  `fpermission_id` bigint unsigned DEFAULT '0' COMMENT '权限id',
  `fcreate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fcreate_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `fmodify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `fmodify_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3139 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='菜单权限勾选表';

-- ----------------------------
-- Table structure for t_menu_tenant
-- ----------------------------
DROP TABLE IF EXISTS `t_menu_tenant`;
CREATE TABLE `t_menu_tenant` (
  `fid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fmenu_id` bigint unsigned NOT NULL COMMENT '菜单id',
  `ftenant_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '租户id',
  `fmenu_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
  `fmenu_level` tinyint unsigned DEFAULT '1' COMMENT '菜单级别，1代表1级，2代表2级，',
  `fsort_no` bigint unsigned DEFAULT '0' COMMENT '排序值',
  `fparent_menu_id` bigint unsigned DEFAULT '0' COMMENT '上级菜单id',
  `fis_last_menu` tinyint unsigned DEFAULT '0' COMMENT '是否是末级菜单，0否，1是',
  `fmenu_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '前端菜单渲染code 唯一不能重复修改不能启用只能新建',
  `fis_use` tinyint unsigned DEFAULT '1' COMMENT '是否启用，0否，1是，默认1',
  `fcreate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fcreate_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `fmodify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `fmodify_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='租户类型菜单表';

-- ----------------------------
-- Table structure for t_menu_tenant_type
-- ----------------------------
DROP TABLE IF EXISTS `t_menu_tenant_type`;
CREATE TABLE `t_menu_tenant_type` (
  `fid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `ftenant_type` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '租户类型， 行云为1类，普通客户为2级，合资公司为3类，后面可扩展',
  `fmenu_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '菜单id',
  `fcreate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fcreate_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `fmodify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `fmodify_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='菜单租户类型关联表';

-- ----------------------------
-- Table structure for t_organization
-- ----------------------------
DROP TABLE IF EXISTS `t_organization`;
CREATE TABLE `t_organization` (
  `forganization_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '组织id',
  `ftenant_id` bigint unsigned DEFAULT '0' COMMENT '租户id',
  `ftenant_type` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '租户类型， 行云为1类，普通客户为2级，合资公司为3类，后面可扩展',
  `forganization_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '组织名称',
  `fparent_organization_id` bigint unsigned DEFAULT '0' COMMENT '上级组织id，如果是租户，则为0',
  `forganization_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '组织描述',
  `fis_delete` tinyint unsigned DEFAULT '0' COMMENT '是否删除，0否，1是，默认0',
  `fcreate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fcreate_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `fmodify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `fmodify_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  PRIMARY KEY (`forganization_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10039 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='组织表';

-- ----------------------------
-- Table structure for t_organization_employee_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_organization_employee_relation`;
CREATE TABLE `t_organization_employee_relation` (
  `fid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'fid',
  `ftenant_id` bigint unsigned DEFAULT '0' COMMENT '租户id',
  `forganization_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '组织id',
  `femployee_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '员工id',
  `fcreate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fcreate_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `fmodify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `fmodify_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='组织员工关联表';

-- ----------------------------
-- Table structure for t_organization_leader
-- ----------------------------
DROP TABLE IF EXISTS `t_organization_leader`;
CREATE TABLE `t_organization_leader` (
  `fid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'fid',
  `ftenant_id` bigint unsigned DEFAULT '0' COMMENT '租户id',
  `femployee_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '负责人id',
  `femployee_account` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '负责人账号',
  `femployee_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '负责人姓名',
  `forganization_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '组织id',
  `forganization_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '组织名称',
  `fparent_organization_id` bigint unsigned DEFAULT '0' COMMENT '上级组织id，如果是租户，则为0',
  `femployee_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `femployee_mobile_country_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '86' COMMENT '手机号码国际区号',
  `femployee_mobile` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `fdirector_id` bigint unsigned DEFAULT '0' COMMENT '上级领导id',
  `femployee_email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '邮箱',
  `flast_login_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最近登录时间',
  `femployee_head_picture_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '头像地址',
  `fis_use` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '是否启用，0否，1是，默认1',
  `fcreate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fcreate_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `fmodify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `fmodify_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='组织负责人表';

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `fpermission_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `fmenu_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '末级菜单id',
  `freq_type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '界面操作类型',
  `fpermission_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限名称',
  `freq_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '请求方式',
  `fpermission_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '权限路径',
  `fpermission_type` tinyint unsigned DEFAULT '1' COMMENT '权限类型，1为功能权限，2为字段权限，',
  `fpermission_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '权限描述',
  `fpermission_icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '权限icon',
  `fis_delete` tinyint unsigned DEFAULT '0' COMMENT '是否删除，0否，1是，默认0',
  `fcreate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fcreate_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `fmodify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `fmodify_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  PRIMARY KEY (`fpermission_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `frole_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `ftenant_id` bigint unsigned DEFAULT '0' COMMENT '租户id',
  `frole_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `frole_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '角色描述',
  `fis_delete` tinyint unsigned DEFAULT '0' COMMENT '是否删除，0否，1是，默认0',
  `fcreate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fcreate_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `fmodify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `fmodify_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  PRIMARY KEY (`frole_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Table structure for t_role_menu_selection
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu_selection`;
CREATE TABLE `t_role_menu_selection` (
  `fid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'fid',
  `ftenant_id` bigint unsigned DEFAULT '0' COMMENT '租户id',
  `frole_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '角色id',
  `fmenu_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '菜单id',
  `fparent_menu_id` bigint unsigned DEFAULT '0' COMMENT '上级菜单id',
  `fis_last_menu` tinyint unsigned DEFAULT '0' COMMENT '是否是末级菜单，0否，1是',
  `fcreate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fcreate_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `fmodify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `fmodify_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=786 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色菜单勾选表';

-- ----------------------------
-- Table structure for t_tenant
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant`;
CREATE TABLE `t_tenant` (
  `ftenant_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '租户id',
  `ftenant_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户名称',
  `ftenant_type` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '租户类型， 超级管理员为0，行云为1类，普通客户为2级，合资公司为3类，后面可扩展',
  `forganization_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '组织描述',
  `fcreate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fcreate_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `fmodify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `fmodify_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  PRIMARY KEY (`ftenant_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1009 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='租户表';

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `branch_id` bigint NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
SET FOREIGN_KEY_CHECKS=1;
 