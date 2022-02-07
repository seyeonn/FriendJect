<template>
  <div class="file-upload">
    <button class="file-upload-btn" type="button" @click="saveFile()">
      Save
    </button>
    <div class="image-upload-wrap">
      <input
        class="file-upload-input"
        type="file"
        @change="readURL"
        accept="image/*, application/*, audio/*, video/*"
      />
      <div class="drag-text">
        <h3>Drag and drop</h3>
      </div>
    </div>
    <div class="file-upload-content">
      <img class="file-upload-image" src="#" alt="your image" />
      <div class="image-title-wrap">
        <button type="button" @click="removeUpload()" class="remove-image">
          Remove <span class="image-title">Uploaded File</span>
        </button>
      </div>
    </div>
  </div>
</template>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
import { registerFile } from "@/api/projectroom.js";

export default {
  name: "fileUpload",
  data() {
    return {
      file: "",
      isSelected: true,
    };
  },
  created() {
    let doc = document.createElement("script");
    doc.setAttribute("src", "your-link-here");
    document.head.appendChild(doc);
  },
  mounted() {
    $(".image-upload-wrap").bind("dragover", function() {
      $(".image-upload-wrap").addClass("image-dropping");
    });
    $(".image-upload-wrap").bind("dragleave", function() {
      $(".image-upload-wrap").removeClass("image-dropping");
    });
  },
  methods: {
    readURL(event) {
      var input = event.target;
      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
          $(".image-upload-wrap").hide();
          $(".file-upload-image").attr("src", e.target.result);
          $(".file-upload-content").show();
          $(".image-title").html(input.files[0].name);
        };
        this.file = input.files[0];
        console.log(this.file);
        reader.readAsDataURL(input.files[0]);
      } else {
        removeUpload();
      }
    },
    removeUpload() {
      $(".file-upload-input").replaceWith($(".file-upload-input").clone());
      $(".file-upload-content").hide();
      $(".image-upload-wrap").show();
    },
    saveFile() {
      let formData = new FormData();
      formData.append("file", this.file);
      registerFile(
        formData,
        (response) => {
          console.log(response);
          this.$router.go();
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>
<style lang="scss" scoped>
@import "@/assets/style/projectroom/fileUpload.scss";
</style>
