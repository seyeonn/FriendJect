<template>
  <div>
    <div class="cards" style="width:100%;">
      <article class="information [ card ]" style="width:40%; margin-right:10%">
        <span class="tag">새로운 프로젝트 생성 </span>
        <h2 class="title">팀 생성</h2>
        <p class="info" style="font-size:0.9rem;">
          팀 참여코드는 자동으로 생성되고, 클립보드에 복사됩니다. <br />
          팀 이름만 설정해보세요!
        </p>
        <b-input-group size="md">
          <b-form-input
            v-model="teamName"
            class="form-control"
            type="text"
          ></b-form-input>
        </b-input-group>
        <br />
        <button class="button" @click="randomNumber">
          <span>Create</span>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="24px"
            viewBox="0 0 24 24"
            width="24px"
            fill="none"
          >
            <path d="M0 0h24v24H0V0z" fill="none" />
            <path
              d="M16.01 11H4v2h12.01v3L20 12l-3.99-4v3z"
              fill="currentColor"
            />
          </svg>
        </button>
      </article>

      <article class="information [ card ]" style="width:40%">
        <span class="tag">기존 프로젝트에 참여하기</span>
        <h2 class="title">팀 코드로 참가</h2>
        <p class="info" style="font-size:0.9rem;">
          팀원에게 받은 고유코드를 입력하세요!
        </p>
        <!-- 이부분 현재 바인딩이 안됨. code 바인딩해서 넘겨주기 -->
        <router-link 
          :to="{ name: 'minime', 
          params: { code: '123' }}" 
          class="button">
          MINIME
        </router-link>

        <button class="button" @click="setIn">
          <span>Join</span>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="24px"
            viewBox="0 0 24 24"
            width="24px"
            fill="none"
          >
            <path d="M0 0h24v24H0V0z" fill="none" />
            <path
              d="M16.01 11H4v2h12.01v3L20 12l-3.99-4v3z"
              fill="currentColor"
            />
          </svg>
        </button>
      </article>
    </div>

    <div>
      <h2 style="color:#3D3D3C; margin-left:10%">참여중인 프로젝트</h2>

      <!-- 이부분 접속 했던 이력을 for 문으로 나타내기 -->
      <div class="cards" style="width:100%;">
        <article class="information [ card ]">
          <dl class="details">
            <div>
              <dt>#2123</dt>
              <dd>[2팀] 콘푸로스트</dd>
            </div>
          </dl>
          <button class="button" style="background-color:#F9B225; color:white">
            접속하기
          </button>
        </article>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "search",
  data() {
    return {
      message: "",
      test: "",
    };
  },
};
</script>

<style lang="scss" scope>
@import "@/assets/style/join_room.scss";
</style>
