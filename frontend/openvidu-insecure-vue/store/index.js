/* eslint-disable eol-last */
import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    accessToken: "",
    // user pk
    // id: "",
    //  user user_email
    store_ov: undefined,
    store_session: undefined,
    userEmail: "",
  },
  getters: {
    isLogin: function(state) {
      if (state.userEmail) return true;
      return false;
    },
    get_ov(state) {
      return(state.store_ov);
    },
    get_session(state) {
      return(state.store_session);
    }
  },
  mutations: {
    setToken(state, newAccessToken) {
      localStorage.setItem("accessToken", newAccessToken);
      state.accessToken = newAccessToken;
    },
    SET_USERINFO: function(state, userdata) {
      // state.id = userdata["id"];
      state.userEmail = userdata["userEmail"];
    },
    SET_OV(state, ov) {
      state.store_ov = ov;
    },
    SET_SESSTION(state, session) {
      state.store_session = session;
    }
  },
  actions: {
    setUserinfo: function({ commit }, userEmail) {
      commit("SET_USERINFO", userEmail);
    },
    set_ov({commit}, ov) {
      commit("SET_OV", ov);
    },
    set_session({commit}, session) {
      commit("SET_SESSOIN", session);
    }
  },
});
