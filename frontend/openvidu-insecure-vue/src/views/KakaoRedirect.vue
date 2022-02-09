<template>
  <div>
    <h1>kakao Redirect 화면입니다.</h1>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import axios from "axios";

export default {
  components: {
    ...mapState(["accessToken"]),
  },
  methods: {
    ...mapActions(["setUserinfo"]),
    kako_api: function() {
      axios
        .post("http://localhost:8081/login", {
          code: this.$route.query.code,
        })
        .then((res) => {
          alert("DB에 전송완료");
          this.setUserinfo({
            ...res.data,
          });
          console.log(this.$store.getters.isLogin);
          if (this.$store.getters.isLogin == true) {
            this.$store.commit("setToken", res.data.accessToken);
          }
          console.log(res.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {
    //this.kako_api();
    // 여기서 choice , 즉 본인의 방을 생성하거나 참가하는 페이지로 이동합니다.
    // 이때 카카오에서 받은 닉네임을 props 로 choice.vue 에 넘겨주세요.
    // choice 에서 넘겨받은 닉네임을 participant 에 input 값으로 지정해주세요.
    // 그러면 자동으로 닉네임 + 팀코드로 방에 접속됩니다.

    console.log(this.$route.query.code);
    //this.$router.replace("/choice");
    this.$router.replace("/join");
  },
};
</script>

<style scoped>
@import "../assets/style/macbook.css";
@import "../assets/style/typing.css";
</style>
