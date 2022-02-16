<template>
  <b-container fluid style="background-color:white; height:100%">
    <b-row style="height:30%; padding-top:1%">
      <consult-top-navi />
    </b-row>

    <b-row>
      {{question}}
      질문자 : {{q_user}}
      답변자 : {{a_user}}
      <b-col cols="8"><consult-video /> </b-col>
      <b-col cols="4"><consult-chatting /> </b-col>
    </b-row>
  </b-container>
</template>

<script>
import ConsultChatting from "../components/consultroom/ConsultChatting.vue";
import ConsultTopNavi from "../components/consultroom/ConsultTopNavi.vue";
import ConsultVideo from "../components/consultroom/ConsultVideo.vue";
import { mapActions, mapState } from "vuex";

export default {
  computed: {
    ...mapState(["consultRoomMember", "question", "q_user","a_user"]),
  },
  data() {
    return {
      //question: 0,
    }
  },
  components: {
    
    ConsultChatting,
    ConsultTopNavi,
    ConsultVideo,
  },
  methods: {
    ...mapActions(["set_consult_room_member"]),
  }
};
</script>

<style></style>
