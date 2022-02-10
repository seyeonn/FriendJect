<template>
  <div>
    <!-- 세션이 없다면 (접속 안한 상태) -->
    <div id="join" v-if="!session">
      <div>
        <div class="cards" style="width:100%;">
          <article
            class="information [ card ]"
            style="width:40%; margin-right:10%"
          >
            <span class="tag">새 프로젝트 시작하기</span>
            <h2 class="title">팀 생성</h2>
            <p class="info" style="font-size:0.9rem;">
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

          <article class="information [ card ]" style="width:40%">
            <span class="tag">기존 프로젝트에 참여하기</span>
            <h2 class="title">팀 코드로 참가</h2>
            <p class="info" style="font-size:0.9rem;">
              팀원에게 받은 고유코드를 입력하세요!
            </p>

            <b-input-group size="md">
              <b-form-input
                v-model="mySessionId"
                class="form-control"
                type="text"
                required
              ></b-form-input>
            </b-input-group>
            <br />
            <button class="button" @click="joinSession">
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
          <h2 style="color:#3D3D3C; margin-left:10%">참여중인 프로젝트</h2>

          <!-- 이부분 접속 했던 이력을 for 문으로 나타내기 -->
          <div class="cards" style="width:100%;">
            <article class="information [ card ]">
              <dl class="details">
                <div>
                  <dt>#2123</dt>
                  <dd>[2팀] 콘푸로스트</dd>
                </div>
              </dl>
              <button
                class="button"
                style="background-color:#F9B225; color:white"
              >
                접속하기
              </button>
            </article>
          </div>
        </div>
      </div>
    </div>

    <!-- 세션이 있을때 (접속 한 상태) -->
    <div v-if="session">
      <div class="container" fluid>
        <div class="main">
          <div class="main-container">
            <!-- <img
            src="@/assets/images/main_day.png"
            style="width:100%; height:100%"
          /> -->

            <h5>팀코드 : {{ mySessionId }}</h5>
            <button @click="leaveSession" value="Leave session">
              세션 나가기
            </button>
            <input
              type="button"
              class="btn btn-info"
              @click="copyTeamCode"
              value="팀코드 복사"
            />
            <b-row>
              <!-- 방장 캠 -->
              <user-video
                :stream-manager="mainStreamManager"
                style="width:20%"
              ></user-video>

              <!-- 접속자 캠 -->
              <user-video
                style="width:20%"
                v-for="sub in subscribers"
                :key="sub.stream.connection.connectionId"
                :stream-manager="sub"
                @click.native="updateMainVideoStreamManager(sub)"
              />
            </b-row>

            <!-- 네비게이션 부분 -->
            <div>
              <button @click="exit">각 방 나가기</button>
              <button type="button" id="camera" @click="videoOnAndOff()">
                비디오 끄기
              </button>
              <button type="button" id="mute" @click="audioOnAndOff()">
                음소거
              </button>
              <keep-alive>
                <component :is="currentTab" v-on:emitTab="changeTab">
                </component>
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
            <div class="side-title">접속 중인 팀원</div>
            <user-list-row
              v-for="sub in subscribers"
              :key="sub.stream.connection.data"
              :stream-manager="sub"
              @click.native="updateMainVideoStreamManager(sub)"
            />
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
  </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "../components/UserVideo.vue";

axios.defaults.headers.post["Content-Type"] = "application/json";
const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";

import ConsultRoom from "./ConsultRoom/ConsultRoom.vue";
import ProjectRoom from "./Project.vue";
import MeetingRoom from "./Meeting.vue";
import StudyRoom from "./Studyroom.vue";
import Center from "../components/layout/Center.vue";
import UserListRow from "../components/UserListRow.vue";

export default {
  name: "main",
  data() {
    return {
      // openvidu start
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      mySessionId: "",
      teamName: "",
      // 이부분만 카카오 닉네임으로 설정해주시면 됩니다.
      myUserName: "Participant" + Math.floor(Math.random() * 100),
      // openvidu end

      log: [],
      currentTab: "Center",

      // 비디오, 마이크 온/오프
      videoEnabled: true,
      audioEnabled: true,

      subList: [],
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
    UserVideo,
    Center,
    UserListRow,
  },
  methods: {
    randomNumber: function() {
      var num = Math.floor(Math.random() * 10000) + 1000;
      this.mySessionId = num;
      this.joinSession();
    },
    changeTab: function(value) {
      this.currentTab = value;
    },
    getLog: function() {
      console.log("상담 기록 조회");
      this.log = [];
      axios
        .get(
          `http://localhost:9999/consultroom/consult?userId=ilove_13@naver.com`
        )
        .then((response) => {
          console.log(response.data);
          this.log.push(response.data);
        })
        .catch();
    },

    exit: function() {
      this.currentTab = "Center";
    },

    // openvidu methods
    joinSession: function() {
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();

      // --- Init a session ---
      this.session = this.OV.initSession();

      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });

      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then((token) => {
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---

            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.mainStreamManager = publisher;
            this.publisher = publisher;

            // --- Publish your stream ---

            this.session.publish(this.publisher);
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });

      window.addEventListener("beforeunload", this.leaveSession);
    },
    copyTeamCode() {
      const copyText = this.mySessionId;
      console.log(copyText);

      const text_team_code = document.createElement("input");
      text_team_code.value = copyText;
      document.body.append(text_team_code);

      text_team_code.select();
      document.execCommand("copy");
      text_team_code.remove();
    },
    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      window.removeEventListener("beforeunload", this.leaveSession);
    },

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },

    /**
     * --------------------------
     * SERVER-SIDE RESPONSIBILITY
     * --------------------------
     * These methods retrieve the mandatory user token from OpenVidu Server.
     * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
     * the API REST, openvidu-java-client or openvidu-node-client):
     *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
     *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
     *   3) The Connection.token must be consumed in Session.connect() method
     */

    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) =>
        this.createToken(sessionId)
      );
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
    createToken(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
            {},
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.token))
          .catch((error) => reject(error.response));
      });
    },
    videoOnAndOff() {
      this.videoEnabled = !this.videoEnabled;
      this.publisher.publishVideo(this.videoEnabled);
      if (!this.videoEnabled) {
        document.getElementById("camera").innerText = "비디오 켜기";
      } else {
        document.getElementById("camera").innerText = "비디오 끄기";
      }
    },
    audioOnAndOff() {
      this.audioEnabled = !this.audioEnabled;
      this.publisher.publishAudio(this.audioEnabled);
      if (!this.audioEnabled) {
        document.getElementById("mute").innerText = "음소거 해제";
      } else {
        document.getElementById("mute").innerText = "음소거";
      }
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/style/main-new.scss";
@import "@/assets/style/consultLog_modal.scss";
</style>
