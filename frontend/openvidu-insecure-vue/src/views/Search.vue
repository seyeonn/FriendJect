<template>
  <div>
    <div class="cards" style="width: 100%">
      <article
        class="information [ card ]"
        style="width: 40%; margin-right: 10%"
      >
        <span class="tag">새로운 프로젝트 생성 </span>
        <h2 class="title">팀 생성</h2>
        <p class="info" style="font-size: 0.9rem">
          팀 참여코드는 자동으로 생성되고, 클립보드에 복사됩니다. <br />
          팀 이름만 설정해보세요!
        </p>
        <b-input-group size="md">
          <b-form-input
            v-model="teamName"
            class="form-control"
            type="text"
          ></b-form-input>
        </b-input-group>
        <br />
        <button class="button" @click="randomNumber">
          <span>Create</span>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="24px"
            viewBox="0 0 24 24"
            width="24px"
            fill="none"
          >
            <path d="M0 0h24v24H0V0z" fill="none" />
            <path
              d="M16.01 11H4v2h12.01v3L20 12l-3.99-4v3z"
              fill="currentColor"
            />
          </svg>
        </button>
      </article>


      <article class="information [ card ]" style="width: 40%">
        <span class="tag">기존 프로젝트에 참여하기</span>
        <h2 class="title">팀 코드로 참가</h2>
        <p class="info" style="font-size: 0.9rem">
          팀원에게 받은 고유코드를 입력하세요!
        </p>
        <b-input-group size="md">
          <b-form-input
            v-model="teamNum"
            class="form-control"
            type="text"
          ></b-form-input>
        </b-input-group>
        <!-- 이부분 현재 바인딩이 안됨. code 바인딩해서 넘겨주기 -->
        <router-link
          :to="{ name: 'minime', params: { code: '123' } }"
          class="button"
        >
          MINIME
        </router-link>
        <b-button @click="checkTeamExists" class="button">ROOM</b-button>
        <button class="button" @click="checkTeamExists">
          <span>Join</span>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="24px"
            viewBox="0 0 24 24"
            width="24px"
            fill="none"
          >
            <path d="M0 0h24v24H0V0z" fill="none" />
            <path
              d="M16.01 11H4v2h12.01v3L20 12l-3.99-4v3z"
              fill="currentColor"
            />
          </svg>
        </button>
      </article>
    </div>

    <div>
      <h2 style="color: #3d3d3c; margin-left: 10%">참여중인 프로젝트</h2>
      <!-- 이부분 접속 했던 이력을 for 문으로 나타내기 -->
      <div class="cards" style="width: 100%">
        <article
          class="information [ card ]"
          v-for="(value, index) in this.teams"
          :key="index"
        >
          <dl class="details">
            <div>
              <dt>#{{ value.teamNumber }}</dt>
              <dd>{{ value.name }}</dd>
            </div>
          </dl>
          <router-link
            to="/room"
            style="background-color: #f9b225; color: white"
            tag="button"
            @click.native="goToRoom(value.id)"
            >접속하기</router-link
          >
        </article>
      </div>
    </div>
  </div>
</template>

<script>
import { getOneTeam, joinTeam, createTeam, getTeamList } from "@/api/center.js";
import { mapActions, mapState } from "vuex";
export default {
  name: "search",
  data() {
    return {
      message: "",
      test: "",
      teamNum: "",
      teamInfo: "",
      userId: localStorage.getItem("userId"),
      teams: [],
    };
  },
  created() {
    // 참여중인 프로젝트 목록
    console.log(this.userId);
    getTeamList(
      this.userId,
      (response) => {
        console.log(response.data.data);
        this.teams = response.data.data;
        console.log("length: ", this.teams.length);
      },
      (error) => {
        console.log(error);
      }
    );
  },
  computed: {
    ...mapState(["teamId"]),
    ...mapState(["teamNumber"]),
    ...mapState(["teamName"]),
  },
  methods: {
    ...mapActions(["setCurrentTeam"]),
    // 참여중인 프로젝트 접속
    goToRoom(id) {
      console.log(id);
      //vuex에 저장 (teamName, teamId, teamNumber)
      getOneTeam(
        id,
        (response) => {
          this.setCurrentTeam({ ...response.data.data });
          console.log(response.data.data);
          this.$store.commit("setTeamId", response.data.data.id);
          this.$store.commit("setTeamNumber", response.data.data.teamNumber);
          this.$store.commit("setTeamName", response.data.data.name);
          localStorage.setItem("teamId", this.teamId);
        },
        (error) => {
          if (error.response) {
            alert("존재하지 않는 팀 입니다.\n 팀 코드를 다시 확인해주세요");
            this.$router.go();
          }
        }
      );
    },
    // 팀 코드로 참가
    checkTeamExists() {
      getOneTeam(
        this.teamNum,
        (response) => {
          console.log(response.data.data);

          this.teamInfo = {
            userId: this.userId,
            teamNumber: response.data.data.teamNumber,
          };

          console.log(this.teamInfo.teamNumber);
          joinTeam(
            this.teamInfo,
            (response) => {
              console.log(response.data);
              //this.$router.push({ name: "room" });
            },
            (error) => {
              if (error.response) {
                console.log("이미 가입된 팀");
                //this.$router.replace({ name: "room" });
                //this.$router.push("/room/main");
              }
            }
          );
        },
        (error) => {
          if (error.response) {
            alert("존재하지 않는 팀 입니다.\n 팀 코드를 다시 확인해주세요");
            this.$router.go();
          }
        }
      );
    },
    // 팀 생성
    randomNumber() {
      this.teamNum = Math.random().toString(36).substr(2, 11);
      console.log(this.teamNum);
      createTeam(
        {
          userId: localStorage.getItem("userId"),
          teamName: this.teamName,
          teamNum: this.teamNum,
        },
        (response) => {
          this.arrTodo = response.data.data;
          // 팀 생성과 동시에 이동
          // TODO 바로 복사 되는 기능?
          this.$router.push({ name: "minime" }); // 화면이 안 움직임 아놔
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style lang="scss" scope>
@import "@/assets/style/join_room.scss";
</style>
