<template>
  <div>
    <div id="join-dialog" class="jumbotron vertical-center">
      <h1>Join a video session</h1>
      <div class="form-group">
        <p>
          <label>Participant</label>
          <input
            v-model="myUserName"
            class="form-control"
            type="text"
            required
          />
        </p>
        <p>
          <label>Session</label>
          <input
            v-model="mySessionId"
            class="form-control"
            type="text"
            required
          />
        </p>
        <p class="text-center">
          <button class="btn btn-lg btn-success" @click="joinSession()">
            Join!
          </button>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    data() {
        return {
            myUserName: "",
            mySessionId: "",
        }
    },
    methods: {
        joinSession() {
            //여기서 프롭스로 쟤네 전달
            console.log(this.mySessionId);
            this.$router.push('join');
        }
    }
}
</script>

<style></style>
