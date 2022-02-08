<template>
  <b-container fluid style="background-color:white; height:100%">
    <b-row>
      <b-col cols="2" sm="3">
        <left-bar />
      </b-col>
      <b-col cols="10" sm="8">
        <b-row style="height:30%; padding-top:1%"> Openvidu cameras </b-row>
        <router-view></router-view>
      </b-col>
    </b-row>
  </b-container>
</template>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
import FileList from "../components/projectroom/FileList.vue";
import FileUpload from "@/components/projectroom/FileUpload.vue";
import LeftBar from "../components/layout/LeftTempBar.vue";

export default {
  components: { FileList, FileUpload, LeftBar },
};
</script>
