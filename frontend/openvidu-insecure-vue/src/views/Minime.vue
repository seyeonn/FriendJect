<template>
  <div>
    <div class="mb-1rem">
      
    </div>
    <vue-select-image
      :dataImages="dataImages"
      @onselectimage="onSelectImage">
    </vue-select-image>
    <button class="mb-1rem button basic outline" @click="postMinime(userEmail)">선택</button>
  </div>
</template>

<script>
import VueSelectImage from 'vue-select-image'
import axios from 'axios'
import { mapState, mapActions } from "vuex";

export default {
    name: "minime",
    components: { VueSelectImage },
    data() {
      return {
        singleSelected: {
          id: "",
          src: "",
          alt: "",
        },
        dataImages:
          [{
            id: '1',
            src: 'https://user-images.githubusercontent.com/83205029/153934643-f07bb826-bce4-4b58-a4d6-4f78bb69cd71.png',
            alt: 'black_tiger'
            }, {
            id: '2',
            src: 'https://user-images.githubusercontent.com/83205029/153934672-c1e3436d-d5f6-4072-bef3-c678d7f329d8.png',
            alt: 'choijun_tiger'
            }, {
            id: '3',
            src: 'https://user-images.githubusercontent.com/83205029/153934682-074d5222-905f-4c9b-ac86-bf2b75e6faa6.png',
            alt: 'flower_tiger',
            }, {
            id: '4',
            src: 'https://user-images.githubusercontent.com/83205029/153934686-1582bb31-bc21-43ca-a78f-1ac21530af3b.png',
            alt: 'glass_tiger',
            }, {
            id: '5',
            src: 'https://user-images.githubusercontent.com/83205029/153934703-2e326d30-4f11-421c-9e00-33ed8cc68622.png',
            alt: 'green_tiger',
            }, {
            id: '6',
            src: 'https://user-images.githubusercontent.com/83205029/153934715-f7d13f2d-52c0-4f9a-bcb8-7c91defcd6d2.png',
            alt: 'mask_tiger',
            }, {
            id: '7',
            src: 'https://user-images.githubusercontent.com/83205029/153934728-9ebbd49f-7a06-4001-a72a-47513554a801.png',
            alt: 'ssafy_tiger',
            }, {
            id: '8',
            src: 'https://user-images.githubusercontent.com/83205029/153934734-cbac39a1-2b54-4089-b18a-0e15a19fac46.png',
            alt: 'winter_tiger',
            },
         ]
      }
    },
    computed: {
    ...mapState(["profileUrl"]),
    ...mapState(["userEmail"]),
    },
    methods: {
        ...mapActions(["setUserinfo"]),
        onSelectImage(objectImage) {
            if (!objectImage.disabled) {
            this.singleSelected = Object.assign({}, this.singleSelected, objectImage);
            // this.$emit("onselectimage", objectImage); // 자식 컴포넌트에서 이벤트를 선언
            }
        },
        postMinime: function(email) {
            if(this.singleSelected.src) {
            axios.patch(`http://localhost:8081/api/profile/minime/${email}`, {
                profileUrl: this.singleSelected.src
                }).then(res => {
                    console.log(res)
                    // this.setUserinfo 업데이트된 미니미로 변경x 수정필요
                    this.$router.push({ name: 'room'})
                })
                .catch(err => {
                    console.log(err)
                })
            }
        },
    },
}
</script>

<style lang="scss" scope>
@import "@/assets/style/minime.scss";
</style>