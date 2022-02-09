<template>
  <b-row>
    <b-col cols="8"
      ><div id="app" class="grid">
        <h1><b>File Table</b> 📄</h1>
        <label>Files per page</label>
        <select v-model="pageSizeModel">
          <option value="3">3</option>
          <option value="5">5</option>
          <option value="10">10</option>
          <option value="25">25</option>
        </select>
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
            <file-list-row
              v-for="(file, index) in sortedFiles()"
              :key="index"
              v-bind="file"
            ></file-list-row>
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
    <b-col cols="4"><file-upload /> </b-col>
  </b-row>
</template>
<script>
import FileListRow from "@/components/projectroom/child/FileListRow";
import FileUpload from "@/components/projectroom/FileUpload.vue";
import { getFileList } from "@/api/projectroom.js";

export default {
  name: "fileList",
  components: {
    FileListRow,
    FileUpload,
  },
  data() {
    return {
      files: [],
      sort: "updatedAt",
      sortDir: "desc",
      page: 0,
      pageSize: 5,
    };
  },
  created() {
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
  mounted() {},
  methods: {
    sortedFiles() {
      return this.files
        .sort((a, b) => {
          let dir = this.sortDir === "asc" ? 1 : -1;
          if (a[this.sort] < b[this.sort]) {
            return -1 * dir;
          } else if (a[this.sort] > b[this.sort]) {
            return 1 * dir;
          } else {
            return 0;
          }
        })
        .filter((row, idx) => {
          let s = this.page * this.pageSize;
          let e = (this.page + 1) * this.pageSize;
          return idx >= s && idx < e;
        });
    },
    sortBy(s) {
      if (s === this.sort) {
        this.sortDir = this.sortDir === "asc" ? "desc" : "asc";
      } else {
        this.sortDir = "asc";
      }
      this.sort = s;
    },
    isActiveSort(s) {
      return this.sort === s;
    },
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
  },
  computed: {
    pageSizeModel: {
      get() {
        return this.pageSize;
      },
      set(v) {
        this.pageSize = v;
        this.page = 0;
      },
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/style/projectroom/fileList.scss";
</style>
