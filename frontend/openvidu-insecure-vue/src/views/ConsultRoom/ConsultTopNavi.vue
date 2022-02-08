<template>
  <div>
    <b-row style="text-align:center">
      <b-col>질문자 얼굴</b-col>
      <b-col>
          
          <div v-on:click="solutionBook">
             
             <a href="#consult"><img :src="image_src" style="height:40%" @mouseover="mouseover" @mouseleave="mouseleave"
/></a>
          </div>
      </b-col>
      <b-col>답변자 얼굴</b-col>
    </b-row>

    <div id="consult" class="modal-window">
      <div>
        <a href="#" title="Close" class="modal-close">
            <b-icon icon="x-circle-fill" scale="2" variant="danger"></b-icon>
        </a>
       
        <div style="text-align:center; width:90%;">
          {{ solution }}
        </div>
        <!-- <div><small>Check out</small></div> -->
      </div>
    </div>
  </div>
</template>

<script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.4.2/vue.min.js"></script>
<script>
import axios from "axios";

export default {
  data() {
    return {
     image_src: "https://user-images.githubusercontent.com/16666658/152699993-0aa2056e-4097-462b-9d20-5ed6d72894ea.png",
      solution: "",
    };
  },
  methods: {
    solutionBook: function() {
      console.log("책 조회");
      axios
        .get(`http://localhost:9999/consultroom/book`)
        .then((response) => {
          console.log(response.data.text);
          this.solution = response.data.text;
        })
        .catch();
    },

     mouseover: function(){
      this.image_src = "https://user-images.githubusercontent.com/16666658/152699680-8ba0db26-bdbc-4718-b39e-71230cbe1e14.png"
    },   
    mouseleave: function(){
      this.image_src = "https://user-images.githubusercontent.com/16666658/152699993-0aa2056e-4097-462b-9d20-5ed6d72894ea.png"
    },  
  },
};
</script>

<style lang="scss" scoped>
@import "@/assets/style/solution_modal.scss";
</style>
