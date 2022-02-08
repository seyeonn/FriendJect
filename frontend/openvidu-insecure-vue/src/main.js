<<<<<<< HEAD
import Vue from 'vue'
import App from './App.vue'
import Vuex from "vuex";
import {router} from './routers/index.js'
import { store } from "../store/index.js";
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
=======
import Vue from "vue";
import Vuex from "vuex";
import { store } from "../store/index.js";
import App from "./App.vue";
import { router } from "./routers/index.js";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
>>>>>>> feature/signup

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

Vue.use(Vuex);

<<<<<<< HEAD
Vue.config.productionTip = false
=======
Vue.config.productionTip = false;
>>>>>>> feature/signup

new Vue({
  render: (h) => h(App),
  router,
  store,
<<<<<<< HEAD
}).$mount('#app')
=======
}).$mount("#app");
>>>>>>> feature/signup
