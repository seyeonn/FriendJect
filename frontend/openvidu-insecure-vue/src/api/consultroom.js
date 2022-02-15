import { consultRoomInstance } from "./index.js";

const api = consultRoomInstance();

// 해결의 책
function getOneBook(success, fail) {
  api
    .get(`book`)
    .then(success)
    .catch(fail);
}

// 상담실
function getConsultLogList(userId, success, fail) {
  api
    .get(`/${userId}`)
    .then(success)
    .catch(fail);
}

export { getOneBook, getConsultLogList };
