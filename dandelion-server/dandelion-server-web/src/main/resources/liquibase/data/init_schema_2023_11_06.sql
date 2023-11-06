CREATE TABLE `sys_role`
(
    `id`        bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_name` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色信息';

INSERT INTO `sys_role` (`id`, `role_name`)
VALUES (1, 'admin');
INSERT INTO `sys_role` (`id`, `role_name`)
VALUES (2, 'user1');
INSERT INTO `sys_role` (`id`, `role_name`)
VALUES (3, 'user2');


CREATE TABLE `sys_user_role`
(
    `id`      bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id` bigint NOT NULL COMMENT '用户id',
    `role_id` bigint NOT NULL COMMENT '角色id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户角色关联信息';

INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (1, 1, 1);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (2, 2, 2);


# CREATE TABLE `sys_auth`
# (
#     `id`        bigint                                 NOT NULL AUTO_INCREMENT COMMENT '主键',
#     `auth_name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
#     PRIMARY KEY (`id`)
# ) ENGINE = InnoDB
#   DEFAULT CHARSET = utf8mb4
#   COLLATE = utf8mb4_general_ci COMMENT ='权限信息';
#
# INSERT INTO `sys_auth` (`id`, `auth_name`) VALUES (1, 'read');
# INSERT INTO `sys_auth` (`id`, `auth_name`) VALUES (2, 'write');
#
#
# CREATE TABLE `sys_role_auth`
# (
#     `id`      bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
#     `role_id` bigint NOT NULL COMMENT '角色id',
#     `auth_id` bigint NOT NULL COMMENT '权限id',
#     PRIMARY KEY (`id`)
# ) ENGINE = InnoDB
#   DEFAULT CHARSET = utf8mb4
#   COLLATE = utf8mb4_general_ci COMMENT ='角色权限关联信息';
#
# INSERT INTO `sys_role_auth` (`id`, `role_id`, `auth_id`) VALUES (1, 1, 1);
# INSERT INTO `sys_role_auth` (`id`, `role_id`, `auth_id`) VALUES (2, 1, 2);
# INSERT INTO `sys_role_auth` (`id`, `role_id`, `auth_id`) VALUES (3, 2, 1);
# INSERT INTO `sys_role_auth` (`id`, `role_id`, `auth_id`) VALUES (4, 3, 2);
