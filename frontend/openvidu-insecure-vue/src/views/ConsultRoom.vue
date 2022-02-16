<template>
  <b-container fluid style="background-color:white; height:100%">
    <b-row>
      <b-col cols="7"><consult-top-navi v-on:upstream="upstream"/> </b-col>
      <b-col cols="5">
        <consult-chatting />
      </b-col>

    </b-row>
  </b-container>
</template>

<script>
import ConsultChatting from "../components/consultroom/ConsultChatting.vue";
import ConsultTopNavi from "../components/consultroom/ConsultTopNavi.vue";

export default {
  components: {
    ConsultChatting,
    ConsultTopNavi,
  },
  methods: {
    upstream() {
      console.log("상위호출 완료");
      this.$emit("upstream");
    }
  }
};
</script>

<style></style>
