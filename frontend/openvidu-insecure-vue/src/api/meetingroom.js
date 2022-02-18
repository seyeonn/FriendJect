import { meetingRoomInstance } from "./index.js";

const api = meetingRoomInstance();

// 회의록 APIs
function getOneDocument(targetId, success, fail) {
  api
    .get(`document/${targetId}`)
    .then(success)
    .catch(fail);
}

function getDocumentList(teamId, success, fail) {
  api
    .get(`document/list/${teamId}`)
    .then(success)
    .catch(fail);
}

function registerDocument(documentItem, success, fail) {
  api
    .post(`document/`, documentItem)
    .then(success)
    .catch(fail);
}

function modifyDocument(targetId, documentItem, success, fail) {
  api
    .put(`document/${targetId}`, documentItem)
    .then(success)
    .catch(fail);
}

function removeDocument(targetId, success, fail) {
  api
    .delete(`document/${targetId}`)
    .then(success)
    .catch(fail);
}

export {
  getOneDocument,
  getDocumentList,
  registerDocument,
  modifyDocument,
  removeDocument,
};
