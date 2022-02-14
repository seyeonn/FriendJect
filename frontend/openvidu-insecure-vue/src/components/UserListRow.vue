<template>
  <div class="user">
    <!-- user profile_url api로 추가 예정 -->
    <img src="https://randomuser.me/api/portraits/men/1.jpg" class="user-img" />
    <div class="username">
      {{ clientData }}
    </div>
  </div>
</template>

<script>
export default {
  name: "UserListRow",

  props: {
    streamManager: Object,
  },

  computed: {
    clientData() {
      const { clientData } = this.getConnectionData();
      return clientData;
    },
  },

  methods: {
    getConnectionData() {
      const { connection } = this.streamManager.stream;
      return JSON.parse(connection.data);
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/style/main-new.scss";
</style>
