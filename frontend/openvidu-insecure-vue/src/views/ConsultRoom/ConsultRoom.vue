<template>
  <b-container fluid style="background-color:white; height:100%">
    <b-row style="height:30%; padding-top:1%">
      <consult-top-navi />
    </b-row>

    <b-row>
      <b-col cols="8"><consult-video/> </b-col>
      <b-col cols="4"><consult-chatting/> </b-col>
    </b-row>
  </b-container>
</template>

<script>
import ConsultChatting from './ConsultChatting.vue';
import ConsultTopNavi from "./ConsultTopNavi.vue";
import ConsultVideo from "./ConsultVideo.vue";

export default {
  components: {
    ConsultChatting,
    ConsultTopNavi,
    ConsultVideo,
  },
};
</script>

<style></style>
