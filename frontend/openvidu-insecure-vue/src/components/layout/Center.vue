<template>
  <div class="main_center" style="height:100%">
    <h1>test</h1>
    <b-row>
      <b-col class="box" style="margin-right:10%">
        <button @click="setTab('StudyRoom')">Study</button>
      </b-col>
      <b-col class="box">
        <button @click="setTab('MeetingRoom')">Meeting</button>
      </b-col>
    </b-row>

    <b-row>
      <b-col class="box" style="margin-right:10%">
        <button @click="setTab('ProjectRoom')">Project</button>
      </b-col>
      <b-col class="box">
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

<style>
.box {
  padding-top: 18%;
  padding-bottom: 8%;
  margin-left: 18%;
}
</style>
