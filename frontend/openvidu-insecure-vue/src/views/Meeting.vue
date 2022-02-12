<template>
  <b-container fluid class="p-1" style="background-color:white; height:100%">
    <div>
      <b-tabs content-class="m-3" fill>
        <b-tab title="화이트보드" active class="shadow-sm p-3 mb-5 bg-white rounded">
          <white-board></white-board>
        </b-tab>
        <b-tab title="회의록">
          <meeting-note :documetns="allDocuments" :profile="userProfile"></meeting-note>
        </b-tab>
      </b-tabs>
    </div>
  </b-container>
</template>

<script>
import axios from "axios";
import MeetingNote from "../components/meetingroom/MeetingNote.vue";
import WhiteBoard from "../components/meetingroom/WhiteBoard.vue";

export default {
  components: {
    MeetingNote, 
    WhiteBoard,
  },
  data() {
    return {
      // 회의록 전체 글
      allDocuments: [],

    };
  },
  methods: {
    // db에서 전체 글 목록 가져오기
    getAllDocuments: function() {
      axios({
        method: "get",
        url: "http://localhost:8081/meetingroom/document/",
      })
        .then((res) => {
          console.log(res);
          this.allDocuments = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  created: function() {
    this.getAllDocuments();
  },
};
</script>

<style></style>
