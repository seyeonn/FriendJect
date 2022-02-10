<template>
  <div>
    <div v-show="!isShow">
      <div>
        <h3 class="title">
          {{ this.title
          }}<button class="memo__btns modify" @click="updateMemoForm">m</button
          ><button class="memo__btns delete" @click="removeMemo">X</button>
        </h3>
      </div>
      <div>
        <p class="description">{{ this.content }}</p>
      </div>
    </div>
    <div v-show="isShow">
      <div>
        <h3 class="title">
          <input
            type="text"
            name="title"
            id="input__title"
            v-model="memo.title"
            value="this.title"
            ref="cursor"
            required
          />
        </h3>
      </div>
      <div>
        <p class="description">
          <input
            type="text"
            name="content"
            id="input__content"
            v-model="memo.content"
            value="this.content"
            required
            @keyup.enter="updateMemo"
          />
        </p>
      </div>
      <div class="memo__register">
        <button class="memo__register__btn" type="button" @click="updateMemo">
          등록
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { modifyMemo, deleteMemo } from "@/api/projectroom";
export default {
  name: "MemoElement",
  data() {
    return {
      isShow: false,
      memo: [],
    };
  },
  props: {
    id: Number,
    title: String,
    content: String,
  },
  created() {
    this.memo.title = this.title;
    this.memo.content = this.content;
  },
  methods: {
    updateMemoForm() {
      this.isShow = true;
    },
    updateMemo() {
      let msg = "제목을 입력하세요";
      if (this.memo.title === "") {
        alert(msg);
      } else if (this.memo.content === "") {
        msg = "내용을 입력하세요";
        alert(msg);
      } else {
        modifyMemo(
          this.id,
          {
            title: this.memo.title,
            content: this.memo.content,
          },
          ({ data }) => {
            console.log(data);
            msg = "메모 수정이 완료되었습니다!";
            alert(msg);
            this.$router.go();
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
    removeMemo() {
      if (confirm("정말 삭제하시겠습니까?")) {
        deleteMemo(this.id, () => {
          this.$router.go();
        });
      }
    },
  },
};
</script>

<style lang="scss" scoped>
$page-bg: #282f32;
$bright-blue: #68b3ff;
$light-gray: #999;
//$curve: cubic-bezier(0, 0, 0.52, 1.11);
$curve: linear;
.title {
  font-size: 1.1rem;
  font-weight: bold;
}
.description {
  font-size: 0.7rem;
}

input[type="text"] {
  width: 100%;
  padding-left: 0;
  background: none;
  border: none;
  border-bottom: 1px solid $light-gray;
}
.memo__register {
  display: flex;
  flex-direction: row;
  justify-content: end;
}
.memo__register__btn {
  color: white;
  background: #f9b225;
  padding: 0.3em 0.4em;
  font-size: 1em;
  border: none;
  border-radius: 5px;
  display: flex;
  justify-content: flex-end;
}
* :focus {
  outline-color: transparent;
  outline-style: none;
}
</style>
<style scoped>
button {
  background: transparent;
  padding: 0;
  border: none;
  outline: none;
}
.memo__btns:hover {
  color: rgb(58, 24, 24);
  transform: translateY(-3px);
  font-size: 13px;
}
.modify {
  position: absolute;
  color: rgb(64, 148, 106);
  right: 25px;
  font-size: 11px;
}
.delete {
  position: absolute;
  color: rgb(255, 0, 0);
  right: 12px;
  font-size: 11px;
}
</style>
