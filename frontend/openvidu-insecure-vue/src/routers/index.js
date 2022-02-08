import Vue from "vue";
import VueRouter from "vue-router";
import Join from "../views/Join.vue";
import Home from "../views/Home.vue";
import Main from "../views/Main.vue";
import KakaoLogin from "../views/KakaoRedirect.vue";

//consult room
import Consult from "../views/ConsultRoom/ConsultRoom.vue";

// project room
import Project from "../views/Project.vue";

// meeting room
import Meeting from "../views/Meeting.vue";

// study room
import Study from "../views/Studyroom.vue";

Vue.use(VueRouter);
export const router = new VueRouter({
  mode: "history",
  routes: [
    //path : url 주소, component : 페이지에 보여질 컴포넌트
    {
      path: "/",
      component: Home,
    },
    {
      path: "/join",
      name: "join",
      component: Join,
    },
    {
      path: "/main",
      name: "main",
      component: Main,
    },
    {
      path: "/consultroom",
      name: "consultroom",
      component: Consult,
    },
    {
      path: "/studyroom",
      name: "studyroom",
      component: Study,
    },
    {
      path: "/kakao",
      name: "kakao",
      component: KakaoLogin,
    },
    {
      path: "/projectroom",
      name: "projectroom",
      component: Project,
    },
    {
      path: "/meetingroom",
      name: "meetingroom",
      component: Meeting,
    },
  ],
});
