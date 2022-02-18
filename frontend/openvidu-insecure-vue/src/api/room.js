import { profileInstance, minimeInstance } from "./index.js";

const api = profileInstance();
const api_v2 = minimeInstance();

function changProfile(email, imgfrm, success, fail) {
    api
      .patch(`${email}`, imgfrm)
      .then(success)
      .catch(fail);
  }

function selectMinimi(email, profileUrl, success, fail) {
  api_v2
      .patch(`minime/${email}`, profileUrl)
      .then(success)
      .catch(fail);
  }
  
export { 
  changProfile,
  selectMinimi
};