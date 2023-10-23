<template>
  <el-menu active-text-color="#000" class="el-menu-vertical-demo" default-active="1"
           text-color="#000" @open="handleOpen" @close="handleClose" @select="handleSelect">
    <el-sub-menu index="1">
      <template #title>
        <el-icon>
          <Menu/>
        </el-icon>
        <span>Home</span>
      </template>
      <el-menu-item index="/home">
        home
      </el-menu-item>
    </el-sub-menu>
    <el-sub-menu index="2">
      <template #title>
        <el-icon>
          <Menu/>
        </el-icon>
        <span>Page</span>
      </template>
<!--      <el-menu-item v-for="route in filteredRoutes" :index="route.path">
        {{ route.meta.title }}
      </el-menu-item>-->
      <el-menu-item index="/page1">
        page1
      </el-menu-item>
      <el-menu-item index="/page2">
        page2
      </el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<script lang="ts" setup>
import {Menu} from '@element-plus/icons-vue'
import router from '~/router';

const handleOpen = (key: string, keyPath: string[]) => {
  console.log("open", key, keyPath)
};
const handleClose = (key: string, keyPath: string[]) => {
  console.log("close", key, keyPath)
};
const handleSelect = (index: string) => {
  router.push(index)
};

// 获取当前的路由配置
// const routes = router.getRoutes();

// 过滤需要显示在菜单中的路由项
// const filteredRoutes = routes.filter((route) => {
  // 判断是否需要显示该路由项，可以根据 meta 属性来判断
  // 例如，如果路由项的 meta 属性中有 hidden: true，则不显示该路由项
  // return !route.meta.hidden;
// });

</script>