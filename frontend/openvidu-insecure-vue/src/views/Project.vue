<template>
  <b-container fluid class="p-1" style="background-color:white; height:100%">
    <div>
      <b-tabs content-class="m-3" fill>
        <b-tab
          title="메모보드"
          active
          class="shadow-sm p-3 mb-5 bg-white rounded"
          ><memo-board></memo-board
        ></b-tab>
        <b-tab title="자료실"><file-list></file-list></b-tab>
      </b-tabs>
    </div>
  </b-container>
</template>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
import FileList from "../components/projectroom/FileList.vue";
import FileUpload from "@/components/projectroom/FileUpload.vue";
import MemoBoard from "@/components/projectroom/MemoBoard.vue";

export default {
  name: "projectroom",
  components: { FileList, FileUpload, MemoBoard },
};
</script>
