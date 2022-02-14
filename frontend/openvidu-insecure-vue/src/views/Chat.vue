<template>
  <!-- 세션이 있을때 (접속 한 상태) -->
  <!-- chat -->
  <div class="fabs blue-grey">
    <div :class="isActive ? 'is-visible' : ''" class="chat">
      <div class="chat_header">
        <span id="chat_head">Live Chat</span>
      </div>
      <div id="chat_converse" class="chat_converse">
        <div v-for="chat in chatList" :key="chat.id">
          <span
            :class="
              chat.streamId == session.connection.connectionId
                ? 'chat_msg_item chat_msg_item_user'
                : 'chat_msg_item chat_msg_item_admin'
            "
          >
            <div class="chat_avatar">
              <i class="zmdi" style="font-size:15px;">{{ chat.nickname }}</i>
            </div>
            {{ chat.message }}</span
          >
        </div>
      </div>
      <div class="fab_field">
        <a
          :class="isActive ? 'is-visible' : ''"
          class="fab"
          id="fab_send"
          @click="messageSend"
        >
          <span
            class="ink animate"
            style="height: 40px; width: 40px; top: 1px; left: -10px;"
          ></span
          ><i class="zmdi zmdi-mail-send"></i
        ></a>
        <b-textarea
          id="chatSend"
          name="chat_message"
          placeholder="Write a message"
          class="chat_field chat_message"
        />
      </div>
    </div>

    <a id="prime" class="fab" @click="chatShow()"
      ><i class="prime zmdi zmdi-plus"></i
    ></a>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";

axios.defaults.headers.post["Content-Type"] = "application/json";

export default {
  name: "chat",
  data() {
    return {
      // openvidu start
      OV: undefined,
      mySessionId: "",
      message: "",
      messageList: [],
      // openvidu end
      log: [],
      isActive: false,
      userType: false,
    };
  },
  props: {
    session: {
      type: Object,
      default: undefined,
    },
    teamName: {
      type: String,
      default: "",
    },
    chatList: {
      type: Array,
      default: undefined,
    },
  },
  computed: {
    // 카카오 닉네임
    ...mapState(["myUserName"]),
  },
  methods: {
    messageSend() {
      const nowMessage = document.querySelector("#chatSend").value;
      const data = {
        message: nowMessage,
        nickname: this.myUserName,
        streamId: this.session.connection.connectionId,
      };
      document.querySelector("#chatSend").value = "";

      // 메시지 보내기
      this.session
        .signal({
          data: JSON.stringify(data), // Any string (optional)
          to: [], // Array of Connection objects (optional. Broadcast to everyone if empty)
          type: "my-chat", // The type of message (optional)
        })
        .then(() => {
          console.log("Message successfully sent");
        })
        .catch((error) => {
          console.error(error);
        });
      this.message = "";
    },
    // chat_board show
    chatShow() {
      this.isActive = !this.isActive;
    },
    chatPush(data) {
      let messageList = this.messageList;
      messageList.push({
        message: data.message,
        nickname: data.nickname,
        streamId: data.streamId,
      });
      console.log(messageList);
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/style/chat.scss";
</style>
