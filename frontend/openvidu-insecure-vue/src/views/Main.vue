<template>
  <div>
    <div class="container" fluid x-data="{ rightSide: false, leftSide: false }">
      <div class="main">
        <div class="main-container">
          <!-- <img
            src="@/assets/images/main_day.png"
            style="width:100%; height:100%"
          /> -->
          <!-- 이 부분에 if문 써서 컴포넌트로 각 4개의 방이 들어가야함.   -->
          <!-- <router-link :to="{ name: 'consult', params: { username: 'erina' }}">
            상담실
          </router-link> -->
          <div id="dynamic-component-demo" class="demo">
            <button
              v-for="tab in tabs"
              :key="tab"
              :class="['tab-button', { active: currentTab === tab }]"
              @click="currentTab = tab"
            >
              {{ tab }}
            </button>

            <!-- Inactive components will be cached! -->
            <keep-alive>
              <component :is="currentTab"> </component>
            </keep-alive>
          </div>
        </div>
      </div>
      <div class="right-side" :class="{ active: rightSide }">
        <div class="account">
          <button class="account-button">
            <svg
              stroke="currentColor"
              stroke-width="2"
              fill="none"
              stroke-linecap="round"
              stroke-linejoin="round"
              class="css-i6dzq1"
              viewBox="0 0 24 24"
            >
              <path
                d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"
              />
              <path d="M22 6l-10 7L2 6" />
            </svg>
          </button>
          <button class="account-button">
            <svg
              stroke="currentColor"
              stroke-width="2"
              fill="none"
              stroke-linecap="round"
              stroke-linejoin="round"
              class="css-i6dzq1"
              viewBox="0 0 24 24"
            >
              <path
                d="M18 8A6 6 0 006 8c0 7-3 9-3 9h18s-3-2-3-9M13.73 21a2 2 0 01-3.46 0"
              />
            </svg>
          </button>
          <span class="account-user"
            >Quan Ha
            <img
              src="https://images.genius.com/2326b69829d58232a2521f09333da1b3.1000x1000x1.jpg"
              alt=""
              class="account-profile"
            />
            <span>▼</span>
          </span>
        </div>
        <div class="side-wrapper stories">
          <div class="side-title">STORIES</div>
          <div class="user">
            <img
              src="https://pbs.twimg.com/profile_images/1102351320567164931/ZCkJgJIH.png"
              alt=""
              class="user-img"
            />
            <div class="username">
              Lisandro Matos
              <div class="album-date">12 hours ago</div>
            </div>
          </div>
          <div class="user">
            <img
              src="https://pbs.twimg.com/profile_images/1153966095444992000/1lpIyHaQ.jpg"
              alt=""
              class="user-img"
            />
            <div class="username">
              Gvozden Boskovsky
              <div class="album-date">29 minutes ago</div>
            </div>
          </div>
          <div class="user">
            <img
              src="https://images.unsplash.com/photo-1565464027194-7957a2295fb7?ixlib=rb-1.2.1&auto=format&fit=crop&w=3500&q=80"
              alt=""
              class="user-img"
            />
            <div class="username">
              Hnek Fortuin
              <div class="album-date">3 hours ago</div>
            </div>
          </div>
          <div class="user">
            <img
              src="https://images.unsplash.com/photo-1527980965255-d3b416303d12?ixlib=rb-1.2.1&auto=format&fit=crop&w=1400&q=80"
              alt=""
              class="user-img"
            />
            <div class="username">
              Lubomir Dvorak
              <div class="album-date">18 hours ago</div>
            </div>
          </div>
        </div>

        <div style="text-align:center;" v-on:click="getLog">
          <a href="#consultLog"> <div>상담내역보기</div> </a>
        </div>

        <div class="side-wrapper contacts">
          <div class="side-title">CONTACTS</div>
          <div class="user">
            <img
              src="https://randomuser.me/api/portraits/men/1.jpg"
              class="user-img"
            />
            <div class="username">
              Andrei Mashrin
              <div class="user-status"></div>
            </div>
          </div>
          <div class="user">
            <img
              src="https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=046c29138c1335ef8edee7daf521ba50"
              class="user-img"
            />
            <div class="username">
              Aryn Jacobssen
              <div class="user-status offline"></div>
            </div>
          </div>
          <div class="user">
            <img
              src="https://images.unsplash.com/photo-1575084713138-342cae5f8d00?ixlib=rb-1.2.1&auto=format&fit=crop&w=958&q=80"
              class="user-img"
            />
            <div class="username">
              Carole Landu
              <div class="user-status offline"></div>
            </div>
          </div>
          <div class="user">
            <img
              src="https://images.pexels.com/photos/598745/pexels-photo-598745.jpeg?h=350&auto=compress&cs=tinysrgb"
              class="user-img"
            />
            <div class="username">
              Chineze Afa
              <div class="user-status"></div>
            </div>
          </div>
          <div class="user">
            <img
              src="https://pbs.twimg.com/profile_images/2452384114/noplz47r59v1uxvyg8ku.png"
              class="user-img"
            />
            <div class="username">
              Mok Kwang
              <div class="user-status"></div>
            </div>
          </div>
          <div class="user">
            <img
              src="https://randomuser.me/api/portraits/women/63.jpg"
              class="user-img"
            />
            <div class="username">
              Naomi Yepes
              <div class="user-status"></div>
            </div>
          </div>
          <div class="user">
            <img
              src="https://images.unsplash.com/photo-1476493279419-b785d41e38d8?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=61eaea85f1aa3d065400179c78163f15"
              class="user-img"
            />
            <div class="username">
              Shaamikh Ale
              <div class="user-status"></div>
            </div>
          </div>
          <div class="user">
            <img
              src="https://m.media-amazon.com/images/M/MV5BMjI4NDcyNjQxNl5BMl5BanBnXkFtZTgwMzI4OTM3NjM@._V1_UY256_CR13,0,172,256_AL_.jpg"
              class="user-img"
            />
            <div class="username">
              Sofia Alcocer
              <div class="user-status idle"></div>
            </div>
          </div>
          <div class="user">
            <img
              src="https://images.unsplash.com/photo-1509380836717-c4320ccf1a6f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=e01c8c45a063daaf6d6e571a32bd6c90"
              class="user-img"
            />
            <div class="username">
              Wen Yahui
              <div class="user-status"></div>
            </div>
          </div>
          <div class="user">
            <img
              src="https://pbs.twimg.com/profile_images/737221709267374081/sdwta9Oh.jpg"
              alt=""
              class="user-img"
            />
            <div class="username">
              Leslee Moss
              <div class="user-status idle"></div>
            </div>
          </div>
        </div>
      </div>

      <!-- 모달 -->
      <div id="consultLog" class="modal-window">
        <div>
          <a href="#" title="Close" class="modal-close">
            <b-icon icon="x-circle-fill" scale="2" variant="danger"></b-icon>
          </a>

          <h1>지난 상담내역보기</h1>
          <!-- <div><small>Check out</small></div> -->
          {{ log }}
        </div>
      </div>

      <div
        class="overlay"
        @click="
          rightSide = false;
          leftSide = false;
        "
        :class="{ active: rightSide || leftSide }"
      ></div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import ConsultRoom from "./ConsultRoom/ConsultRoom.vue";
import ProjectRoom from "./Project.vue";
import MeetingRoom from "./Meeting.vue";
import StudyRoom from "./Studyroom.vue";

export default {
  name: "main",
  data() {
    return {
      log: [],
      currentTab: "Main",
      tabs: ["Main", "ConsultRoom", "ProjectRoom", "MeetingRoom", "StudyRoom"],
    };
  },
  computed: {
    currentTabComponent() {
      return "tab-" + this.currentTab.toLowerCase();
    },
  },
  components: {
    ConsultRoom,
    ProjectRoom,
    MeetingRoom,
    StudyRoom,
  },
  methods: {
    getLog: function() {
      console.log("상담 기록 조회");
      this.log = [];
      axios
        .get(
          `http://localhost:8081/consultroom/consult?userId=ilove_13@naver.com`
        )
        .then((response) => {
          console.log(response.data);
          this.log.push(response.data);
        })
        .catch();
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/style/main-new.scss";
@import "@/assets/style/consultLog_modal.scss";
</style>
