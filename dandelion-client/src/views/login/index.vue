<template>
  <div class="login-box">
    <el-form ref="loginFormRef" :model="loginForm">
      <h2>Login</h2>
      <el-form-item>
        <el-input size="large" placeholder="admin" v-model="loginForm.userName" class="input-width">
          <template #prefix>
            <el-icon>
              <UserFilled/>
            </el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-input size="large" type="password" placeholder="123456" v-model="loginForm.password" class="input-width">
          <template #prefix>
            <el-icon>
              <Lock/>
            </el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="large" @click="login()" class="input-width">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>


<script setup lang="ts">
import {useAuthStore} from "~/stores/auth.ts";
import {reactive} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";
import router from "~/router";
// import axios from "axios";

const authStore = useAuthStore();

const loginForm = reactive({
  userName: '',
  password: ''
})



const login = async () => {

  const response = await axios.post('http://localhost:22333/api/login', loginForm)

  if (response.data.code == 200) {
    ElMessage({
      message: '登录成功',
      type: 'success'
    });
    authStore.login();
    await router.push({name: 'home'});
  } else {
    ElMessage({
      message: '登录失败',
      type: 'error'
    });
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


.login-box {
  width: 100vw;
  height: 100vh;
  background: url('~/assets/login.svg');
  background-size: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;

  .input-width {
    width: 100%;
  }

}


</style>