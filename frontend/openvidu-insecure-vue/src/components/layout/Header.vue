<template>
  <div>
    <b-container fluid class="bv-example-row">
      <b-row>
        <b-col><img class="logo" src="@/assets/images/logo.png"/></b-col>
        <b-col cols="8"></b-col>
        <b-col>
          <b-button
            variant="outline-warning"
            style="margin-top: 50px"
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
          <a
            href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=e1a7f29f3266d25acd09415836291442&redirect_uri=http://i6b202.p.ssafy.io:8080/kakao"
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
  methods: {
    login: function() {
      //모달 닫히는게 필요
      this.$router.push("join");
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/style/modal.scss";
</style>
