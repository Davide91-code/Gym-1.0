import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api', // 🔁 Adatta alla tua base URL
  withCredentials: true, // Se usi cookie per autenticazione
});

// ✅ Interceptor per aggiungere token (opzionale)
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default api;