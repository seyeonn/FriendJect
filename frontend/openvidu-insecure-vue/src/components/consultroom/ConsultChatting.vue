<template>
  <div style="margin-top: 5%; height:100%; overflow:auto;">
    <div style="margin-left: 60%">
       <b-img
        src="https://i.imgur.com/tyBPQPf.png"
        style="width:180px;"
        @click="exitConsult"
      ></b-img>
    </div>
    <div style="margin-top: 10px; text-align:center; height:1000px;">
      <b-img
        src="https://i.imgur.com/5bJ55eZ.png"
        style="width:220px;"
      ></b-img>
      <AceEditor
        v-model="content"
        @init="editorInit"
        lang="javascript"
        theme="monokai"
        width="100%"
        height="400px"
        :options="{
          enableBasicAutocompletion: true,
          enableLiveAutocompletion: true,
          fontSize: 16,
          highlightActiveLine: true,
          enableSnippets: true,
          showLineNumbers: true,
          tabSize: 2,
          showPrintMargin: false,
          showGutter: true,
        }"
        :commands="[
          {
            name: 'save',
            bindKey: { win: 'Ctrl-S', mac: 'Command-S' },
            exec: dataSumit,
            readOnly: true,
          },
        ]"
      />
    </div>

    <b-modal
      id="modal-prevent-closing"
      ref="modal"
      title="상담 종료하기"
      @show="resetModal"
      @hidden="resetModal"
      @ok="handleOk"
      v-model="modalState"
    >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-img
        src="https://i.imgur.com/SiWCJCf.png"
        style="width:400px;"
      ></b-img>
        <b-form-group
          label-for="name-input"
          invalid-feedback="Name is required"
          :state="questionState"
        >
          <b-form-input
            id="name-input"
            v-model="question"
            :state="questionState"
            required
          ></b-form-input>
        </b-form-group>

         <b-img
        src="https://i.imgur.com/2TWcHpK.png"
        style="width:400px;"
      ></b-img>

        <b-form-group
          label-for="name-input"
          invalid-feedback="Name is required"
          :state="questionState"
        >
          <b-form-input
            id="name-input"
            v-model="answer"
            :state="questionState"
            required
          ></b-form-input>
        </b-form-group>

        <p> <b-img
        src="https://i.imgur.com/X3ReYJh.png"
        style="width:300px;"
      ></b-img></p>
      </form>
    </b-modal>
    <!-- <h2>저장된 코드 </h2>
    <AceEditor 
    v-model="saveCode" 
    @init="editorInit" 
    lang="javascript" 
    theme="monokai" 
    width="100%" 
    height="200px"
    :options="{
        enableBasicAutocompletion: true,
        enableLiveAutocompletion: true,
        fontSize: 14,
        highlightActiveLine: true,
        enableSnippets: true,
        showLineNumbers: true,
        tabSize: 2,
        showPrintMargin: false,
        showGutter: true,
        readOnly: true
        
    }"
    :commands="[
        {
            name: 'save',
            bindKey: { win: 'Ctrl-S', mac: 'Command-S' },
            exec: dataSumit,
            readOnly: true,
        },
    ]"
    /> -->
  </div>
</template>

<script>
import AceEditor from "vuejs-ace-editor";

export default {
  data() {
    return {
      content: "",
      saveCode: "",
      question: "",
      answer: "",
      questionState: null,
      modalState: false,
    };
  },
  components: {
    AceEditor,
  },

  methods: {
    dataSumit() {
      //code here
      alert("소스코드가 정상적으로 저장되었습니다.");
      this.saveCode = this.content;
      console.log(this.content);
    },
    editorInit: function () {
      require("brace/ext/language_tools"); //language extension prerequsite...
      require("brace/mode/html");
      require("brace/mode/javascript"); //language
      require("brace/mode/less");
      require("brace/theme/monokai");
      require("brace/snippets/javascript"); //snippet
    },

    exitConsult: function () {
      this.modalState = !this.modalState;
    },
    checkFormValidity() {
      const valid = this.$refs.form.checkValidity();
      this.nameState = valid;
      return valid;
    },
    resetModal() {
      this.name = "";
      this.nameState = null;
    },
    handleOk(bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault();
      // Trigger submit handler
      this.handleSubmit();
    },
    handleSubmit() {
      if (this.question != "" && this.answer != "") {
        //여기서 api 콜
      }
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide("modal-prevent-closing");
      });
    },
  },
};
</script>

<style></style>
