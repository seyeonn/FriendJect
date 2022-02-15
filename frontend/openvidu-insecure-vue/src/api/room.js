import { profileInstance } from "./index.js";

const api = profileInstance();

function changProfile(email, imgfrm, success, fail) {
    api
      .patch(`${email}`, imgfrm)
      .then(success)
      .catch(fail);
  }
  
  export { changProfile };