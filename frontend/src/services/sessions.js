import api from './api';

export const getSessionsForCourse = async (courseId) => {
  const response = await api.get(`/sessions/course/${courseId}`);
  return response.data;
};