<template>
  <div class="user">
    <!-- user profile_url api로 추가 예정 -->
    <img src="https://randomuser.me/api/portraits/men/1.jpg" class="user-img" />
    <div class="username">
      {{ clientData }}
    </div>
    <div class="userstate">
      {{ stateData }}
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { API_BASE_URL } from "@/config";

export default {
  name: "UserListRow",

  props: {
    streamManager: Object,
  },
  data() {
    return {
      stateData: "",
    };
  },
  computed: {
    clientData() {
      const { clientData } = this.getConnectionData();
      return clientData;
    },
  },
  methods: {
    getConnectionData() {
      const { connection } = this.streamManager.stream;
      return JSON.parse(connection.data);
    },
  },
  mounted: {
    getStateData: function() {
      axios
        .get(API_BASE_URL + "process/")
        .then((res) => {
          this.stateData = res;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/style/main-new.scss";
</style>
