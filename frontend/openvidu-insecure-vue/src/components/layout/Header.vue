<template>
  <div>
    <b-container fluid class="bv-example-row">
      <b-row>
        <b-col><img class="logo" src="@/assets/images/logo.png"/></b-col>
        <b-col cols="8"></b-col>
        <b-col>
          <b-button
            variant="outline-warning"
            style="margin-top:50px"
            href="#join"
            >로그인 또는 회원가입</b-button
          >
        </b-col>
      </b-row>
    </b-container>

    <div id="join" class="modal-window">
      <div>
        <a href="#" title="Close" class="modal-close">Close</a>
        <div style="text-align:center;"><h1>3초만에 시작하기</h1></div>
        <div>
          여기에 카카오톡으로 로그인 연결
          <button v-on:click="login"> 로그인 됐다고 가정 </button>
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
      this.$router.push('choice'); 
    }
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/style/modal.scss";
</style>
