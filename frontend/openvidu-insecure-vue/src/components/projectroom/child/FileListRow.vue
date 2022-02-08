<template>
  <tr class="list__tr" @click.prevent="downloadFile()">
    <td>{{ this.name }}</td>
    <td>{{ this.type }}</td>
    <td>{{ this.returnSize }}</td>
    <td>{{ this.createdAt }}</td>
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
      createdAt: "",
    };
  },
  props: {
    id: String,
    name: String,
    type: String,
    size: Number,
    createdDate: String,
  },
  created() {
    this.createdAt = timeForToday(this.createdDate);
    this.returnSize = returnFileSize(this.size);
  },

  methods: {
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
