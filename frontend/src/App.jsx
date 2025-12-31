import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Header from './components/Header';
import Footer from './components/Footer';

import HomePage from './pages/HomePage';
import CorsiPage from './pages/CorsiPage';
import CategoriePage from './pages/CategoriePage';
import CategoriaDettaglioPage from './pages/CategoriaDettaglioPage';
import CourseDetails from './components/CourseDetails';
import ContattiPage from './pages/ContattiPage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import MyBookingsPage from './pages/MyBookingsPage'; 

import { AuthProvider } from './context/AuthContext';
import PrivateRoute from './components/PrivateRoute'; // wrapper route protette

import './App.css';

const App = () => {
  const [selectedCategoryId, setSelectedCategoryId] = useState(null);

  return (
    <AuthProvider>
      <Router>
        <div className="app-container">
          <Header />

          <main className="main-content">
            <Routes>
              <Route
                path="/"
                element={
                  <HomePage
                    selectedCategoryId={selectedCategoryId}
                    setSelectedCategoryId={setSelectedCategoryId}
                  />
                }
              />

              <Route path="/corsi" element={<CorsiPage />} />
              <Route path="/categorie" element={<CategoriePage />} />
              <Route path="/categorie/dettaglio" element={<CategoriaDettaglioPage />} />
              <Route path="/corsi/:id" element={<CourseDetails />} />
              <Route path="/contatti" element={<ContattiPage />} />

              {/* Login e registrazione */}
              <Route path="/login" element={<LoginPage />} />
              <Route path="/register" element={<RegisterPage />} />

              {/* Rotta protetta */}
              <Route
                path="/prenotazioni"
                element={
                  <PrivateRoute>
                    <MyBookingsPage />
                  </PrivateRoute>
                }
              />

              {/* Fallback 404 */}
              <Route path="*" element={<p style={{ padding: 20 }}>Pagina non trovata</p>} />
            </Routes>
          </main>

          <Footer />
        </div>
      </Router>
    </AuthProvider>
  );
};

export default App;