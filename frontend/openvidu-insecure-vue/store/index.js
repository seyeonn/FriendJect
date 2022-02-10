/* eslint-disable eol-last */
import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    accessToken: "",
    userEmail: "",
    myUserName: "",
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
    SET_USERINFO: function(state, userdata) {
      state.userEmail = userdata["userEmail"];
      state.myUserName = userdata["nickName"];
    },
  },
  actions: {
    setUserinfo: function({ commit }, userdata) {
      commit("SET_USERINFO", userdata);
    },
  },
});
