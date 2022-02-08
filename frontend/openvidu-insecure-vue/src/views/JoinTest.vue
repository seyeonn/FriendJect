<template>
  <b-container>
    <div>
      <div class="cards" style="width:100%;">
        <article
          class="information [ card ]"
          style="width:40%; margin-right:10%"
        >
          <span class="tag">새 프로젝트 시작하기</span>
          <h2 class="title">팀 생성</h2>
          <p class="info" style="font-size:0.9rem;">
            팀 코드가 생성되고, 클립보드에 복사됩니다. 팀원에게 코드로 참여를
            유도하세요!
          </p>
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
          
          <br />
        </article>

        <article class="information [ card ]" style="width:40%">
          <span class="tag">기존 프로젝트에 참여하기</span>
          <h2 class="title">팀 코드로 참가</h2>
          <p class="info" style="font-size:0.9rem;">
            팀원에게 받은 코드를 입력하세요!
          </p>

          <b-input-group size="md">
            <b-form-input v-model="myUserName"
              class="form-control"
              type="text"
              required></b-form-input>
          </b-input-group>
          <br />
          <button class="button">
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
        <!-- <article class="information [ card ]">
      <h2 class="title">The only tool you need to schedule your day</h2>
      <p class="info">
        Always keep updated with this simple tool on the go, when and where ever
        you need.
      </p>
      <dl class="details">
        <div>
          <dt>Satisfaction</dt>
          <dd>100%</dd>
        </div>
        <div>
          <dt>Customers</dt>
          <dd>4.5K</dd>
        </div>
      </dl>
    </article> -->
      </div>

      <div>
        <h2 style="color:#3D3D3C">참여중인 프로젝트</h2>
        <div class="cards" style="width:100%;">
          <article class="information [ card ]">
            <dl class="details">
              <div>
                <dt>#2123</dt>
                <dd>[2팀] 콘푸로스트 </dd>
              </div>
            </dl>
            <button class="button" style="background-color:#F9B225; color:white">
              접속하기
            </button>
            
          </article>
          
        </div>
      </div>
    </div>
  </b-container>
</template>

<script>
export default {
};
</script>

<style lang="scss">
@import "@/assets/style/join_room.scss";
</style>
