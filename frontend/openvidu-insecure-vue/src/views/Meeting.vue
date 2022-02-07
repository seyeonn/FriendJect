<template>
  <div>
      <meeting-note :documetns="allDocuments" :profile="userProfile"></meeting-note>
  </div>
</template>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
import axios from 'axios'
import MeetingNote from '../components/meetingroom/MeetingNote.vue'

export default {
    components: {
        MeetingNote,
    },
    data() {
    return {
      // 회의록 전체 글
      allDocuments : [],
      
      // 유저프로필사진url
      userProfile : '',
    }
  },
  methods: {
    // db에서 전체 글 목록 가져오기
    getAllDocuments: function() {
      axios({
        method: 'get',
        url: 'http://localhost:8080/meetingroom/document/',
      })
        .then(res => {
          console.log(res)
          this.allDocuments = res.data
        })
        .catch(err => {
          console.log(err)
        })
    },
    // db에서 유저프로필url 가져오기
     getUserInfo: function() {
      axios({
        method: 'get',
        url: 'http://localhost:8080/profile/1',
      })
        .then(res => {
          console.log(res)
          this.userProfile = res.data.profile
        })
        .catch(err => {
          console.log(err)
        })
    },

  },
  created: function() {
    this.getAllDocuments()
    this.getUserInfo()
  },
}
</script>

<style>
</style>