// authStore.ts
import {defineStore} from 'pinia';
import router from "~/router";

export const useAuthStore = defineStore('auth', {
    state: () => ({
        isAuthenticated: false,
    }),
    actions: {
        login() {
            this.isAuthenticated = true;
            // sessionStorage.setItem('isAuthenticated', 'true');
            // localStorage.setItem('isAuthenticated', 'true')
        },
        logout() {
            this.isAuthenticated = false;
            // sessionStorage.setItem('isAuthenticated', 'false');
            // localStorage.setItem('isAuthenticated', 'false')
            router.push("/login")
        },
    },
});
