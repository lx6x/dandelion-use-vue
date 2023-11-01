import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import ElementPlus from 'element-plus'
// import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import 'element-plus/dist/index.css'
import "element-plus/theme-chalk/src/message.scss";
import 'element-plus/theme-chalk/index.css';
import 'uno.css';
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import router from "~/router";
import {createPinia} from "pinia";

const app = createApp(App)
// 状态管理库，允许你跨组件或页面共享状态
const pinia=createPinia();

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.use(ElementPlus)
app.use(router)
app.use(pinia)
app.mount('#app')


