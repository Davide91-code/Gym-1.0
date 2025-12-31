import api from './api';

export async function getCategorie() {
  const response = await api.get('/categories');
  return response.data;
}