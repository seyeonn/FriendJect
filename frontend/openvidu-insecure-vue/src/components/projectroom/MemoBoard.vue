<template>
  <div class="container board__container">
    <div class="row">
      <div class="col">
        <b-form class="add__task__container">
          <b-form-input
            id="inline-form-input-name"
            class="mb-2 mr-sm-2 mb-sm-0 add__task__input"
            v-model="newTitle"
            required
            placeholder="Title"
          ></b-form-input>
          <b-input-group class="mb-2 mr-sm-2 mb-sm-0 add__task__input">
            <b-form-input
              v-model="newContent"
              required
              placeholder="Content"
              @keyup.enter="add"
            ></b-form-input>
          </b-input-group>
          <b-button @click="add" class="mt-3 add__task__btn">Save</b-button>
        </b-form>
      </div>
    </div>
    <div class="row mt-5">
      <div class="col-3">
        <div class="p-2 alert alert-secondary">
          <h3 class="title">To do</h3>
          <!-- Backlog draggable component. Pass arrBackLog to list prop -->
          <draggable
            class="list-group kanban-column"
            :list="arrTodo"
            group="tasks"
            v-model="arrTodo"
          >
            <memo-element
              class="list-group-item"
              v-for="element in arrTodo"
              :key="element.id"
              v-bind="element"
            ></memo-element>
          </draggable>
        </div>
      </div>

      <div class="col-3">
        <div class="p-2 alert alert-primary">
          <h3 class="title">In Progress</h3>
          <!-- In Progress draggable component. Pass arrInProgress to list prop -->
          <draggable
            class="list-group kanban-column"
            :list="arrInProgress"
            v-model="arrInProgress"
            group="tasks"
          >
            <memo-element
              class="list-group-item"
              v-for="element in arrInProgress"
              :key="element.id"
              v-bind="element"
            ></memo-element>
          </draggable>
        </div>
      </div>

      <div class="col-3">
        <div class="p-2 alert alert-warning">
          <h3 class="title">Testing</h3>
          <!-- Testing draggable component. Pass arrTested to list prop -->
          <draggable
            class="list-group kanban-column"
            :list="arrTested"
            v-model="arrTested"
            group="tasks"
          >
            <memo-element
              class="list-group-item"
              v-for="element in arrTested"
              :key="element.id"
              v-bind="element"
            ></memo-element>
          </draggable>
        </div>
      </div>

      <div class="col-3">
        <div class="p-2 alert alert-success">
          <h3 class="title">Done</h3>
          <!-- Done draggable component. Pass arrDone to list prop -->
          <draggable
            class="list-group kanban-column"
            :list="arrDone"
            v-model="arrDone"
            group="tasks"
          >
            <memo-element
              class="list-group-item"
              v-for="element in arrDone"
              :key="element.id"
              v-bind="element"
            ></memo-element>
          </draggable>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import draggable from "vuedraggable";
import {
  getMemoList,
  registerMemo,
  registerMemoList,
} from "@/api/projectroom.js";
import MemoElement from "@/components/projectroom/child/MemoElement.vue";

export default {
  name: "memoboard",
  components: {
    //import draggable as a component
    draggable,
    MemoElement,
  },
  data() {
    return {
      // for new tasks
      newTask: "",
      newTitle: "",
      newContent: "",
      // 4 arrays to keep track of our 4 statuses
      arrTodo: [],
      arrInProgress: [],
      arrTested: [],
      arrDone: [],
      currentTeamId: "",
    };
  },

  watch: {
    arrTodo: function() {
      this.updateStatusList(this.arrTodo, "TODO");
    },
    arrInProgress: function() {
      this.updateStatusList(this.arrInProgress, "INPROGRESS");
    },
    arrTested: function() {
      this.updateStatusList(this.arrTested, "TESTING");
    },
    arrDone: function() {
      this.updateStatusList(this.arrDone, "DONE");
    },
  },
  created() {
    this.currentTeamId = localStorage.getItem("teamId");
    getMemoList(
      "TODO",
      this.currentTeamId,
      (response) => {
        this.arrTodo = response.data.data;
      },
      (error) => {
        console.log(error);
      }
    );
    console.log("test");
    getMemoList(
      "INPROGRESS",
      this.currentTeamId,
      (response) => {
        this.arrInProgress = response.data.data;
      },
      (error) => {
        console.log(error);
      }
    );
    console.log("test");
    getMemoList(
      "TESTING",
      this.currentTeamId,
      (response) => {
        this.arrTested = response.data.data;
      },
      (error) => {
        console.log(error);
      }
    );
    console.log("test");
    getMemoList(
      "DONE",
      this.currentTeamId,
      (response) => {
        this.arrDone = response.data.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    //add new tasks method
    add() {
      if (this.newTitle && this.newContent) {
        this.writeNewMemo();
      }
    },
    writeNewMemo() {
      registerMemo({
        teamId: this.currentTeamId,
        title: this.newTitle,
        content: this.newContent,
        status: "TODO",
      });
      this.$router.go();
    },
    updateStatusList(arr, status) {
      registerMemoList(arr, status, this.currentTeamId);
    },
  },
};
</script>

<style scoped>
/* light stylings for the kanban columns */
.kanban-column {
  min-height: 400px;
}

.board__container {
  padding-right: 0;
}
.add__task__container {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  flex-direction: column;
  width: 30rem;
  height: 10rem;
  margin: auto;
  background: #a8a8a8;
  border: #000013 0.2rem solid;
  border-radius: 0.2rem;
  padding: 0.4rem;
}
.add__task__input {
  align-items: flex-start;
}
.add__task__btn {
  align-self: center;
  background-color: #ffcb1e;
  padding: 0 1rem;
  height: 2.8rem;
  width: 10rem;
  margin-top: 0.6rem;
  border: none;
}
.list-group-item {
  padding: 8px 12px;
  margin: 0;
}
</style>
<style lang="scss" scoped>
.title {
  font-size: 1.7rem;
  font-weight: bold;
}
</style>
