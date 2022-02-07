import Vue from 'vue';
import VueRouter from 'vue-router';                               
import Join from '../views/Join.vue';
import Home from '../views/Home.vue';
import Main from '../views/Main.vue';
import MainNew from '../views/Main-new.vue';
import Choice from '../views/Choice.vue';

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
            path: '/choice',
            name : 'choice',
            component: Choice,
        },
        {
            path: '/main',
            name : 'main',
            component: Main,
        },
        {
            path: '/main-new',
            name : 'main-new',
            component: MainNew,
        },
        {
            path: "/projectroom",
            name: "projectroom",
            component: Project,
            redirect: "/projectroom/board",
            children: [
              {
                path: "files",
                name: "fileList",
                component: FileList,
              },
              {
                path: "files",
                name: "fileUpload",
                component: FileUpload,
              },
              {
                path: "board",
                name: "kanbanBoard",
                component: KanbanBoard,
              },
            ],
          },
        
    ]
});