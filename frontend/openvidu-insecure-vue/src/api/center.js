import { icebreakingInstance } from "./index.js";

const api = icebreakingInstance();

function getQuestionList(success, fail) {
  api
    .get(`questions`)
    .then(success)
    .catch(fail);
}

export { getQuestionList };
