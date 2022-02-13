<template>
  <div class="main_study" style="height:100%">
    <div style="margin-top: 100px; margin-left: 220px;">
    <studyroom-player />
    </div>
    <div id="stopwatch" style="margin-top: 30px;">
      <!-- 스톱워치 기능 개발 -->
      <div class="stopwatch">{{ formattedElapsedTime }} &nbsp;&nbsp;</div>
      <div class="watch">
        <button class="w-btn w-btn-indigo" type="button" @click="start">
          Start
        </button>
        <button class="w-btn w-btn-green" type="button" @click="stop">
          Stop
        </button>
        <button class="w-btn w-btn-green2" type="button" @click="save">
          Save
        </button>
      </div>
    </div>
    <!-- 어제의 학습왕 조회 -->
    <div class="studybest">{{ studybest }}</div>

    <!-- 상태 메세지 등록 및 수정 -->
    <div class="comment" style="margin-top: 160px; margin-left: 122px;">
      <span  v-show=!showInput>{{ com }}</span>
      <input type="text" v-show=showInput v-model="value" class="input_comment"/>
      <button class="w-btn w-btn-white" v-on:click = "showInput =!showInput" @click="setDefault()">{{regOrEdit}}</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { regMember, regTime } from "@/api/member";
import StudyroomPlayer from "./StudyroomPlayer.vue";

export default {
  name: "Studyroom",
  components: {
    StudyroomPlayer,
  },
  data() {
    return {
      elapsedTime: 0,
      timer: undefined,
      userInfo: {
        userid: "aaaaa",
        time: 0,
      },
      studybest: "",
      value: "",
      com: "",
      showInput: true,
      regOrEdit: "등록",
    };
  },
  created() {
    // 스터디룸 입장 유저 감지
    console.log("studyroom entry");
    regMember(
      {
        userid: this.userInfo.userid,
        time: this.userInfo.time,
      },
      ({ data }) => {
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );

    // 어제의 학습왕 조회
    axios
      .get(`http://localhost:8081/studyroom`)
      .then((response) => {
        console.log("어제의 학습왕은?");
        console.log(response.data);
        this.studybest = response.data;
      })
      .catch();
  },
  mounted() {
    // getStudyBest(
    //         console.log("getstudyBest"),
    //         ({ data }) => {
    //           console.log("오늘의 학습왕은?");
    //           console.log(data);
    //           this.studybest = data;
    //         },
    //         (error) => {
    //           console.log(error);
    //         },
    //       );
  },
  // 스톱워치 설정
  computed: {
    formattedElapsedTime() {
      const date = new Date(null);
      date.setSeconds(this.elapsedTime / 1000);
      const utc = date.toUTCString();
      return utc.substr(utc.indexOf(":") - 2, 8);
    },
  },
  methods: {
    // 스톱워치 시작 버튼
    start() {
      this.timer = setInterval(() => {
        this.elapsedTime += 1000;
      }, 1000);
    },
    // 스톱워치 중지 버튼
    stop() {
      clearInterval(this.timer);
    },
    // 스톱워치 저장 버튼
    save() {
      console.log("front: " + this.elapsedTime / 1000);
      regTime(
        //this.$route.params.userid,
        {
          userid: this.userInfo.userid,
          time: this.elapsedTime / 1000,
        },
        ({ data }) => {
          console.log(data);
          this.userid = data;
          alert("저장이 완료되었습니다.");
        },
        (error) => {
          console.log(error);
        }
      );
    },
    // 상태 메세지 등록 및 수정
    setDefault() {
      console.log(this.value);
      this.com = this.value;
      this.regOrEdit = "수정";
    },
    // 오늘의 학습왕 12시 정각에 실행
    // studyb() {
    //   var today = new Date();
    //   var c_hour = today.getHours();
    //   var c_min = today.getMinutes();
    //   var c_sec = today.getSeconds();

    //   if(c_hour == 0 && c_min == 0 && c_sec == 0) {
    //     axios
    //   .get(`http://localhost:8081/studyroom`)
    //   .then((response) => {
    //     console.log("어제의 학습왕은?");
    //     console.log(response.data);
    //     this.studybest = response.data;
    //   })
    //   .catch();
    //   }
    // },
  },
};

document.addEventListener("DOMContentLoaded", () => {
  const cancelBonuses = document.querySelector(".app-cancel-bonuses");
  const app = document.querySelector(".app");

  const toggleBtn = document.querySelectorAll(".new-mail__toggle");
  toggleBtn.forEach((element) => {
    element.addEventListener("click", () => {
      document.querySelector(".new-mail").classList.toggle("active");
      document.querySelector(".new__button").classList.toggle("active");
    });
  });

  cancelBonuses.addEventListener("click", () => {
    const classes = ["weird-rotate", "bonus-zoom", "bonus-exit"];
    app.classList.remove(...classes);
    byeCancelButton();
  });

  function byeCancelButton() {
    cancelBonuses.classList.toggle("app-cancel-bonuses--active");
  }

  function toggleClassToApp(trigger, className, cancellable) {
    let bonus = document.querySelector(trigger);
    bonus.addEventListener("click", () => {
      app.classList.toggle(className);
      if (cancellable) byeCancelButton();
    });
  }

  toggleClassToApp(".bonus-weird-rotate", "weird-rotate", true);
  toggleClassToApp(".bonus-dark-mode", "dark-mode", false);
  toggleClassToApp(".bonus-zoom", "bonus-zoom", true);
  toggleClassToApp(".bonus-exit", "bonus-exit", true);
  toggleClassToApp(".bonus-why-so-serious", "why-so-serious", false);
  toggleClassToApp(".bonus-russia", "bonus-russia", false);
});
</script>

<style scoped>
@import url(https://fonts.googleapis.com/css?family=Open+Sans);

.studybest {
  font-size: 46px;
  margin-top: -100;
  margin-left: 780px;
}
.watch {
  display: inline;
}
.stopwatch {
  font-size: 35px;
  color: gray;
  display: inline;
  margin-top: 170px;
  margin-left: 190px;
}
.w-btn {
  position: relative;
  border: none;
  display: inline-block;
  padding: 10px 20px;
  border-radius: 15px;
  font-family: "paybooc-Light", sans-serif;
  box-shadow: 0 1px 20px rgba(0, 0, 0, 0.2);
  text-decoration: none;
  font-weight: 600;
  transition: 0.25s;
  margin-right: 5px;
}

.w-btn-outline {
  position: relative;
  padding: 10px 20px;
  border-radius: 15px;
  font-family: "paybooc-Light", sans-serif;
  box-shadow: 0 1px 20px rgba(0, 0, 0, 0.2);
  text-decoration: none;
  font-weight: 600;
  transition: 0.25s;
}

.w-btn-indigo {
  background-color: aliceblue;
  color: #1e6b7b;
}

.w-btn-indigo-outline {
  border: 3px solid aliceblue;
  color: #1e6b7b;
}

.w-btn-indigo-outline:hover {
  color: #1e6b7b;
  background: aliceblue;
}

.w-btn-white {
  padding: 6px 12px;
  background-color: rgba(234, 237, 240, 0.712);
  color: #F9B225;
}

.w-btn-white-outline {
  border: 3px solid rgba(234, 237, 240, 0.712);
  color: #F9B225;
}

.w-btn-white-outline:hover {
  color: #F9B225;
  background: rgba(234, 237, 240, 0.712);
}

.w-btn-green {
  background-color: #77af9c;
  color: #d7fff1;
}

.w-btn-green2 {
  background-color: #519d9e;
  color: #9dc8c8;
}

.w-btn-green-outline {
  border: 3px solid #77af9c;
  color: darkgray;
}

.w-btn-green2-outline {
  border: 3px solid #519d9e;
  color: darkgray;
}

.w-btn-green-outline:hover {
  background-color: #77af9c;
  color: #d7fff1;
}

.w-btn-green2-outline:hover {
  background-color: #519d9e;
  color: #9dc8c8;
}
</style>
