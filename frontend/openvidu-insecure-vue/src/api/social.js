import { apiInstance } from "./index.js";

const api = apiInstance();

function socialLogin(code, success, fail) {
  api
    .post(`login/kakao/${code}`)
    .then(success)
    .catch(fail);
}

export { socialLogin };
