package com.dandelion.use.server.web.controller.system;

import com.dandelion.use.server.common.result.R;
import com.dandelion.use.server.service.sys.domain.SysMenus;
import com.dandelion.use.server.service.sys.service.ISysMenusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author lx6x
 * @date 2023/7/25
 */
@Tag(name = "菜单")
@RestController
@RequestMapping("/sys/menus")
public class SysMenusController {

    @Resource
    private ISysMenusService sysMenusService;

    @Operation(description = "获取菜单列表")
    @GetMapping("/getList")
    public R<List<SysMenus>> getList() {
        return R.success(sysMenusService.list());
    }

}
