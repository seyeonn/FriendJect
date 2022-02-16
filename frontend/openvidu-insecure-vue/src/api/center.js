import { apiInstance } from "./index.js";

const api = apiInstance();

function getQuestionList(success, fail) {
  api
    .get(`icebreaking/questions`)
    .then(success)
    .catch(fail);
}

function getOneTeam(param, success, fail) {
  api
    .get("team", {
      params: {
        teamNumber: param,
      },
    })
    .then(success)
    .catch(fail);
}

function joinTeam(teamInfo, success, fail) {
  api
    .post(`team/join`, JSON.stringify(teamInfo))
    .then(success)
    .catch(fail);
}

function createTeam(teamRegInfo, success, fail) {
  api
    .post(`team/create`, JSON.stringify(teamRegInfo))
    .then(success)
    .catch(fail);
}

export { getQuestionList, getOneTeam, joinTeam, createTeam };
