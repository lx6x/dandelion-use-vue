package com.dandelion.use.server.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author lx6x
 * @since 2023/07/25
 */
@Getter
@Setter
@TableName("sys_menus")
@Schema(name = "SysMenus", description = "菜单")
public class SysMenus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "菜单路径")
    private String path;

    @Schema(description = "父菜单id")
    private Integer parentId;

    @Schema(description = "是否隐藏菜单项")
    private Boolean isHidden;
}
