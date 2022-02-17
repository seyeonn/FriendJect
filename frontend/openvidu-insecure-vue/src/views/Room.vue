<template>
  <div class="container" fluid>
    <div class="main">
      <div class="main-container">
        <div class="cursor">
          <img style="width: 200px;" :src="profileUrl" alt="my_minime" />
        </div>
        <!-- <img
            src="@/assets/images/main_day.png"
            style="width:100%; height:100%"
          /> -->
        <h4>팀 코드 : {{ this.$store.state.teamNumber }}</h4>
        <h4>팀 네임 : {{ this.$store.state.teamName }}</h4>
        <button @click="leaveSession" value="Leave session">세션 나가기</button>
        <input
          type="button"
          class="btn btn-info"
          @click="copyTeamCode"
          value="팀코드 복사"
        />
        <b-row id="roomHeader">
          <!-- 방장 캠 -->

          <user-video
            :stream-manager="mainStreamManager"
            style="width: 200px"
          ></user-video>
          <!-- 접속자 캠 -->
          <user-video
            style="width: 200px"
            v-for="sub in subscribers"
            :key="sub.stream.connection.connectionId"
            :stream-manager="sub"
            @click.native="updateMainVideoStreamManager(sub)"
          />
        </b-row>
        <!-- 네비게이션 부분 -->

        <div style="position: relative; z-index: 1;">
          <router-link :to="'/room'"
            ><img
              src="https://i.imgur.com/2QnSMDT.png"
              style="
        width: 170px;
        height: 60px;
        "
          /></router-link>
          <button type="button" id="camera" @click="videoOnAndOff()">
            비디오 끄기
          </button>
          <button type="button" id="mute" @click="audioOnAndOff()">
            음소거
          </button>
          <!-- 각 방 들어가는 부분 -->
          <router-view v-on:upstream="upstream"> </router-view>
        </div>

        <!-- 화면공유 캠 -->
        <user-video
          :stream-manager="screenShare"
          style="width:600px; top:-500px; left:1%; position: relative; z-index: 2;"
        >
        </user-video>
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
        <span class="account-user">{{ this.myUserName }}</span>
      </div>

      <span class="account-user">
        <img :src="profileUrl" alt="" class="account-profile" />
        <a href="#profileModal">
          <b-icon-pencil-square></b-icon-pencil-square
        ></a>
      </span>
      <div style="text-align: center" v-on:click="getLog">
        <a href="#consultLog">
          <img src="https://i.imgur.com/TomnxTd.png" style="width: 160px;" />
        </a>
      </div>

      <div style="text-align: center">
        <!-- 투표는 openvidu의 브로드캐스팅 참고해야할듯.. -->
        <a href="#vot">
          <img src="https://i.imgur.com/BPgngsm.png" style="width: 160px;" />
        </a>
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

    <!-- 상담 모달 -->

    <div id="consultLog" class="modal-consult">
      <div style="width:70%">
        <a href="#" title="Close" class="modal-close">
          <b-icon icon="x-circle-fill" scale="2" variant="danger"></b-icon>
        </a>

        <img
          src="https://i.imgur.com/reE2Tgo.png"
          style="
        width: 520px;
        height: 200px;
        margin-left: -50;
        margin-top: -20px;
        "
        />
        <!-- <div><small>Check out</small></div> -->

        <div>
          <b-table striped hover :items="consult_log"></b-table>
        </div>

        <!-- <b-list-group>
              <b-list-group-item href="#" active class="flex-column align-items-start">
                <div class="d-flex w-100 justify-content-between">
                  <h5 class="mb-1">List group item heading</h5>
                  <small>3 days ago</small>
                </div>

                <p class="mb-1">
                  Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.
                </p>

                <small>Donec id elit non mi porta.</small>
              </b-list-group-item>
            </b-list-group> -->
      </div>
    </div>

    <!-- 투표 모달 -->
    <div id="vot" class="modal-vot">
      <div style="width: 40%">
        <a href="#" title="Close" class="modal-close">
          <b-icon icon="x-circle-fill" scale="2" variant="danger"></b-icon>
        </a>

        <img
          src="https://i.imgur.com/H6aJjTA.png"
          style="width: 400px;
        height: 150px;
        margin-left: -50px;
        margin-top: -20px;
        "
        />
        <!-- <div><small>Check out</small></div> -->
        <div>
          질문
          <input
            v-model="votTitle"
            type="text"
            class="form-control"
            style="width: 80%"
            placeholder="투표 질문을 입력해주세요."
          />
          <br />
          <!-- <p>투표 제목: "{{ votTitle }}"</p> -->
        </div>

        <button @click="add" class="w-btn w-btn-indigo">답변 추가</button>

        <div v-for="item in items" :key="item.idx">
          {{ item.index }}. <input v-model="item.value" class="voteinput" />
          <button @click="del" class="delb">
            <b-icon icon="x-circle-fill" scale="2" variant="white"></b-icon>
          </button>
        </div>

        <!-- {{ items }} -->

        <div>
          <b-form-checkbox v-model="checked" name="check-button" switch>
            강제 투표
            <!-- <b>(Checked: {{ checked }})</b> -->
          </b-form-checkbox>
        </div>

        <button
          @click="
            play(
              'http://soundbible.com/mp3/Air Plane Ding-SoundBible.com-496729130.mp3'
            )
          "
          class="w-btn w-btn-green"
        >
          audioPlayer
        </button>

        <button class="w-btn w-btn-green2" @click="cvote">투표 생성하기</button>
      </div>
    </div>
    <!-- 프로필 편집 모달 -->
    <div id="profileModal" class="modal-profile">
      <div>
        <a href="#" title="Close" class="modal-close">
          <b-icon icon="x-circle-fill" scale="2" variant="danger"></b-icon>
        </a>

        <img
          src="https://i.imgur.com/ExHH7bv.png"
          style="
        width: 300px;
        height: 110px;
        margin-top: -20px;
        margin-bottom: 10px;
        "
        />
        <img
          :src="profileUrl"
          alt="profile_img"
          class="user-img"
          @error="replaceImg"
        />
        <input id="input" @change="onInputImage" type="file" accept="image/*" />
        <button
          class="btn"
          @click="onChangProfile(userEmail)"
          style="margin-top: 10px"
        >
          변경
        </button>
        <button
          class="btn"
          @click="onDeleteProfile(userEmail)"
          style="margin-top: 10px"
        >
          삭제
        </button>
        <button class="btn" @click="onInitProfile" style="margin-top: 10px">
          초기화
        </button>
      </div>
    </div>
    <!-- chat -->
    <chat :session="session" :team-name="teamName" :chat-list="chatList"></chat>
  </div>
</template>

<script>
import axios from "axios";
import jquery from "jquery";
import { API_BASE_URL } from "@/config";
import { getConsultLogList } from "@/api/consultroom.js";
import { changProfile } from "@/api/room.js";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "../components/UserVideo.vue";

axios.defaults.headers.post["Content-Type"] = "application/json";
const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";

import Chat from "./Chat.vue";
import UserListRow from "../components/UserListRow.vue";
import { mapState, mapActions } from "vuex";

export default {
  name: "room",

  data() {
    return {
      // openvidu start
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      screenShare: undefined,
      publisher: undefined,
      subscribers: [],
      mySessionId: "",
      teamCode: "",
      message: "",
      // 이부분만 카카오 닉네임으로 설정해주시면 됩니다.
      //myUserName: "Participant" + Math.floor(Math.random() * 100),

      //탭 전환
      currentTab: "Center",

      //투표 설정
      votTitle: "",
      items: [],
      items_cnt: 0,
      checked: false,
      // 채팅 리스트
      chatList: [],
      // 비디오, 마이크 온/오프
      videoEnabled: true,
      audioEnabled: true,

      subList: [],
    };
  },
  computed: {
    ...mapState(["consult_log", "store_sessionId"]),
    ...mapState(["myUserName"]),
    ...mapState(["kakaoId"]),
    ...mapState(["profileUrl"]),
    ...mapState(["userEmail"]),
    ...mapState(["teamName"]),
    currentTabComponent() {
      return "tab-" + this.currentTab.toLowerCase();
    },
  },
  created() {
    console.log(this.$route.params.code);
  },
  components: {
    Chat,
    UserVideo,
    UserListRow,
  },
  mounted() {
    this.joinSession();
    jquery(document).ready(function() {
      jquery(document).mousemove(function(e) {
        var mouseX = e.pageX;
        var mouseY = e.pageY;

        jquery(".cursor").css({
          left: mouseX + "px",
          top: mouseY + "px",
        });
      });
    });
  },
  methods: {
    ...mapActions([
      "set_consult_room_member",
      "set_consult_question",
      "push_sub",
      "set_sesstion_id",
      "setProfileUrl",
    ]),
    //최상위에서 비디오를 내려주는 코드입니다. 비디오, 화면공유 포함.
    upstream: function() {
      console.log("최상위 도달");
      this.screenVideo();
    },
    play: function(sound) {
      if (sound) {
        var audio = new Audio(sound);
        audio.play();
      }
    },

    add: function() {
      this.items_cnt++;
      this.items.push({ index: this.items_cnt, value: "" });
    },
    del: function() {
      if (this.items.length > 0) {
        this.items.pop();
        this.items_cnt--;
        //arrInputValue.pop();
      }
    },
    randomNumber: function() {
      var num = Math.floor(Math.random() * 10000) + 1000;
      this.mySessionId = num;
      this.joinSession;
    },
    changeTab: function(value) {
      this.currentTab = value;
      if (this.currentTab == "Con102sultRoom") {
        //여기서 카운트를 늘려줘야함, 누른사람 아이디 넘겨주기.
        this.set_consult_question("질문은 이겁니다.");
        this.set_consult_room_member(this.myUserName);
        alert("접속");
      }
    },
    getLog: function() {
      console.log("상담 기록 조회");
      this.log = [];
      // TODO: userId 불러와야함!!
      var userId = "ilove_13@naver.com";
      getConsultLogList(
        userId,
        (response) => {
          console.log(response.data);
          this.log.push(response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    exit: function() {
      this.currentTab = "Center";
    },

    pushMessage(message) {
      this.chatList.push(JSON.parse(message));
    },

    // openvidu methods
    joinSession: function() {
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();

      this.set_ov(this.OV);
      // --- Init a session ---
      this.session = this.OV.initSession();

      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });

      // this.session
      //   .signal({
      //     data: "My custom message", // Any string (optional)
      //     to: [], // Array of Connection objects (optional. Broadcast to everyone if empty)
      //     type: "my-chat", // The type of message (optional)
      //   })
      //   .then(() => {
      //     console.log("메세지 보이냐??");
      //   })
      //   .catch((error) => {
      //     console.error(error);
      //   });

      // this.session.on("signal", (event) => {
      //   alert("??");
      //   console.log(event.data); // Message
      //   console.log(event.from); // Connection object of the sender
      //   console.log(event.type); // The type of message
      // });
      // // On every Stream destroyed...
      // this.session.on("streamDestroyed", ({ stream }) => {
      //   const index = this.subscribers.indexOf(stream.streamManager, 0);
      //   if (index >= 0) {
      //     this.subscribers.splice(index, 1);
      //   }
      // });
      // // On every asynchronous exception...
      // this.session.on("exception", ({ exception }) => {
      //   console.warn(exception);
      // });

      // --- Connect to the session with a valid user token ---

      // 메세지 수신(보통 세션 커넥트 전)
      this.session.on("signal:my-chat", (event) => {
        this.pushMessage(event.data);
      });
      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then((token) => {
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            this.set_session(this.session);
            this.sessionId = this.mySessionId;
            // --- Get your own camera stream with the desired properties ---
            // var path = (location.pathname.slice(-1) == "/" ? location.pathname : location.pathname + "/");
            //     window.history.pushState("", "", path + '#' + this.store_sessionId);

            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "200x150", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.session
              .signal({
                data: "My custom message", // Any string (optional)
                to: [], // Array of Connection objects (optional. Broadcast to everyone if empty)
                type: "my-chat", // The type of message (optional)
              })
              .then(() => {
                console.log("메세지 보이냐??");
              })
              .catch((error) => {
                console.error(error);
              });

            this.session.on("signal", (event) => {
              //   alert("브로드캐스팅 테스트. 투표하세요!!!");
              console.log(event.data); // Message
              console.log(event.from); // Connection object of the sender
              console.log(event.type); // The type of message
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

            this.mainStreamManager = publisher;
            this.publisher = publisher;

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

    screenVideo() {
      this.screenShare = !this.screenShare;

      let publisher = this.OV.initPublisher(undefined, {
        audioSource: undefined, // The source of audio. If undefined default microphone
        videoSource: "screen", // The source of video. If undefined default webcam
        publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
        publishVideo: true, // Whether you want to start publishing with your video enabled or not
        resolution: "200x150", // The resolution of your video
        frameRate: 30, // The frame rate of your video
        insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
        mirror: false, // Whether to mirror your local video or not
      });

      this.screenShare = publisher;
      //this.publisher = publisher;
      this.session.publish(this.screenShare);
    },

    copyTeamCode() {
      const copyText = this.$route.params.code;
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
      this.screenShare = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;
      localStorage.removeItem("teamId");
      window.removeEventListener("beforeunload", this.leaveSession);
      this.$router.push({ name: "main" });
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
    //프로필 이미지
    replaceImg: function(event) {
      event.target.src =
        "https://img.freepik.com/free-icon/x-symbol_318-1407.jpg";
    },
    onInputImage: function(file) {
      this.image = file.target.files[0];
      console.log(this.image);
    },
    onChangProfile: function(email) {
      const imgfrm = new FormData();
      imgfrm.append("filename", this.image);
      changProfile(
        email,
        imgfrm,
        (res) => {
          console.log(res.data + "프로필 변경");
          this.setProfileUrl(
            API_BASE_URL + "profile/image/" + email + "/" + this.image.name
          );
        },
        (err) => {
          console.log(err);
        }
      );
    },
    onDeleteProfile: function(email) {
      axios
        .patch(`http://localhost:8081/api/profile/empty/${email}`)
        .then((res) => {
          console.log(res);
          this.setUserinfo;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    onInitProfile: function() {
      this.$router.push({ name: "minime" });
    },
    cvote() {
      alert("투표 생성이 완료되었습니다.");
    }
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/style/main-new.scss";
@import "@/assets/style/consultLog_modal.scss";
@import "@/assets/style/join_room.scss";

video {
  width: 100px;
}

button {
  background-color: rgba(234, 237, 240, 0.842);
  color: #f9b225;
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
  margin-bottom: 5px;
}
.cursor {
  position: absolute;
  top: 0px;
  left: 0px;
  z-index: 9999;
  width: 250px;
  height: 100px;
  transform: translate(-50%, -50%);
}
</style>
