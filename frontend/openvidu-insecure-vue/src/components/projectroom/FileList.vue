<template>
  <b-row class="m-5">
    <b-col cols="7 mt-5 ml-5"
      ><div id="app" class="grid p-8">
        <img
          src="https://i.imgur.com/naGbUBF.png"
          style="width: 150px; hieght: 70px"
        />
        <table>
          <thead>
            <tr>
              <th :class="{ sorted: sort === 'name' }" @click="sortBy('name')">
                Name
              </th>
              <th :class="{ sorted: sort === 'type' }" @click="sortBy('type')">
                Uploader
              </th>
              <th :class="{ sorted: sort === 'size' }" @click="sortBy('size')">
                Size
              </th>
              <th
                :class="{ sorted: sort === 'updatedAt' }"
                @click="sortBy('updatedAt')"
              >
                Updated
              </th>
            </tr>
          </thead>
          <tbody>
            <!-- <file-list-row
              v-for="(file, index) in sortedFiles()"
              :key="index"
              v-bind="file"
            ></file-list-row> -->
            <tr
              class="list__tr"
              @click.prevent="downloadFile()"
              v-for="(file, index) in files"
              :key="index"
            >
              <td>{{ file.name }}</td>
              <td>
                {{ randomUploader() }}
              </td>
              <td>{{ file.returnSize }}</td>
              <td>{{ file.modifiedAt }}</td>
            </tr>
          </tbody>
        </table>

        <div class="controls">
          <button class="btn btn-sm" :disabled="!hasPage(-1)" @click="prevPage">
            ←
          </button>
          <button class="btn btn-sm" :disabled="!hasPage(1)" @click="nextPage">
            →
          </button>
        </div>
      </div>
    </b-col>
    <b-col cols="4 p-5 center" class="rounded border border-warning m-4">
      <img
        src="https://i.imgur.com/J7TRws3.png"
        style="width: 150px; hieght: 70px"
      />
      <!-- <h4><b>파일 올리기</b>👇</h4> -->
      <file-upload />
    </b-col>
  </b-row>
</template>
<script>
// import FileListRow from "@/components/projectroom/child/FileListRow";
import FileUpload from "@/components/projectroom/FileUpload.vue";
import { getFileList } from "@/api/projectroom.js";
import { getFileInfo } from "@/api/projectroom.js";
import { timeForToday } from "@/assets/js/util/timePrint.js";
import { returnFileSize } from "@/assets/js/util/fileSize.js";

export default {
  name: "fileList",
  components: {
    // FileListRow,
    FileUpload,
  },
  data() {
    return {
      files: [],
      sort: "updatedAt",
      sortDir: "desc",
      page: 0,
      pageSize: 10,
      file: "",
      returnSize: "",
      modifiedAt: "",
      uploader: "",
    };
  },
  created() {
    this.modifiedAt = timeForToday(this.modifiedDate);
    this.returnSize = returnFileSize(this.size);
    getFileList(
      (response) => {
        console.log(response.data.data);
        this.files = response.data.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    // sortedFiles() {
    //   return this.files
    //     .sort((a, b) => {
    //       let dir = this.sortDir === "asc" ? 1 : -1;
    //       if (a[this.sort] < b[this.sort]) {
    //         return -1 * dir;
    //       } else if (a[this.sort] > b[this.sort]) {
    //         return 1 * dir;
    //       } else {
    //         return 0;
    //       }
    //     })
    //     .filter((row, idx) => {
    //       let s = this.page * this.pageSize;
    //       let e = (this.page + 1) * this.pageSize;
    //       return idx >= s && idx < e;
    //     });
    // },
    sortBy(s) {
      if (s === this.sort) {
        this.sortDir = this.sortDir === "asc" ? "desc" : "asc";
      } else {
        this.sortDir = "asc";
      }
      this.sort = s;
    },
    // isActiveSort(s) {
    //   return this.sort === s;
    // },
    hasPage(dir) {
      if (dir === -1 && this.page > 0) return true;
      if (dir === 1 && (this.page + 1) * this.pageSize < this.files.length)
        return true;
      return false;
    },
    prevPage() {
      if (this.hasPage(-1)) this.page--;
    },
    nextPage() {
      if (this.hasPage(1)) this.page++;
    },
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
  computed: {
    // pageSizeModel: {
    //   get() {
    //     return this.pageSize;
    //   },
    //   set(v) {
    //     this.pageSize = v;
    //     this.page = 0;
    //   },
    // },
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/style/projectroom/fileList.scss";
</style>
