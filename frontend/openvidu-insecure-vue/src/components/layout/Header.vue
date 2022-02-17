<template>
  <div>
    <b-container fluid class="bv-example-row" style="height:120px; background-color:white;">
      <b-row>
        <b-col><img class="logo" src="@/assets/images/logo.png"/></b-col>
        <b-col cols="8"></b-col>
        <b-col>
          <b-button
            v-if="isLogin"
            variant="outline-warning"
            style="margin-top: 30px"
            @click="logout"
            >로그아웃</b-button
          >
          <b-button
            v-else
            variant="outline-warning"
            style="margin-top: 30px"
            href="#join"
            >로그인 또는 회원가입</b-button
          >
        </b-col>
      </b-row>
    </b-container>

    <div id="join" class="modal-window">
      <div>
        <a href="#" title="Close" class="modal-close">Close</a>
        <div style="text-align: center"><h1>3초만에 시작하기</h1></div>
        <div style="text-align: center; margin-top: 50px">
          <!-- 서버에 올릴 때 <a
            href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=e1a7f29f3266d25acd09415836291442&redirect_uri=http://localhost:8080/kakao"
          >-->
           <a
            href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=e1a7f29f3266d25acd09415836291442&redirect_uri=https://i6b202.p.ssafy.io/kakao"
          >
            <img alt="kakao-button" src="@/assets/images/kakao_login.png" />
          </a>
        </div>
        <!-- <div><small>Check out</small></div> -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  props: {
    isLogin: {
      type: Boolean,
      default: false,
    },
  },
  methods: {
    logout() {
      this.$emit("logout");
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/style/modal.scss";
</style>
