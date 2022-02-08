import { projectRoomInstance } from "./index.js";

const api = projectRoomInstance();

function getFileInfo(fileId, success, fail) {
  api
    .get(`files/${fileId}`)
    .then(success)
    .catch(fail);
}

function getFileList(success, fail) {
  api
    .get(`files`)
    .then(success)
    .catch(fail);
}

function registerFile(file, success, fail) {
  api
    .post(`files`, file, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    })
    .then(success)
    .catch(fail);
}

export { getFileInfo, getFileList, registerFile };
