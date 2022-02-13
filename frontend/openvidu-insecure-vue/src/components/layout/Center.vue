<template>
  <div class="main_center" style="height:100%">
    <div>
    <!-- 아이스 브레이킹 -->
    <div @click="icebraking">
      <a href="#icebraking"><img src="https://i.imgur.com/TsyLttq.png" style="width: 15%; margin-left: 500px;"/></a>
    </div>

    <!-- 아이스 브레이킹 모달창 -->
    <div id="icebraking" class="modal-window">
      <div>
        <a href="#" title="Close" class="modal-close">
            <b-icon icon="x-circle-fill" scale="2" variant="danger"></b-icon>
        </a>
        <div class="timer">
        
        </div>
        
      </div>
    </div>
    </div>
    <b-row>
      <b-col class="box" style="margin-right:10%">
        <button @click="setTab('StudyRoom')">Study</button>
      </b-col>
      <b-col class="box">
        <button @click="setTab('MeetingRoom')">Meeting</button>
      </b-col>
    </b-row>

    <b-row>
      <b-col class="box2" style="margin-right:10%">
        <button @click="setTab('ProjectRoom')">Project</button>
      </b-col>
      <b-col class="box2">
        <button @click="setTab('ConsultRoom')">Consult</button>
      </b-col>
    </b-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentTab: "Center",
    };
  },
  methods: {
    setTab: function(tab) {
      this.$emit("emitTab", tab);
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/style/icebraking.scss";
</style>

<style>
@import url(https://fonts.googleapis.com/css?family=Open+Sans);

.box {
  padding-top: 5%;
  padding-bottom: 7%;
  margin-left: 18%;
}
.box2 {
  padding-top: 18%;
  padding-bottom: 8%;
  margin-left: 18%;
}
</style>
