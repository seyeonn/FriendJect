<template>
  <b-container fluid style="background-color:white; height:100%">
    <div>
      <b-tabs content-class="mt-3" fill>
        <b-tab title="자료실" active><file-list></file-list></b-tab>
        <b-tab title="메모보드"><memo-board></memo-board></b-tab>
      </b-tabs>
    </div>
  </b-container>
</template>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
import FileList from "../components/projectroom/FileList.vue";
import FileUpload from "@/components/projectroom/FileUpload.vue";
import MemoBoard from "@/components/projectroom/MemoBoard.vue";
import LeftBar from "../components/layout/LeftTempBar.vue";

export default {
  name: "projectroom",
  components: { FileList, FileUpload, LeftBar, MemoBoard },
};
</script>
