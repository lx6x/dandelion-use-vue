<template>
  <el-container class="layout-container">
    <el-header>Header</el-header>
    <el-container>
      <el-aside width="200px">
        <Menu/>
      </el-aside>
      <el-main class="main">
        <router-view/>
      </el-main>
    </el-container>
    <el-footer class="footer">
      京ICP备2021004282号-1
    </el-footer>
  </el-container>
</template>

<script lang="ts" setup>
import Menu from "~/layouts/components/Menu.vue";
</script>

<style lang="scss">
.layout-container {
  height: 100vh;
}

.layout-container .el-header {
  //position: relative;
  color: #898080;
  font-size: 15px;
  text-align: center;
}

.layout-container .el-aside {
  color: var(--el-text-color-primary);
}

.layout-container .el-menu {
  border-right: none;
}

.layout-container .el-main {
  padding: 0;
}

.layout-container .footer {
  font-size: 15px;
  text-align: center;
  color: #898080
}
</style>

