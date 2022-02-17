<template>
  <div>
    <!-- <h1>kakao Redirect 화면입니다.</h1> -->
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import axios from "axios";
import { SIGNUP_URL } from "@/config";

export default {
  computed: {
    ...mapState(["accessToken"]),
  },
  methods: {
    ...mapActions(["setUserinfo"]),
    kako_api: function() {
      axios
        .post(SIGNUP_URL, {
          code: this.$route.query.code,
        })
        .then((res) => {
          this.setUserinfo({
            ...res.data,
          });
          // 올바른 인적사항이 response 되면 accessToken 저장요청
          this.$store.commit("setToken", res.data.accessToken);
          this.$store.commit("setKakaoId", res.data.kakaoId);
          this.$store.commit("setUserName", res.data.nickName);
          this.$store.commit("SET_USEREMAIL", res.data.userEmail);
          this.$store.commit("SET_PROFILEURL", res.data.profileUrl);
          this.$store.commit("setUserId", res.data.id);

          this.$router.replace("/main");
        })
        .catch((error) => {
          console.log(error);
          this.$router.replace("/");
        });
    },
  },
  created() {
    this.kako_api();
    // console.log(this.$route.query.code);
    // this.$router.replace("/join");
  },
};
</script>

<style scoped>
@import "../assets/style/macbook.css";
@import "../assets/style/typing.css";
</style>
