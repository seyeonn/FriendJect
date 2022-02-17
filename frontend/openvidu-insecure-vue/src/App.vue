<template>
  <!-- 메인이 되는 부분 -->
  <div>
    <!-- 반응형 메인 이미지 코드!! 주석 처리 되었으나, 재사용 할거같으니 지우지 마세용!!! -->
    <!-- <b-img :src="require('./assets/images/main.png')" fluid-gro></b-img> -->
    <!-- 자주 바뀌는 영역 -->

    <Header :is-login="isLogin" @logout="logout" />
    <body>
      <router-view></router-view>
    </body>
  </div>
</template>

<script>
import Header from "./components/layout/Header.vue";
import { mapState, mapGetters } from "vuex";

export default {
  name: "App",
  data() {
    return {};
  },
  components: {
   Header,
  },
  computed: {
    ...mapState(["accessToken"]),
    ...mapGetters(["isLogin"]),
  },
  methods: {
    logout() {
      this.$store.commit("deleteToken");
      this.$router.replace("/");
    },
  },
  created() {
    const accessToken = localStorage.getItem("accessToken") || "";
    const kakaoId = localStorage.getItem("kakaoId") || "";
    const userName = localStorage.getItem("myUserName") || "";
    const profile = localStorage.getItem("profileUrl") || "";
    const userEmail = localStorage.getItem("userEmail") || "";
    this.$store.commit("setToken", accessToken);
    this.$store.commit("setKakaoId", kakaoId);
    this.$store.commit("setUserName", userName);
    this.$store.commit("SET_PROFILEURL", profile);
    this.$store.commit("SET_USEREMAIL", userEmail);
  },
};
</script>

<style>
html,
body {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}
</style>
