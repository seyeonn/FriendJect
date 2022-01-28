import Vue from 'vue';
import VueRouter from 'vue-router';                               
import Join from '../views/Join.vue';
import Home from '../views/Home.vue';
import Main from '../views/Main.vue';
// import Main from '../views/Main.vue';

Vue.use(VueRouter);                                               
export const router = new VueRouter({                             
    mode: 'history',                                              
    routes:[                                                      
        //path : url 주소, component : 페이지에 보여질 컴포넌트
        {                                                         
            path: '/',
            component: Home,
        },
        {
            path: '/join',
            name : 'join',
            component: Join,
        },
        {
            path: '/main',
            name : 'main',
            component: Main,
        },
        // {
        //     path: '/meet',
        //     name : 'meet',
        //     component: Meeting,
        // },
    ]
});