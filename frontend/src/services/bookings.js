import api from './api';

export const getMyBookings = async () => {
  const response = await api.get('/bookings/mine/info');
  return response.data;
};

export const deleteBooking = async (id) => {
  await api.delete(`/bookings/${id}`);
};

export const bookSession = async (sessionId) => {
  const response = await api.post('/bookings', { sessionId });
  return response.data;
};