<template>
  <b-container fluid class="p-1" style="background-color:white; height:100%">
    <div>
      <b-tabs content-class="m-3" fill>
        <b-tab title="회의록" class="shadow-sm p-3 mb-5 bg-white rounded" active>
          <meeting-note
            :documetns="allDocuments"
            :profile="userProfile"
          ></meeting-note>
        </b-tab>
        <b-tab
          title="화이트보드"
        >
          <white-board></white-board>
        </b-tab>
      </b-tabs>
    </div>
  </b-container>
</template>

<script>
import { getDocumentList } from "@/api/meetingroom.js";
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
      currentTeamId: "",
    };
  },
  methods: {
    // db에서 전체 글 목록 가져오기
    getAllDocuments: function() {
      getDocumentList(
        this.currentTeamId,
        (res) => {
          console.log(res);
          this.allDocuments = res.data;
        },
        (err) => {
          console.log(err);
        }
      );
    },
  },
  created: function() {
    this.currentTeamId = localStorage.getItem("teamId");
    this.getAllDocuments();
  },
};
</script>