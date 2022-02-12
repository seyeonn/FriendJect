<template>
  <div>
    <meeting-note
      :documetns="allDocuments"
    ></meeting-note>
  </div>
</template>

<script>
import axios from "axios";
import MeetingNote from "../components/meetingroom/MeetingNote.vue";

export default {
  components: {
    MeetingNote,
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
        url: "http://localhost:8080/meetingroom/document/",
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
