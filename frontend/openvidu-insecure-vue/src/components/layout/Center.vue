<template>
  <div class="main_center" style="height:100%">
    <div>

      <div class="cursor"> 
        <img style="width: 200px;" :src=profileUrl alt="my_minime"> 
      </div>
      <!-- 아이스 브레이킹 -->
      <div @click="icebreaking">
        <a href="#icebreaking"
          ><img
            src="https://i.imgur.com/Lc6TLfM.png"
            style="width: 15%; margin-left: 500px;"
        /></a>
      </div>

      <!-- 아이스 브레이킹 모달창 -->
      <div id="icebreaking" class="modal-window">
        <div>
          <a href="#" title="Close" class="modal-close">
            <b-icon icon="x-circle-fill" scale="2" variant="danger"></b-icon>
          </a>
          <div class="timer">
            <div v-show="showtimer">
              <input type="text" v-model="time" style="width: 30px" />
              <button class="w-btn w-btn-white" v-on:click="submit">
                시간 설정
              </button>
            </div>
            <div id="demo"></div>
          </div>
          <div class="icequestion">
            <div v-for="question in questions" :key="question">
              - {{ question }}
            </div>
          </div>
        </div>
      </div>
    </div>

    <b-row>
      <b-col class="box" style="margin-right:10%">
         <router-link :to="'/room/studyroom'"><img :src="study_img" @mouseover="study_mouse" @mouseleave="study_leave"
 class="door" /></router-link>
      </b-col>
      <b-col class="box">
        <router-link :to="'/room/meetingroom'"><img :src="meet_img" @mouseover="meet_mouse" @mouseleave="meet_leave"
 class="door" /></router-link>
      </b-col>
    </b-row>

    <b-row>
      <b-col class="box2" style="margin-right:10%">
        <router-link :to="'/room/projectroom'"><img :src="project_img" @mouseover="project_mouse" @mouseleave="project_leave"
 class="door" /></router-link>
      </b-col>
      <b-col class="box2">
        <router-link :to="'/room/consultroom'"><img :src="consult_img" @mouseover="consult_mouse" @mouseleave="consult_leave"
 class="door" /></router-link>
      </b-col>
    </b-row>

  </div>
</template>

<script>
import { getQuestionList } from "@/api/center.js";
import { mapState} from "vuex";
import jquery from 'jquery';

var audio = new Audio();
audio.src = "@/assets/ding-dong.mp3";

export default {
  data() {
    return {
      currentTab: "Center",
      questions: [],
      time: 0,
      min: 0,
      sec: 0,
      showtimer: true,
      study_img: "https://i.imgur.com/RrhPVhJ.png",
      meet_img: "https://i.imgur.com/okrgWZv.png",
      project_img: "https://i.imgur.com/GBQxYDq.png",
      consult_img: "https://i.imgur.com/l1XEp53.png"
    };
  },
  computed: {
    ...mapState(["profileUrl"]),
  },
  mounted() {
    this.showMinime();
    
  },
  methods: {
    showMinime: function() {
      jquery('.main_center').ready(function(){
        jquery('.main_center').mousemove(function(e){
          var mouseX = e.pageX;
          var mouseY = e.pageY;
        
        jquery('.cursor').css({
              left: mouseX + "px",
              top : mouseY + "px"
        })
        })
      })
      
    },
    setTab: function(tab) {
      this.$emit("emitTab", tab);
    },
    // 아이스 브레이킹 질문 랜덤 조회
    icebreaking() {
      // console.log("아이스 브레이킹");
      // this.questions = [];
      // getQuestionList(
      //   (response) => {
      //     console.log(response.data);

      //     for (var i = 0; i < 5; i++) {
      //       this.questions.push(response.data[i].question);
      //     }
      //   },
      //   (error) => {
      //     console.log(error);
      //   }
      // );
    },
    // 아이스 브레이킹 타이머
    submit() {
      var time2 = parseInt(this.time) * 60;
      var min2 = parseInt(this.min);
      var sec2 = parseInt(this.sec);
      var x;

      if (time2 >= 1860) {
        alert("30분까지만 가능합니다. 재설정 해주세요.");
      } else {
        this.showtimer = false;
        x = setInterval(function() {
          min2 = parseInt(time2 / 60);
          sec2 = time2 % 60;

          document.getElementById("demo").innerHTML = min2 + ":" + sec2;
          time2--;

          if (time2 < 0) {
            clearInterval(x);
            document.getElementById("demo").innerHTML = '<img src="https://i.imgur.com/hIIcPI9.png" style="margin-left: -60px; margin-top:-125px; width: 200px; height:200px;"/>';
            audio.play();
          }
        }, 1000);
      }

      console.log("아이스 브레이킹");
      this.questions = [];
      getQuestionList(
        (response) => {
          console.log(response.data);

          for (var i = 0; i < 5; i++) {
            this.questions.push(response.data[i].question);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    study_mouse: function(){
      this.study_img = "https://i.imgur.com/UpoyNOh.png"
    },   
    study_leave: function(){
      this.study_img = "https://i.imgur.com/RrhPVhJ.png"
    }, 
    meet_mouse: function(){
      this.meet_img = "https://i.imgur.com/o7r4iTg.png"
    },   
    meet_leave: function(){
      this.meet_img = "https://i.imgur.com/okrgWZv.png"
    }, 
    project_mouse: function(){
      this.project_img = "https://i.imgur.com/wU5poOo.png"
    },   
    project_leave: function(){
      this.project_img = "https://i.imgur.com/GBQxYDq.png"
    }, 
    consult_mouse: function(){
      this.consult_img = "https://i.imgur.com/1eRdXWT.png"
    },   
    consult_leave: function(){
      this.consult_img = "https://i.imgur.com/l1XEp53.png"
    }, 
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/style/icebreaking.scss";
</style>

<style>
@import url(https://fonts.googleapis.com/css?family=Open+Sans);

img.door {
  height: 200px;
  width: 200px;
}
img.door:hover {
  width: 200px;
  height: 200px;
}
.icequestion {
  margin-left: 285px;
  width: 200px;
  margin-top: -100px;
  font-size: 14px;
}
.timer {
  margin-top: 170px;
  margin-left: -5px;
  width: 170px;
}
.timer:active {
  margin-top: 170px;
  margin-left: -5px;
  width: 170px;
}
.timer > input {
  width: 50px;
}
#demo {
  font-size: 50px;
  color: white;
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
.w-btn-white {
  padding: 6px 12px;
  background-color: rgba(234, 237, 240, 0.712);
  color: #f9b225;
}

.w-btn-white-outline {
  border: 3px solid rgba(234, 237, 240, 0.712);
  color: #f9b225;
}

.w-btn-white-outline:hover {
  color: #f9b225;
  background: rgba(234, 237, 240, 0.712);
}
.box {
  padding-top: 0.8%;
  padding-bottom: 7%;
  margin-left: 12.5%;
}
.box2 {
  padding-top: 4.7%;
  margin-left: 12.5%;
}
.cursor { 
		position:absolute; 
		top:0px; 
		left: 0px; 
		z-index: 9999; 
		width: 250px; 
		height: 100px; 
		transform:translate(-50%, -50%); 
}
</style>
