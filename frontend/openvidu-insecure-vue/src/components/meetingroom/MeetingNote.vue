<template>
  <b-container class="my-contatiner">
    <b-row>
      <b-col sm="5">
        <div class="list-group">
          <button class="button basic outline" @click="showPostForm">
            <p class="h2 mb-2"><b-icon icon="plus-circle"></b-icon></p>
          </button>
          <div class="list-group-item" v-for="doc in documetns" :key="doc.id">
            <div>
              <h4 class="list-group-item-heading">
                <b-icon icon="exclamation-circle"></b-icon>
                <b-button variant="light" @click="getDocument(doc.id, $event)">
                  {{ doc.title }}</b-button
                >
              </h4>
              <h5>
                <b-icon icon="calendar4-event"></b-icon>
                {{ doc.meeting_time | yyyyMMddHHmm }}
              </h5>
            </div>
          </div>
        </div>
      </b-col>

      <b-col sm="7">
        <div class="form" v-if="postable">
          <div class="mt-3_2">
            <img src="https://i.imgur.com/WJhZ9Lm.png" style="width: 200px; height: 70px;">
          </div>
          <div>
            <div>
              <input class="form-control" placeholder="title" v-model="title" />
              <b-textarea
                rows="8"
                placeholder="content"
                v-model="content"
              ></b-textarea>
              <button class="button basic outline" @click="postDocument">
                Submit
              </button>
            </div>
          </div>
        </div>

        <div class="form" v-if="editable">
          <div class="panel-heading mt-3_2">
            <h3>Edit Meetingnote</h3>
          </div>
          <div class="panel-body">
            <div>
              <input class="form-control" v-model="document.title" />
              <b-textarea rows="8" v-model="document.content"></b-textarea>
              <button
                class="button basic outline"
                @click="updateDocument(document.id, $event)"
              >
                수정완료
              </button>
            </div>
          </div>
        </div>

        <div v-if="document && showDetail">
          <div class="mt-3_2">
            <h3>Meetingnote Detail</h3>
          </div>
          <div>
            <b-card :title="document.title">
              <b-card-text style="white-space:pre-line;">
                {{ document.content }}
              </b-card-text>
              <template #footer>
                <small class="text-muted"
                  ><time>{{
                    document.meeting_time | yyyyMMddHHmm
                  }}</time></small
                >
              </template>
            </b-card>
          </div>
          <div>
            <button class="button basic outline" @click="showUpdateForm">
              Update
            </button>
            <button
              class="button basic outline"
              @click="deleteDocument(document.id, $event)"
            >
              Delete
            </button>
          </div>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import {
  getOneDocument,
  registerDocument,
  modifyDocument,
  removeDocument,
} from "@/api/meetingroom.js";
export default {
  name: "MeetingNote",
  props: {
    // 회의록
    documetns: Array,
  },
  data() {
    return {
      // 회의록
      title: "",
      content: "",
      document: null,
      postable: true,
      showDetail: false,
      editable: false,
      currentTeamId: "",
    };
  },
  filters: {
    yyyyMMddHHmm: function(value) {
      if (value == null) return "";

      var js_date = new Date(value);

      var year = js_date.getFullYear();
      var month = js_date.getMonth() + 1;
      var day = js_date.getDate();
      var hour = js_date.getHours();
      var min = js_date.getMinutes();

      if (month < 10) month = "0" + month;
      if (day < 10) day = "0" + day;
      if (hour < 10) hour = "0" + hour;
      if (min < 10) min = "0" + min;

      return (
        year + "년 " + month + "월 " + day + "일 " + hour + "시 " + min + "분"
      );
    },
    reContents: function(value) {
      return value.split("\n");
    },
  },
  methods: {
    getDocument: function(targetId) {
      this.postable = false;
      this.showDetail = true;
      this.editable = false;
      getOneDocument(
        targetId,
        (res) => {
          console.log(res);
          this.document = res.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    showPostForm: function() {
      this.postable = true;
      this.showDetail = false;
      this.editable = false;
    },
    showUpdateForm: function() {
      this.editable = true;
      this.postable = false;
      this.showDetail = false;
    },
    postDocument: function(currentTeamId) {
      const documentItem = {
        title: this.title,
        content: this.content,
        currentTeamId: this.currentTeamId
      };
      if (documentItem.title && documentItem.content) {
        registerDocument(
          currentTeamId,
          documentItem,
          (res) => {
            console.log(res.data + "번 글 작성완료");
            location.reload();
          },
          (err) => {
            console.log(err);
          }
        );
      }
    },
    updateDocument: function(targetId) {
      const documentItem = {
        title: this.document.title,
        content: this.document.content,
      };
      if (documentItem.title && documentItem.content) {
        modifyDocument(
          targetId,
          documentItem,
          (res) => {
            console.log(res.data + "번 글 수정완료");
            location.reload();
          },
          (err) => {
            console.log(err);
          }
        );
      }
    },
    // 글삭제 백엔드 전송
    deleteDocument: function(targetId) {
      removeDocument(
        targetId,
        (res) => {
          console.log(res.data + "번 글 삭제완료");
          location.reload();
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
  created: function() {
    this.currentTeamId = localStorage.getItem("teamId");
  },
};
</script>

<style lang="scss">
@import "@/assets/style/meetingroom/meetingnote.scss";
</style>
