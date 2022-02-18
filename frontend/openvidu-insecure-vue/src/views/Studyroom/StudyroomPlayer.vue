<template>
    <div>
        
<div class="container-audio">
        <audio controls  loop autoplay>
                   <source src="https://commitmentbucket.s3.ap-northeast-2.amazonaws.com/fireplace_sound.mp3" type="audio/ogg">
                   Your browser dose not Support the audio Tag
               </audio>
    </div>
    </div>
</template>

<script>
import $ from 'jquery';

export default ({
    setup() {
        
    },
})

var 
  $player = $(".audio-ctrl"),
  $transcript = $(".audio-transcript");

  $(".audio-ctrl").each(function(){

    $(this).attr("aria-pressed","false");

    $(this).click(function(e){
      e.preventDefault();

      $player.attr("aria-pressed","false");

      if($(this).hasClass("play")){
        $(this).attr("aria-pressed","true");
        // 2 sec delay to allow screen reader
        // to read button state
        setTimeout(function(){
          $("#player")[0].play();
        },2000);
      }
      if($(this).hasClass("pause")){
        $("#player")[0].pause();
        $(this).attr("aria-pressed","true");
      }
      if($(this).hasClass("read")){
        $transcript.removeClass("visually-hidden").focus();
      }
    });
  });
</script>

<style scoped>

</style>