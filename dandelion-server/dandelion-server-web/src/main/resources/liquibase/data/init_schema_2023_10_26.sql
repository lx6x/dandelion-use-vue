CREATE TABLE `sys_user`
(
    `id`          int                                     NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_name`   varchar(20) COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户名/登录账号',
    `password`    varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
    `nick_name`   varchar(20) COLLATE utf8mb4_general_ci                   DEFAULT NULL COMMENT '昵称',
    `sex`         char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '性别 0未知性别 1男性 2女性 9未说明性别',
    `create_time` datetime                                NOT NULL         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户信息';