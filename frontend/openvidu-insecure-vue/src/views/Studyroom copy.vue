<template>
  <div style="background-color:white">
    <button class="app-cancel-bonuses">Please cancel this !</button>
    <div class="app">
      <div class="top">
      </div>
      <!-- 스터디룸 div-->
      <div class="app__content">
        <!-- 스톱워치 기능 개발 -->
        <div id="stopwatch" style="margin-top: 170px;">
          <div class="stopwatch">{{formattedElapsedTime}} &nbsp;&nbsp;</div>
          <div class="watch">
          <button class="w-btn w-btn-indigo" type="button" @click="start">Start</button>
          <button class="w-btn w-btn-green" type="button" @click="stop">Stop</button>
          <button class="w-btn w-btn-green2" type="button" @click="save">Save</button>
          </div>
        </div>
        <!-- 어제의 학습왕 조회 -->
        <div class="studybest">{{studybest}}</div>
        
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { regMember, regTime,  } from "@/api/member";

export default {
  name: "Studyroom",
  data() {
    return {
      elapsedTime: 0,
      timer: undefined,
      userInfo: {
        userid: "aaaaa",
        time: 0,
      },
      studybest: "",
    };
  },
  created() {
    // 스터디룸 입장 유저 감지
    console.log("studyroom entry");
      regMember(
        {
        userid: this.userInfo.userid,
        time: this.userInfo.time
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
        .get(`http://localhost:8080/studyroom`)
        .then((response) => {
          console.log("어제의 학습왕은?");
          console.log(response.data);
          this.studybest= response.data;
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
    }
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
      console.log("front: " + this.elapsedTime/1000);
      regTime(
        //this.$route.params.userid,
        {
          userid: this.userInfo.userid,
          time: this.elapsedTime/1000,
        },
        ({ data }) => {
          console.log(data);
          this.userid = data;
          
        },
        (error) => {
          console.log(error);
        }
      );
      
    }
  }
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
:root {
  --background-color: #fff;
  --font-color: #444;
}
.accounts {
  background: #F9B225;
  border-bottom-left-radius: 7px;
  padding: 10px;
}
.accounts__item {
  border-radius: 5px;
  cursor: pointer;
  margin-bottom: 10px;
  padding: 10px;
  position: relative;
}
.accounts__item:hover {
  background: #FDDD7C;
}
.accounts__item--active,
.accounts__item--active:hover {
  background: #FDDD7C;
}
.accounts__pill {
  position: absolute;
  right: 2px;
  top: 5px;
}
.accounts__avatar {
  border-radius: 40px;
  width: 40px;
}
.app {
  background: var(--background-color);
  border-radius: 7px;
  box-shadow: 0 11px 20px 0 rgba(0, 0, 0, 0.2);
  color: var(--font-color);
  display: grid;
  font-family: Nunito, sans-serif;
  grid-template-rows: auto 1fr;
  height: 100%;
  line-height: 1.5;
  min-width: 780px;
  overflow: hidden;
  position: relative;
}
.app.bonus-russia {
  background: linear-gradient(
    150deg,
    #e9e9e9,
    #e9e9e9 33%,
    #112794 0,
    #112794 66%,
    #c51b0e 0,
    #c51b0e
  );
}
.app.bonus-russia * {
  cursor: url(https://www.larousse.fr/encyclopedie/data/vignettes/1200048-Vladimir_Poutine.jpg)
      0 0,
    auto !important;
}
.app.weird-rotate {
  animation: weird-rotate 2s linear infinite alternate;
}
@keyframes weird-rotate {
  0% {
    transform: scale(0.3) skew(0deg, 3turn);
  }
  to {
    transform: scale(1) skew(1turn, 0deg);
  }
}
.app.dark-mode {
  --background-color: #28212b;
  --font-color: #e6e6e6;
}
.app.why-so-serious {
  --background-color: #751e9b;
  --font-color: #40ff26;
  font-family: cursive;
  font-size: 1.6em;
}
.app.bonus-exit {
  margin-top: 50%;
  transform: scale(0) translateY(-200%) rotate(500deg);
  transition: all, 1s ease;
}
.app.bonus-zoom {
  transform: scale(3);
  transition: transform, 1s ease;
}
.app__content {
  background-image: url('https://i.imgur.com/GWmnN0N.png');
  background-size: 1260px 700px;
}

  .app__content {
    grid-template-columns: auto 80px 1fr;
  }

.app-info {
  background: #F9DF8D;
  border-radius: 0 50px 50px 0;
  font-family: Nunito, sans-serif;
  font-size: 0.9rem;
  font-weight: 700;
  left: 0;
  padding: 5px 10px;
  position: absolute;
  top: 0;
  z-index: 1;
}
.app-info,
.app-info a {
  color: var(--background-color);
}
.app-info--right {
  border-radius: 50px 0 0 50px;
  cursor: pointer;
  left: unset;
  right: 0;
}
.app-cancel-bonuses {
  background: #F9DF8D;
  border: 0;
  border-radius: 200px;
  color: #fff;
  cursor: pointer;
  font-size: 2rem;
  left: 50%;
  outline: 0;
  padding: 10px 50px;
  position: fixed;
  top: 50%;
  transform: translate(-50%, -50%);
  visibility: hidden;
  z-index: 999999;
}
.app-cancel-bonuses--active {
  visibility: visible;
}
.mails {
  display: grid;
  grid-template-columns: minmax(400px, auto) 1fr;
}
@media (max-width: 1020px) {
  .mails {
    grid-template-columns: 1fr;
    grid-template-rows: 1fr 2fr;
  }
}
body {
  background-image: linear-gradient(90deg, #ff9a8b, #ff6a88 55%, #ff99ac);
  height: 100vh;
  overflow: hidden;
  padding: 2.5vw;
}
*,
:after,
:before {
  box-sizing: border-box;
  scrollbar-color: rgba(0, 0, 0, 0.2) transparent;
  scrollbar-width: thin;
}
::-webkit-scrollbar {
  width: 10px;
}
::-webkit-scrollbar-corner {
  background: transparent;
}
::-webkit-scrollbar-thumb {
  background-clip: content-box;
  background-color: hsla(0, 0%, 47.1%, 0);
  border: 2px solid transparent;
  border-radius: 20px;
}
button,
input,
textarea {
  color: #444;
  font-family: Nunito, sans-serif;
  font-size: 1rem;
  line-height: 1.4;
}
.button {
  background: #ebebeb;
  border: 0;
  border-radius: 100px;
  cursor: pointer;
  font-size: 0.8rem;
  font-weight: 700;
  line-height: 1;
  outline: 0;
  padding: 10px 25px;
  text-transform: uppercase;
}
.button--primary {
  background: #F9DF8D;
  color: var(--background-color);
}
.date {
  color: hsla(0, 0%, 50.2%, 0.8);
  font-size: 0.8rem;
  font-style: italic;
  font-weight: 400;
}
.dot {
  border-radius: 8px;
  display: inline-block;
  height: 8px;
  margin-right: 3px;
  position: relative;
  top: -1px;
  width: 16px;
}
.dot--red {
  background: #ff6767;
}
.dot--blue {
  background: #67dcff;
}
.dot--green {
  background: #54dd89;
}
.dot--yellow {
  background: #ffcf67;
}
.dot--purple {
  background: #9567ff;
}
.dot--pink {
  background: #fc84ec;
}
.scrollable {
  position: relative;
}
.scrollable__target {
  bottom: 0;
  left: 0;
  overflow: auto;
  position: absolute;
  right: 0;
  top: 0;
}
.message-list {
  border-right: 1px solid hsla(0, 0%, 50.2%, 0.2);
}
.message-list > .scrollable__target {
  padding: 15px 5px 15px 15px;
}
.message-list:hover ::-webkit-scrollbar-thumb {
  background-color: hsla(0, 0%, 47.1%, 0.2);
}
.message {
  background: hsla(0, 0%, 50.2%, 0.08);
  border: 1px solid hsla(0, 0%, 50.2%, 0.2);
  border-bottom: 0;
  color: hsla(0, 0%, 50.2%, 0.9);
  cursor: pointer;
  display: grid;
  grid-template-columns: auto 1fr;
  position: relative;
}
.message:first-of-type {
  border-radius: 5px 5px 0 0;
}
.message:last-of-type {
  border-bottom: 1px solid hsla(0, 0%, 50.2%, 0.2);
  border-radius: 0 0 5px 5px;
}
.message--new {
  background: var(--background-color);
  color: var(--font-color);
}
.message--active:before {
  background: #F9DF8D;
  border-radius: 5px;
  bottom: 3px;
  content: "";
  left: 3px;
  position: absolute;
  top: 3px;
  width: 4px;
  z-index: 1;
}
.message__content {
  padding: 15px 15px 15px 0;
}
@media (max-width: 1020px) {
  .message__content {
    padding: 10px 15px 10px 0;
  }
}
.message__exp {
  color: hsla(0, 0%, 50.2%, 0.8);
  display: flex;
  font-size: 0.8rem;
  font-style: italic;
  font-weight: 700;
  justify-content: space-between;
}
.message__expr {
  font-size: 0.9rem;
  font-style: italic;
  max-width: 300px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
@media (max-width: 1020px) {
  .message__expr {
    max-width: 1000px;
  }
}
.message__title {
  font-weight: 700;
}
.message__actions {
  align-content: space-between;
  display: grid;
  grid-auto-flow: row;
  grid-gap: 5px;
  padding: 15px;
  text-align: center;
  transform: translateX(-10px);
  transition: transform 0.2s ease;
  visibility: hidden;
}
.message__icon {
  color: hsla(0, 0%, 50.2%, 0.5);
  font-size: 0.9rem;
}
.message__icon:hover {
  color: var(--font-color);
}
.message:hover .message__actions {
  transform: translateX(0);
  visibility: visible;
}
.message-tags {
  bottom: 2px;
  line-height: 1;
  position: absolute;
  right: 2px;
}
.menu {
  border-right: 1px solid hsla(0, 0%, 50.2%, 0.2);
  display: grid;
  grid-template-rows: auto 1fr auto;
  padding: 10px 20px 20px;
  position: relative;
  z-index: 2;
}
@media (max-width: 1300px) {
  .menu {
    padding: 10px;
  }
}
.menu-user {
  border-bottom: 1px solid hsla(0, 0%, 50.2%, 0.2);
  margin-bottom: 20px;
  padding: 10px 10px 15px;
}
@media (max-width: 1300px) {
  .menu-user {
    margin-bottom: 10px;
    padding: 10px 0 15px;
  }
  .menu-user .profile-head__mail,
  .menu-user .profile-head__name {
    display: none;
  }
}
.menu-tags {
  font-size: 0.9rem;
  font-weight: 700;
  padding: 20px 0 0;
}
@media (max-height: 700px) {
  .menu-tags {
    display: none;
  }
}
@media (max-width: 1300px) {
  .menu-tags__label {
    display: none;
  }
}
.menu-tags__item {
  border-radius: 50px;
  color: hsla(0, 0%, 50.2%, 0.8);
  cursor: pointer;
  margin-bottom: 2px;
  padding: 2px 10px;
}
.menu-tags__item:hover {
  background: hsla(0, 0%, 50.2%, 0.1);
}
@media (max-width: 1300px) {
  .menu-tags__item {
    text-align: center;
  }
}
.menu-main {
  border-bottom: 1px solid hsla(0, 0%, 50.2%, 0.2);
}
@media (max-width: 1300px) {
  .menu-main__pill {
    display: none !important;
  }
}
.menu__icon {
  color: hsla(0, 0%, 50.2%, 0.4);
  margin-right: 10px;
}
@media (max-width: 1300px) {
  .menu__icon {
    margin-right: 0;
  }
}
.menu__item {
  align-items: center;
  border-radius: 50px;
  cursor: pointer;
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
  padding: 15px 30px;
  transition: background 0.15s ease;
}
@media (max-width: 1300px) {
  .menu__item {
    display: block;
    font-size: 1.5rem;
    margin-bottom: 5px;
    padding: 5px 10px 7px;
    text-align: center;
  }
}
.menu__item:hover {
  background: hsla(0, 0%, 50.2%, 0.1);
}
.menu__item.active {
  background: #F9B225;
  color: #fff;
}
.menu__item.active .menu__icon {
  color: hsla(0, 0%, 100%, 0.8);
}
.menu__label {
  font-weight: 700;
}
@media (max-width: 1300px) {
  .menu__label {
    display: none;
  }
}
@media (max-width: 1300px) {
  .new {
    text-align: center;
  }
}
.new__button {
  background: #F9E7AD;
  border: 0;
  border-radius: 50px;
  color: var(--background-color);
  cursor: pointer;
  font-size: 1.6rem;
  height: 50px;
  outline: 0;
  transition: transform 0.2s ease;
  width: 50px;
}
.new__button:hover {
  transform: scale(1.1);
}
.new-mail,
.new__button.active {
  transform: scale(0);
}
.new-mail {
  background: var(--background-color);
  border: 1px solid hsla(0, 0%, 50.2%, 0.2);
  border-radius: 7px;
  bottom: 20px;
  box-shadow: 0 11px 20px 0 transparent;
  left: 20px;
  opacity: 0;
  position: absolute;
  transform-origin: bottom left;
  transition-duration: 0.3s;
  transition-property: transform, visibility, opacity;
  transition-timing-function: ease-in-out;
  visibility: hidden;
  width: 600px;
  z-index: 99;
}
.new-mail__close {
  cursor: pointer;
  padding: 5px;
}
.new-mail.active {
  box-shadow: 0 11px 20px 0 rgba(0, 0, 0, 0.2);
  opacity: 1;
  transform: scale(1);
  visibility: visible;
}
.new-mail__top {
  align-items: center;
  display: flex;
  justify-content: space-between;
  padding: 10px 10px 10px 40px;
}
.new-mail-exp,
.new-mail__top {
  border-bottom: 1px solid hsla(0, 0%, 50.2%, 0.2);
  font-weight: 700;
}
.new-mail-exp {
  background: hsla(0, 0%, 50.2%, 0.1);
  color: #636363;
  padding: 10px 40px;
}
.new-mail-exp__item {
  align-items: center;
  display: grid;
  grid-template-columns: 70px 1fr;
  margin-bottom: 10px;
}
.new-mail-exp__input {
  background: transparent;
  border: 0;
  border-bottom: 1px solid hsla(0, 0%, 50.2%, 0.2);
  font-weight: 700;
  outline: 0;
  padding: 5px;
  width: 100%;
}
.new-mail-exp__input::placeholder {
  color: #9c9c9c;
  font-style: italic;
  font-weight: 400;
}
.new-mail-exp__label {
  font-size: 0.8rem;
}
.new-mail-foot {
  align-items: center;
  display: flex;
  justify-content: space-between;
  padding: 20px 40px;
}
.new-mail-foot__icon {
  border-radius: 5px;
  color: #b1b1b1;
  cursor: pointer;
  padding: 10px;
}
.new-mail-foot__icon:hover {
  background: rgba(0, 0, 0, 0.07);
  color: #6d6d6d;
}
.new-mail__message {
  background: var(--background-color);
  border: 1px solid transparent;
  border-radius: 5px;
  color: var(--font-color);
  max-height: 50vh;
  min-height: 30vh;
  outline: 0;
  padding: 20px 40px;
  resize: vertical;
  width: 100%;
}
.paragraph {
  margin: 20px 0;
}
.pill {
  align-items: center;
  background: hsla(0, 0%, 50.2%, 0.1);
  border-radius: 30px;
  color: var(--font-color);
  display: flex;
  font-size: 0.7rem;
  font-weight: 700;
  height: 20px;
  line-height: 1;
  padding: 1px 10px 0;
}
.pill--solid {
  background: #fff;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.4);
  color: #000;
}
.preview {
  display: grid;
  grid-template-rows: auto 1fr auto;
}
.preview:hover ::-webkit-scrollbar-thumb {
  background-color: hsla(0, 0%, 47.1%, 0.3);
}
@media (max-width: 1020px) {
  .preview {
    border-top: 1px solid hsla(0, 0%, 50.2%, 0.2);
  }
}
.preview__title {
  font-size: 1.6rem;
  font-weight: 700;
}
.preview-top {
  align-items: center;
  border-bottom: 1px solid hsla(0, 0%, 50.2%, 0.2);
  display: flex;
  justify-content: space-between;
  padding: 10px 20px;
}
.preview-top__icon {
  color: hsla(0, 0%, 50.2%, 0.5);
  cursor: pointer;
  padding: 5px;
}
.preview-top__icon:hover {
  color: grey;
}
.preview-content {
  padding: 20px;
}
.preview-respond {
  background: var(--background-color);
  border: 1px solid hsla(0, 0%, 50.2%, 0.2);
  border-radius: 5px;
  box-shadow: 0 11px 20px 0 rgba(0, 0, 0, 0.1);
  margin: 0 auto 20px;
  max-width: 600px;
}
.preview-respond__who {
  font-weight: 700;
}
.preview-respond__who-mail {
  color: #838383;
  font-size: 0.8rem;
}
.preview-respond__head {
  border-bottom: 1px solid hsla(0, 0%, 50.2%, 0.2);
  padding: 10px 20px;
}
.preview-respond__content {
  padding: 20px 40px;
}
.preview-foot {
  border-top: 1px solid hsla(0, 0%, 50.2%, 0.2);
  display: flex;
  justify-content: flex-end;
  padding: 20px;
}
.preview-foot__button {
  margin-left: 10px;
}
.profile-head {
  align-items: start;
  display: flex;
  justify-content: space-between;
}
.profile-head__id {
  align-items: center;
  display: flex;
}
.profile-head__name {
  font-weight: 700;
}
.profile-head__mail {
  color: hsla(0, 0%, 50.2%, 0.8);
  font-size: 0.8rem;
}
.profile-head__avatar {
  border-radius: 30px;
  display: inline-block;
  margin-right: 10px;
  width: 30px;
}
.select {
  border: 1px solid hsla(0, 0%, 50.2%, 0.2);
  border-radius: 5px;
  display: inline-block;
  line-height: 1;
  padding-right: 5px;
  position: relative;
}
.select__item {
  all: unset;
  padding: 5px 10px;
}
.select__arrow {
  position: relative;
  right: 5px;
  top: -2px;
}
.top {
  align-items: center;
  border-bottom: 1px solid hsla(0, 0%, 50.2%, 0.2);
  border-radius: 7px 7px 0 0;
  color: #999;
  display: flex;
  font-size: 0.8rem;
  font-weight: 700;
  justify-content: space-between;
  position: relative;
  z-index: 999;
}
.top-search {
  background: hsla(0, 0%, 50.2%, 0.1);
  border-radius: 50px;
  padding: 2px 10px;
}
.top-menu {
  display: grid;
  grid-auto-flow: column;
  line-height: 1;
  padding-left: 15px;
}
.top-menu-sub {
  background: var(--background-color);
  border: 1px solid hsla(0, 0%, 50.2%, 0.2);
  border-radius: 0 5px 5px 5px;
  box-shadow: 0 11px 20px 0 rgba(0, 0, 0, 0.2);
  left: 0;
  padding: 10px 0;
  position: absolute;
  top: calc(100% - 7px);
  transition: top 0.2s ease;
  visibility: hidden;
}
.top-menu-sub__item {
  cursor: pointer;
  padding: 7px 15px;
  white-space: nowrap;
}
.top-menu-sub__item:first-of-type {
  border-radius: 0 5px 0 0;
}
.top-menu-sub__item:last-of-type {
  border-radius: 0 0 5px 5px;
}
.top-menu-sub__item:hover {
  color: var(--font-color);
}
.top-menu-sub__link {
  color: hsla(0, 0%, 50.2%, 0.8);
  text-decoration: none;
}
.top-menu-sub__link:hover {
  color: var(--font-color);
}
.top-menu-sub__separator {
  border-bottom: 1px solid hsla(0, 0%, 50.2%, 0.2);
  margin: 5px 0;
}
.top-menu__label {
  border: 1px solid transparent;
  border-radius: 5px;
  cursor: pointer;
  padding: 5px 15px;
  position: relative;
  z-index: 1;
}
.top-menu__label:hover {
  background: var(--background-color);
  border: 1px solid hsla(0, 0%, 50.2%, 0.2);
  border-bottom: 1px solid var(--background-color);
  border-radius: 5px 5px 0 0;
}
.top-menu__item {
  position: relative;
}
.top-menu__item:hover > .top-menu-sub {
  top: calc(100% - 1px);
  visibility: visible;
}
.top-menu__item:hover > .top-menu__label {
  border: 1px solid hsla(0, 0%, 50.2%, 0.2);
  border-bottom: 1px solid var(--background-color);
  border-radius: 5px 5px 0 0;
}
.top-action {
  display: flex;
}
.top-action__item {
  cursor: pointer;
  font-size: 1rem;
  padding: 15px;
}
.top-action__item:hover {
  background: hsla(0, 0%, 50.2%, 0.1);
  color: var(--font-color);
}
.top-action__item:last-of-type {
  border-radius: 0 7px 0 0;
}
.top-action__item:last-of-type:hover {
  background: #F9E7AD;
  color: var(--background-color);
}
.input {
  background: transparent;
  border: 0;
  font-weight: 700;
  min-width: 350px;
  outline: 0;
  padding: 2px 5px;
}
.input::placeholder {
  color: #a3a3a3;
  font-style: italic;
}
</style>
