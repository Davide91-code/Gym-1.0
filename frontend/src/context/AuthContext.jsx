import React, { createContext, useContext, useEffect, useState } from 'react';
import { login as apiLogin, getProfile } from '../services/auth';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [authReady, setAuthReady] = useState(false);

  const tokenKey = 'token';

  // Leggi token da localStorage
  const getToken = () => localStorage.getItem(tokenKey);

  const saveToken = (token) => localStorage.setItem(tokenKey, token);
  const clearToken = () => localStorage.removeItem(tokenKey);

  const login = async (email, password) => {
    try {
      const { token } = await apiLogin({ email, password });
      saveToken(token);

      const userData = await getProfile();
      setUser(userData);

      return { success: true };
    } catch (error) {
      console.error('Errore nel login:', error);
      return { success: false, message: 'Credenziali non valide' };
    }
  };

  const logout = () => {
    clearToken();
    setUser(null);
  };

  const checkAuth = async () => {
    const token = getToken();
    if (!token) {
      setAuthReady(true);
      return;
    }

    try {
      const userData = await getProfile();
      setUser(userData);
    } catch (error) {
      console.warn('Token non valido o scaduto');
      logout();
    } finally {
      setAuthReady(true);
    }
  };

  useEffect(() => {
    checkAuth();
  }, []);

  return (
    <AuthContext.Provider value={{ user, login, logout, authReady }}>
      {children}
    </AuthContext.Provider>
  );
};

// Custom hook per usare l'autenticazione
export const useAuth = () => useContext(AuthContext);