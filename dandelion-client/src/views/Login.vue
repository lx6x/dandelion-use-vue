<template>

  <el-container class="layout-container">
    <el-main class="main">
      <div class="login">
        <el-form class="login-form">
          <h1>Login</h1>
          <el-input>账号</el-input>
          <el-input>密码</el-input>
          <el-button type="primary" size="large" @click="login">登录</el-button>
        </el-form>
      </div>
    </el-main>
    <el-footer class="footer">
      京ICP备2021004282号-1
    </el-footer>
  </el-container>
</template>

<script setup lang="ts">
import {ElMessage} from "element-plus";
import {useAuthStore} from "~/stores/auth.ts";
import router from "~/router";
// import axios from "axios";

const authStore = useAuthStore();

const login = () => {
  authStore.login();
  const authenticated = authStore.isAuthenticated;
  if (authenticated) {
    // menuList();
    ElMessage({
      message: '登录了',
      type: 'success'
    });
    router.push({name: 'home'});
  } else {
    ElMessage({
      message: '没登录',
      type: 'error'
    })
  }
}

/*function menuList(): any {
  axios.get("/api/sys/menus/getListLevel").then(res => {
    const data = res.data.data;
    addRouter(data)
  })
}*/

/*const addRouter = (data: any) => {
  data.forEach((route: any) => {
    console.log(route)
  });
}*/

</script>

<style lang="scss">
.layout-container {
  height: 100vh;
}


.layout-container .el-aside {
  color: var(--el-text-color-primary);
  //background: var(--el-color-primary-light-8);
}

.layout-container .el-menu {
  border-right: none;
}

.layout-container .el-main .login {
  position: relative;
  top: 30%;
  text-align: center;
  overflow: hidden;

  .login-form {
    min-width: 320px;
    max-width: 520px;
    width: auto;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }
}

.layout-container .footer {
  font-size: 15px;
  text-align: center;
  color: #898080
}

</style>