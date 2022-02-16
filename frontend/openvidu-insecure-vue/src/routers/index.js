import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Room from "../views/Room.vue";
import Search from "../views/Search.vue";
import KakaoLogin from "../views/KakaoRedirect.vue";
import Center from "../components/layout/Center.vue";
//consult room
import Consult from "../views/ConsultRoom.vue";

// project room
import Project from "../views/Project.vue";
import MemoBoard from "../components/projectroom/MemoBoard.vue";
import FileList from "../components/projectroom/FileList.vue";

// meeting room
import Meeting from "../views/Meeting.vue";

// study room
import Study from "../views/Studyroom/Studyroom.vue";

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
      path: "/main",
      name: "main",
      component: Search,
    },
    {
      path: "/room",
      name: "room",
      component: Room,
      redirect: "room/main",
      props:true,
      children: [
        {
          path: "studyroom",
          name: "studyroom",
          component: Study,
        },
        {
          path: "main",
          name: "main",
          component: Center,
        },
        {
          path: "meetingroom",
          name: "meetingroom",
          component: Meeting,
        },
        {
          path: "consultroom",
          name: "consultroom",
          component: Consult,
        },
        {
          path: "projectroom",
          name: "projectroom",
          component: Project,
          redirect: "projectroom/memos",
          children: [
            {
              path: "memos",
              name: "memoboard",
              component: MemoBoard,
            },
            {
              path: "files",
              name: "filelist",
              component: FileList,
            },
          ],
        },
      ],
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
      redirect: "/projectroom/memos",
      children: [
        {
          path: "memos",
          name: "memoboard",
          component: MemoBoard,
        },
        {
          path: "files",
          name: "filelist",
          component: FileList,
        },
      ],
    },
    {
      path: "meetingroom",
      name: "meetingroom",
      component: Meeting,
    },
  ],
});
