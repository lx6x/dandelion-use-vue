CREATE TABLE `sys_menus`
(
    `id`        bigint                                                           NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`      varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜单名称',
    `path`      varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜单路径',
    `parent_id` int        DEFAULT NULL COMMENT '父菜单id (0默认一级菜单)',
    `is_hidden` tinyint(1) DEFAULT '0' COMMENT '是否隐藏菜单项 (0-false 1-true)',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8mb3 COMMENT ='菜单信息';