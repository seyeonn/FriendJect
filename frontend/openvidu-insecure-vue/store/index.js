/* eslint-disable eol-last */
import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    accessToken: "",
    mySessionId: "",
    myUserName: "",
    // user pk
    // id: "",
    //  user user_email
    userEmail: "",
  },
  getters: {
    isLogin: function(state) {
      if (state.userEmail) return true;
      return false;
    },
  },
  mutations: {
    setToken(state, newAccessToken) {
      localStorage.setItem("accessToken", newAccessToken);
      state.accessToken = newAccessToken;
    },
    setSessionID(state, newMySessionId) {
      state.mySessionId = newMySessionId;
    },
    SET_USERINFO: function(state, userdata) {
      // state.id = userdata["id"];
      state.userEmail = userdata["userEmail"];
    },
  },
  actions: {
    setUserinfo: function({ commit }, userEmail) {
      commit("SET_USERINFO", userEmail);
    },
  },
});
