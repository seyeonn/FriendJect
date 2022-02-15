<template>
  <div class="main_center" style="height:100%">
    <div>
    <!-- 아이스 브레이킹 -->
    <div @click="icebreaking">
      <a href="#icebreaking"><img src="https://i.imgur.com/Lc6TLfM.png" style="width: 15%; margin-left: 500px;"/></a>
    </div>

    <!-- 아이스 브레이킹 모달창 -->
    <div id="icebreaking" class="modal-window">
      <div>
        <a href="#" title="Close" class="modal-close">
            <b-icon icon="x-circle-fill" scale="2" variant="danger"></b-icon>
        </a>
        <div class="timer">
        <div v-show=showtimer>
          <input type="text" v-model="time" style="width: 30px">
          <button class="w-btn w-btn-white" v-on:click="submit" >시간 설정</button>
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
         <router-link :to="'/room/studyroom'">study</router-link>
      </b-col>
      <b-col class="box">
        <router-link :to="'/room/meetingroom'">Meeting</router-link>
      </b-col>
    </b-row>

    <b-row>
      <b-col class="box2" style="margin-right:10%">
        <router-link :to="'/room/projectroom'">Project</router-link>
      </b-col>
      <b-col class="box2">
        <router-link :to="'/room/consultroom'">Consult</router-link>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import axios from "axios";

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
    };
  },
  methods: {
    setTab: function(tab) {
      this.$emit("emitTab", tab);
    },
    // 아이스 브레이킹 질문 랜덤 조회
    icebreaking() {
      console.log("아이스 브레이킹");
      this.questions = [];
      axios
        .get(`http://localhost:8081/icebreaking/questions`)
        .then((response) => {
          console.log(response.data);

          for(var i=0; i<5; i++){
            this.questions.push(response.data[i].question);
          }
          
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 아이스 브레이킹 타이머 
    submit() {
      var time2 = parseInt(this.time)*60;
      var min2 = parseInt(this.min);
      var sec2 = parseInt(this.sec);
      var x;
      console.log(time2);

      if(time2 >= 1860){
        alert("30분까지만 가능합니다. 재설정 해주세요.");
      }
      else {
        this.showtimer = false;
        x = setInterval(function() {
          
          min2 = parseInt(time2/60);
          sec2 = time2%60;

          document.getElementById("demo").innerHTML = min2 + ":" + sec2;
          time2--;

          if(time2 < 0){
            clearInterval(x);
            document.getElementById("demo").innerHTML="BAAM!!";
            audio.play();
          }
        },1000);
      }
    }
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/style/icebreaking.scss";
</style>

<style>
@import url(https://fonts.googleapis.com/css?family=Open+Sans);

.icequestion {
  margin-left: 285px;
  width: 200px;
  margin-top: -100px;
  font-size: 14px;
}
.timer {
  margin-top: 210px;
  margin-left: -5px;
}
.timer > input{
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
.box {
  padding-top: 5%;
  padding-bottom: 7%;
  margin-left: 18%;
}
.box2 {
  padding-top: 18%;
  padding-bottom: 8%;
  margin-left: 18%;
}
</style>
