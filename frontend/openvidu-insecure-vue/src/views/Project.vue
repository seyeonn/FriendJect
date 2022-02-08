<template>
  <b-container fluid style="background-color:white; height:100%">
    <b-row style="height:30%; padding-top:1%"> </b-row>

    <b-row>
      <b-col cols="8"><file-list /> </b-col>
      <b-col cols="4"><file-upload /> </b-col>
    </b-row>
  </b-container>
</template>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
import FileList from "../components/projectroom/FileList.vue";
import FileUpload from "@/components/projectroom/FileUpload.vue";

export default {
  components: { FileList, FileUpload },
};
</script>
