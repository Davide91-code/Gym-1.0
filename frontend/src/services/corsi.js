import api from './api';

/**
 * Recupera tutti i corsi.
 * Se viene passato un categoryId, filtra i corsi per categoria.
 */
export const getCorsi = (categoryId) => {
  if (categoryId) {
    return api.get('/courses', {
      params: { categoryId }
    }).then(res => res.data);
  }

  return api.get('/courses').then(res => res.data);
};

/**
 * Recupera un singolo corso per ID.
 * Usato per la pagina di dettaglio corso.
 */
export const getCorsoById = (id) => {
  return api.get(`/courses/${id}`).then(res => res.data);
};

/**
 * Crea un nuovo corso (opzionale, se avrai pannello admin).
 */
export const createCorso = (corsoData) => {
  return api.post('/courses', corsoData).then(res => res.data);
};

/**
 * Modifica un corso esistente (opzionale, se avrai pannello admin).
 */
export const updateCorso = (id, corsoData) => {
  return api.put(`/courses/${id}`, corsoData).then(res => res.data);
};

/**
 * Elimina un corso (opzionale, se avrai pannello admin).
 */
export const deleteCorso = (id) => {
  return api.delete(`/courses/${id}`).then(res => res.data);
};