import axios from "axios";
const BASE_URL='http://localhost:8080/api/v1/'
export const placeorder=(order)=>axios.post(`${BASE_URL}orders`,order)
