import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getCategorie } from '../services/categorie';
import '../styles/CategoriePage.css';

const CategoriePage = () => {
  const [categorie, setCategorie] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    async function fetchData() {
      try {
        const data = await getCategorie();
        setCategorie(data);
      } catch (error) {
        console.error('Errore durante il fetch delle categorie:', error);
      }
    }
    fetchData();
  }, []);

  const handleClick = (category) => {
    navigate(`/categorie/dettaglio?categoryId=${category.id}`, {
      state: { name: category.name },
    });
  };

  const getImageForCategory = (categoryName) => {
    const normalized = categoryName.toLowerCase().replace(/[\s\-&]/g, '');

    console.log('Normalized category:', normalized);

    const imageMap = {
      mindbody: '/images/mindbody.jpg',
      selfdefense: '/images/selfdefense.jpg',
      posturale: '/images/posturale.jpg',
    };

    return imageMap[normalized] || '/images/categorie/placeholder.jpg';
  };

  return (
    <div className="categorie-page">
      <h2 className="categorie-title">Categorie</h2>
      <div className="categorie-grid">
        {categorie.map((category) => (
          <div
            key={category.id}
            className="categoria-card"
            onClick={() => handleClick(category)}
          >
            <img
              src={getImageForCategory(category.name)}
              alt={category.name}
              className="categoria-img"
            />
            <h3>{category.name}</h3>
          </div>
        ))}
      </div>
    </div>
  );
};

export default CategoriePage;