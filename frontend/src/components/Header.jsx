import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/Header.css';
import { useAuth } from '../context/AuthContext';

const Header = () => {
  const [menuOpen, setMenuOpen] = useState(false);
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    setMenuOpen(false);
    navigate('/');
  };

  return (
    <header className="header">
      <div className="header-section-left">
        <button className="menu-btn" onClick={() => setMenuOpen(!menuOpen)}>☰</button>
      </div>

      <div className="header-section-center">
        <h1 className="logo">Endorphin Factory</h1>
      </div>

      <div className="header-section-right">
        {user && <span className="welcome-text">👋 Ciao, {user.name}</span>}
        {user && (
          <button onClick={handleLogout} className="logout-button">
            🔓 Logout
          </button>
        )}
      </div>

      {menuOpen && (
        <div className="side-menu">
          <ul>
            <li><Link to="/" onClick={() => setMenuOpen(false)}>Home</Link></li>
            <li><Link to="/corsi" onClick={() => setMenuOpen(false)}>Corsi</Link></li>
            <li><Link to="/categorie" onClick={() => setMenuOpen(false)}>Categorie</Link></li>
            <li><Link to="/contatti" onClick={() => setMenuOpen(false)}>Contatti</Link></li>

            {user ? (
              <>
                <li><Link to="/prenotazioni" onClick={() => setMenuOpen(false)}>Le mie prenotazioni</Link></li>
              </>
            ) : (
              <>
                <li><Link to="/login" onClick={() => setMenuOpen(false)}>Login</Link></li>
                <li><Link to="/register" onClick={() => setMenuOpen(false)}>Registrati</Link></li>
              </>
            )}
          </ul>
        </div>
      )}
    </header>
  );
};

export default Header;