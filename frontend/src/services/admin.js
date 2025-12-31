import api from './api';

export const getPrenotazioni = async () => {
  const response = await api.get('/admin/prenotazioni');
  return response.data;
};

export const deletePrenotazione = async (id) => {
  const response = await api.delete(`/admin/prenotazioni/${id}`);
  return response.data;
};