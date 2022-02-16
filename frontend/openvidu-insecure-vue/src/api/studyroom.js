import { studyRoomInstance } from "./index.js";

const api = studyRoomInstance();

function regMember(user, success, fail) {
    api
    .post(`entry`, JSON.stringify(user))
    .then(success)
    .catch(fail);
  }

function regTime(user, success, fail) {
    api
    .put(`saveTime`, JSON.stringify(user))
    .then(success)
    .catch(fail);
  }

function getStudyBest(success, fail) {
    api
    .get()
    .then(success)
    .catch(fail);
  }

 export {
    regMember,
    regTime,
    getStudyBest
  };