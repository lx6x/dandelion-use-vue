<template>
  <el-container class="layout-container">
    <el-main class="main">
      <div class="login-box">
        <el-form>
          <h2>Login</h2>
          <el-form-item>
            <el-input size="large" placeholder="admin" v-model="loginParam.userName">
              <template #prefix>
                <el-icon>
                  <UserFilled/>
                </el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-input size="large" type="password" placeholder="123456" v-model="loginParam.password">
              <template #prefix>
                <el-icon>
                  <Lock/>
                </el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" html-type="submit" size="large" @click="login()">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-main>
    <el-footer class="footer">
      xxx
    </el-footer>
  </el-container>
</template>

<script setup lang="ts">
import {ElMessage} from "element-plus";
import {useAuthStore} from "~/stores/auth.ts";
import router from "~/router";
import {Lock, UserFilled} from "@element-plus/icons-vue";
import {ref} from "vue";
// import axios from "axios";

const authStore = useAuthStore();

const loginParam = ref({
  userName: '',
  password: ''
});

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
  background: url('@/assets/login.svg');
  background-size: 100%;

  .login-box {
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
    padding-top: 240px;
  }
}


</style>