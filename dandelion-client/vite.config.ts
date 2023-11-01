// https://vitejs.dev/config/

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite';
import Components from 'unplugin-vue-components/vite';
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers';
import ElementPlus from 'unplugin-element-plus/vite'
import path from 'path'

import Unocss from 'unocss/vite'
import {presetAttributify, presetIcons, presetUno, transformerDirectives, transformerVariantGroup,} from 'unocss'

const pathSrc = path.resolve(__dirname, 'src')

export default defineConfig({
    resolve: {
        alias: {
            '~/': `${pathSrc}/`
        },
    },
    plugins: [
        vue(),
        ElementPlus({
            useSource: true,
        }),
        AutoImport({
            // 在这里配置 unplugin-auto-import 的选项
            resolvers: [ElementPlusResolver()]
        }),
        Components({
            // 在这里配置 unplugin-vue-components 的选项
            resolvers: [
                ElementPlusResolver({
                    importStyle: 'sass',
                })
            ],
            include: [/\.vue$/, /\.vue\?vue/, /\.md$/],
            extensions: ['vue', 'md'],
            dts: true,
            dirs: ['src/components', 'src/layouts'],
        }),
        // https://github.com/antfu/unocss
        // see unocss.config.ts for config
        Unocss({
            presets: [
                presetUno(),
                presetAttributify(),
                presetIcons({
                    scale: 1.2,
                    warn: true,
                }),
            ],
            transformers: [
                transformerDirectives(),
                transformerVariantGroup(),
            ]
        }),
    ],
    // 自定义主题
    css: {
        preprocessorOptions: {
            scss: {
                additionalData: `@use "~/styles/element/index.scss" as *;`,
            },
        },
    },
    server: {
        port: '11111',
        // port: Number(evn.PORT),
        // host: '0.0.0.0'
        proxy: {
            '/api': {
                // 目标接口前缀
                target: 'http://localhost:8081',
                // 开启跨域
                changeOrigin: true,
                // 路径重写
                rewrite: (path) => path.replace(/\/api/, '')
            }
        }
    },

})
