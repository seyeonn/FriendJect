/* eslint-disable eol-last */
import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    accessToken: "",
    kakaoId: "",
    userEmail: "",
    myUserName: "",
    profileUrl: "",
    sessionState: 0,
    message: "",
    messageList: [],
    loginUsers: [],
    userId: "",
    teamInfo: [],
    teamId: "",
    teamName: "",
    teamNumber: "",
  },
  getters: {
    isLogin: (state) => {
      if (state.accessToken) return true;
      return false;
    },
    getUserName: (state) => state.myUserName,
  },
  mutations: {
    // accessToken 저장요청으로 localStorage에 저장
    setToken(state, newAccessToken) {
      state.accessToken = newAccessToken;
      localStorage.setItem("accessToken", newAccessToken);
    },
    setKakaoId(state, newKakaoId) {
      state.kakaoId = newKakaoId;
      localStorage.setItem("kakaoId", newKakaoId);
    },
    setUserName(state, newUserName) {
      state.myUserName = newUserName;
      localStorage.setItem("myUserName", newUserName);
    },
    setUserId(state, newUserId) {
      state.userId = newUserId;
      localStorage.setItem("userId", newUserId);
    },
    deleteToken(state) {
      state.accessToken = "";
      state.kakaoId = "";
      state.myUserName = "";
      state.userId = "";
      localStorage.removeItem("accessToken");
      localStorage.removeItem("kakaoId");
      localStorage.removeItem("myUserName");
      localStorage.removeItem("userId");
    },
    // 카카오 로그인으로 유저정보가 들어왔는지 확인
    SET_USERINFO: function(state, userdata) {
      state.kakaoId = userdata["kakaoId"];
      state.myUserName = userdata["nickName"];
      state.accessToken = userdata["accessToken"];
      state.userEmail = userdata["userEmail"];
      state.profileUrl = userdata["profileUrl"];
      state.sessionState = userdata["sessionState"];
      state.userId = userdata["userId"];
    },
    SET_LOGIN_USERS: function(state, userdata) {
      state.loginUsers = userdata;
    },
    SET_CURRENT_TEAM: function(state, teamInfo) {
      state.teamId = teamInfo.teamId;
      state.teamName = teamInfo.teamName;
      state.teamNumber = teamInfo.teamNumber;
    },
    setTeamId(state, newTeamId) {
      state.teamId = newTeamId;
      //localStorage.setItem("teamId", newTeamId);
    },
    setTeamNumber(state, newTeamNumber) {
      state.teamNumber = newTeamNumber;
      //localStorage.setItem("teamNumber", newTeamNumber);
    },
    setTeamName(state, newTeamName) {
      state.teamName = newTeamName;
      //localStorage.setItem("teamId", newTeamId);
    },
    SET_ProfileUrl(state, newProfileUrl) {
      state.profileUrl = newProfileUrl;
      // localStorage.setItem("profileUrl", newProfileUrl);
    },
  },
  actions: {
    setUserinfo: function({ commit }, userdata) {
      commit("SET_USERINFO", userdata);
    },
    setLoginUsers: function({ commit }, userdata) {
      commit("SET_LOGIN_USERS", userdata);
    },
    setCurrentTeam: function({ commit }, teamInfo) {
      commit("SET_CURRENT_TEAM", teamInfo);
    },
    setProfileUrl: function({ commit }, userprofile) {
      commit("SET_ProfileUrl", userprofile);
    },
  },
});
