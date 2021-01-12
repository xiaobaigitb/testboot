--首先是 帐号表 sys_user  ，里面存放的就是 登录系统的帐号和密码；
--角色表 sys_role ，里面存放的就是 角色信息；
--权限表 sys_permissions ，里面存放的就是 权限信息；
--中间表  user_role 和 role_per，分别是存放 哪个用户分配的哪个角色，多对多； 哪个角色对应哪个权限，多对多；
--数据库方面的设计就到此，那么在开始代码实现前，我们还继续一下数据库方面的东西，就是数据模拟；

CREATE TABLE `sys_user`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id 作为表主键 用于关联',
  `userName` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户登录帐号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户登录密码',
  `userRemarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注，预留字段',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20002 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `sys_role`  (
  `roleId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 角色id 作为表主键 用于关联',
  `roleName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `roleRemarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注，预留字段',
  PRIMARY KEY (`roleId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `sys_permissions`  (
  `perId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限表id 作为表主键 用于关联',
  `permissionsName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `perRemarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注，预留字段',
  PRIMARY KEY (`perId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表主键id',
  `userId` int(11) NULL DEFAULT NULL COMMENT '帐号表的主键id',
  `roleId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色表的主键id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `role_per`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表主键id',
  `roleId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色表的主键id',
  `perId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限表的主键id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

--测试数据
INSERT INTO `test`.`sys_user`(`userId`, `userName`, `password`, `userRemarks`) VALUES (10001, 'adminHong', '202cb962ac59075b964b07152d234b70', '小红');
INSERT INTO `test`.`sys_user`(`userId`, `userName`, `password`, `userRemarks`) VALUES (20001, 'jc', 'e165421110ba03099a1c0393373c5b43', 'JC');

INSERT INTO `test`.`sys_role`(`roleId`, `roleName`, `roleRemarks`) VALUES ('100', 'admin', '系统管理员');
INSERT INTO `test`.`sys_role`(`roleId`, `roleName`, `roleRemarks`) VALUES ('200', 'common', '普通用户');

INSERT INTO `test`.`sys_permissions`(`perId`, `permissionsName`, `perRemarks`) VALUES ('M01', 'resetPassword', '重置密码');
INSERT INTO `test`.`sys_permissions`(`perId`, `permissionsName`, `perRemarks`) VALUES ('M02', 'querySystemLog', '查看系统日志');
INSERT INTO `test`.`sys_permissions`(`perId`, `permissionsName`, `perRemarks`) VALUES ('M03', 'exportUserInfo', '导出用户信息');
INSERT INTO `test`.`sys_permissions`(`perId`, `permissionsName`, `perRemarks`) VALUES ('M204', 'queryMyUserInfo', '查看个人信息');

INSERT INTO `test`.`role_per`(`id`, `roleId`, `perId`) VALUES (1, '100', 'M01');
INSERT INTO `test`.`role_per`(`id`, `roleId`, `perId`) VALUES (2, '100', 'M02');
INSERT INTO `test`.`role_per`(`id`, `roleId`, `perId`) VALUES (3, '100', 'M03');
INSERT INTO `test`.`role_per`(`id`, `roleId`, `perId`) VALUES (4, '200', 'M204');
INSERT INTO `test`.`role_per`(`id`, `roleId`, `perId`) VALUES (5, '100', 'M204');

INSERT INTO `test`.`user_role`(`id`, `userId`, `roleId`) VALUES (1, 10001, '100');
INSERT INTO `test`.`user_role`(`id`, `userId`, `roleId`) VALUES (2, 20001, '200');