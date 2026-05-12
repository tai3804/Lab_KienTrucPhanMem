import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_ORCHESTRATOR_URL || "http://192.168.137.178:8080"
});

export default api;
