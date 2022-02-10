<template>
  <div>
    <div>
      <h3 class="title">
        {{ this.title }}<button class="modify">m</button
        ><button class="delete" @click="removeMemo">X</button>
      </h3>
    </div>
    <div>
      <p class="description">{{ this.content }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "MemoElement",
  props: {
    id: Number,
    title: String,
    content: String,
  },
  created() {
    console.log(this.id);
  },
  methods: {
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
.title {
  font-size: 1.1rem;
  font-weight: bold;
}
.description {
  font-size: 0.7rem;
}
</style>
<style scoped>
button {
  background: transparent;
  padding: 0;
  border: none;
  outline: none;
}
button:hover {
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
