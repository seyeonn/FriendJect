import axios from "axios";
import {
  API_BASE_URL,
  PJT_BASE_URL,
  STU_BASE_URL,
  MEET_BASE_URL,
  CST_BASE_URL,
  PROFILE_BASE_URL,
} from "@/config";

// axios 객체 생성
function apiInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}
function projectRoomInstance() {
  const instance = axios.create({
    baseURL: PJT_BASE_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}
function studyRoomInstance() {
  const instance = axios.create({
    baseURL: STU_BASE_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}
function centerInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}
function meetingRoomInstance() {
  const instance = axios.create({
    baseURL: MEET_BASE_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}
function consultRoomInstance() {
  const instance = axios.create({
    baseURL: CST_BASE_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}
function profileInstance() {
  const instance = axios.create({
    baseURL: PROFILE_BASE_URL,
    headers: {
      "Content-type": "multipart/form-data",
    },
  });
  return instance;
}
function minimeInstance() {
  const instance = axios.create({
    baseURL: PROFILE_BASE_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

export {
  apiInstance,
  projectRoomInstance,
  studyRoomInstance,
  centerInstance,
  meetingRoomInstance,
  consultRoomInstance,
  profileInstance,
  minimeInstance,
};
