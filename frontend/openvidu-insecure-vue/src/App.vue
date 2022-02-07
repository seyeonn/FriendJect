<template>
  <!-- 메인이 되는 부분 -->
  
  <div>
    <!-- 반응형 메인 이미지 코드!! 주석 처리 되었으나, 재사용 할거같으니 지우지 마세용!!! -->
    <!-- <b-img :src="require('./assets/images/main.png')" fluid-gro></b-img> -->
    <!-- 자주 바뀌는 영역 -->
    <!-- <Header /> -->
    <b-container fluid class="bv-example-row">
      <router-view></router-view>
    </b-container>
  </div>
  <!-- Content here -->
</template>

<script>
//  import Header from "./components/layout/Header.vue";
export default {
  name: "App",
  components: {
    // Header,
  },
};
</script>

<!-- <style lang="scss">
	@import "@/assets/main.scss";

</style> -->
