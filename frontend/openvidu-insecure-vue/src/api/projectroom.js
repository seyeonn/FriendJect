import { projectRoomInstance } from "./index.js";

const api = projectRoomInstance();

// 자료실 APIs
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

// 메모보드 APIs
function getMemoList(param, success, fail) {
  api
    .get(`memo`, {
      params: {
        status: param,
      },
    })
    .then(success)
    .catch(fail);
}

function registerMemo(memo, success, fail) {
  api
    .post(`memo`, JSON.stringify(memo))
    .then(success)
    .catch(fail);
}

function registerMemoList(memolist, param, success, fail) {
  api
    .post(`memo/list`, JSON.stringify(memolist), {
      params: {
        status: param,
      },
    })
    .then(success)
    .catch(fail);
}

function deleteMemo(memoId, success, fail) {
  api.delete(`memo/${memoId}`)
}

export {
  getFileInfo,
  getFileList,
  registerFile,
  getMemoList,
  registerMemo,
  registerMemoList,
};
