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
    this.kako_api();
    this.$router.replace("/join");
  },
};
</script>

<style scoped>
@import "../assets/style/macbook.css";
@import "../assets/style/typing.css";
</style>
