CREATE TABLE `sys_user`
(
    `id`          bigint                                  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_name`   varchar(20) COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户名/登录账号',
    `password`    varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
    `nick_name`   varchar(20) COLLATE utf8mb4_general_ci                   DEFAULT NULL COMMENT '昵称',
    `sex`         char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '性别 0未知性别 1男性 2女性 9未说明性别',
    `create_time` datetime                                NOT NULL         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                 DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户信息';

INSERT INTO `dandelion`.`sys_user` (`id`, `user_name`, `password`, `nick_name`, `sex`, `create_time`, `update_time`)
VALUES (1, 'admin', '$2a$10$pTfbL4c0zKazD7iNjmzUwepFt8Jl6Y/JEZELFo/3sM.kV9QZ94Eqm', '超级管理员', '1',
        '2023-10-26 09:28:43', '2023-10-27 07:08:05');
INSERT INTO `dandelion`.`sys_user` (`id`, `user_name`, `password`, `nick_name`, `sex`, `create_time`, `update_time`)
VALUES (2, 'zhangsan', '$2a$10$pTfbL4c0zKazD7iNjmzUwepFt8Jl6Y/JEZELFo/3sM.kV9QZ94Eqm', '张三', '1',
        '2023-10-26 09:28:43', '2023-10-27 07:08:06');
