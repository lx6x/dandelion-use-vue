// 导入 Vue 和 Vue Router
import {createRouter, createWebHistory} from 'vue-router';

// 导入组件
import Layout from "~/layouts/Layout.vue";
import {useAuthStore} from "~/stores/auth.ts";


// 创建路由实例
const router = createRouter({
    history: createWebHistory(),
    routes: [
        // 根目录自动跳转home
        {
            path: '/',
            redirect: '/home',
            meta: {
                hidden: true
            }
        },
        // 所有未定义路由，全部重定向到 404
        {
            path: '/:pathMatch(.*)*',
            redirect: '/404',
            meta: {
                hidden: true
            }
        },
        {
            path: '',
            component: Layout,
            redirect: '/home',
            meta: {
                hidden: true,
                title: '主页'
            },
            children: [
                {
                    path: '/home',
                    name: 'home',
                    component: () => import('~/views/Home.vue'),
                    meta: {
                        title: '主页',
                    }
                }
            ]
        },
        {
            path: '',
            component: Layout,
            redirect: '',
            meta: {
                hidden: true,
                title: 'Page'
            },
            children: [
                {
                    path: '/page1',
                    name: 'page1',
                    component: () => import('~/views/page1/index.vue'),
                    meta: {
                        title: 'page1',
                    }
                },
                {
                    path: '/page2',
                    name: 'page2',
                    component: () => import('~/views/page2/index.vue'),
                    meta: {
                        title: 'page2',
                    }
                },
            ]
        },
        {
            path: '/login',
            name: 'login',
            component: () => import('~/views/Login.vue'),
            meta: {
                hidden: true
            },
        },
        {
            path: '/404',
            name: 'NotFound',
            meta: {
                title: 'Page Not Found',
                hidden: true
            },
            component: () => import('~/views/error/404.vue'),
        },
    ],
});

router.beforeEach((to, __from) => {
    const authStore = useAuthStore();
    if (to.path !== '/login' && !authStore.isAuthenticated) {
        return {path: '/login'};
    } else {
        return true;
    }

})
// 导出路由实例
export default router;
