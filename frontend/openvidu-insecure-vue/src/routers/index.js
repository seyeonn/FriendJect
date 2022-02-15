import Vue from "vue";
import VueRouter from "vue-router";
import Join from "../views/Join.vue";
import JoinTest from "../views/JoinTest.vue";
import Home from "../views/Home.vue";
import Main from "../views/Main.vue";
import KakaoLogin from "../views/KakaoRedirect.vue";

//consult room
import Consult from "../views/ConsultRoom/ConsultRoom.vue";

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
      name: "/",
      component: Home,
    },
    {
      path: "/join",
      name: "join",
      component: Join,
    },
    {
      path: "/jointest",
      name: "join",
      component: JoinTest,
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
// 전역 가드
// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html#%E1%84%8C%E1%85%A5%E1%86%AB%E1%84%8B%E1%85%A7%E1%86%A8-%E1%84%80%E1%85%A1%E1%84%83%E1%85%B3
router.beforeEach((to, from, next) => {
  const accessToken = localStorage.getItem("accessToken");
  if (to.name === "/") {
    if (accessToken) {
      next({ name: "join" });
    }
  } else if (to.name === "join") {
    if (!accessToken) {
      next({ name: "/" });
    }
  }
  next();
});
