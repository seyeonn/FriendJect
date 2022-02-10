<template>
  <div class="user">
    <img src="https://randomuser.me/api/portraits/men/1.jpg" class="user-img" />
    <div class="username">
      {{ sub }}
      <div class="user-status"></div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    // connection: "",
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/style/main-new.scss";
</style>
