<template>
  <div>
    sfaddddddddddddddddddddddddddddddddd
    <article class="information [ card ]">
      <dl class="details">
        <div>
          <dt>dkdldl</dt>
          <dd>[2팀] 콘푸로스트</dd>
        </div>
      </dl>
      <router-link
        to="/room"
        style="background-color:#F9B225; color:white"
        tag="button"
        @click.native="InlineButtonClickHandler"
        >접속하기</router-link
      >
    </article>
  </div>
</template>

<script>
import { getOneTeam } from "@/api/center.js";

export default {
  name: "TeamListRow",
  data() {
    return {
      team: "",
    };
  },
  props: {
    id: Number,
    name: String,
    teamNumber: String,
  },
  created() {
    console.log(this.id);
  },
  methods: {
    InlineButtonClickHandler() {
      //vuex에 저장 (teamName, teamId, teamNumber)
      getOneTeam();
    },
  },
};
</script>

<style lang="scss" scope></style>
