import axios from "axios"

const axios_contact = axios.create({
    // baseURL:"http://i6c102.p.ssafy.io:8080/",
    baseURL:"http://localhost:8080/",
});

export { axios_contact }