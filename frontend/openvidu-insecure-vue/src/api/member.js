import { apiInstance } from "./index.js";

const api = apiInstance();

function getMember(userid, success, fail) {
  console.log("api start", userid);
    api.get(`/studyroom/${userid}`).then(success).catch(fail);
  }

  function regMember(user, success, fail) {
    api.post(`/studyroom`, JSON.stringify(user)).then(success).catch(fail);
  }

  function regTime(user, success, fail) {
    api.put(`/studyroom/saveTime`, JSON.stringify(user)).then(success).catch(fail);
  }

  function getStudyBest(success, fail) {
    api.get(`/studyroom`)
    .then(success).catch(fail);
  }

  export {
    getMember,
    regMember,
    regTime,
    getStudyBest
  };