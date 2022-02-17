<template>
  <tr class="list__tr" @click.prevent="downloadFile()">
    <td>{{ this.name }}</td>
    <td>
      {{ randomUploader() }}
    </td>
    <td>{{ this.returnSize }}</td>
    <td>{{ this.modifiedAt }}</td>
  </tr>
</template>

<script>
import { getFileInfo } from "@/api/projectroom.js";
import { timeForToday } from "@/assets/js/util/timePrint.js";
import { returnFileSize } from "@/assets/js/util/fileSize.js";

export default {
  name: "fileListRow",
  data() {
    return {
      file: "",
      returnSize: "",
      modifiedAt: "",
      uploader: "",
    };
  },
  props: {
    id: String,
    name: String,
    type: String,
    size: Number,
    modifiedDate: String,
  },
  created() {
    this.modifiedAt = timeForToday(this.modifiedDate);
    this.returnSize = returnFileSize(this.size);
  },
  methods: {
    randomUploader() {
      var arr = new Array("정채은", "김주향", "안세연", "이소라", "송진섭");
      return arr[Math.floor(Math.random() * arr.length)];
    },
    downloadFile() {
      getFileInfo(
        this.id,
        (response) => {
          this.forceFileDownload(response);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    forceFileDownload(response) {
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement("a");
      link.href = url;
      link.setAttribute("download", this.name);
      document.body.appendChild(link);
      link.click();
    },
  },
  filters: {
    capitalize(v) {
      if (!v) return "";
      v = v.toString();
      return v.charAt(0).toUpperCase() + v.slice(1);
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/style/projectroom/fileList.scss";
</style>
