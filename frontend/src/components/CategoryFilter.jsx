import React, { useEffect, useState } from 'react';
import { getCategorie } from '../services/categorie';

const CategoryFilter = ({ selectedCategoryId, onCategorySelect }) => {
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    const fetchCategorie = async () => {
      try {
        const data = await getCategorie();
        setCategories(data);
      } catch (error) {
        console.error('Errore nel caricamento delle categorie:', error);
      }
    };

    fetchCategorie();
  }, []);

  return (
    <div className="category-filter">
      <button
        className={!selectedCategoryId ? 'active' : ''}
        onClick={() => onCategorySelect(null)}
      >
        Tutti
      </button>

      {categories.map((category) => (
        <button
          key={category.id}
          className={selectedCategoryId === category.id ? 'active' : ''}
          onClick={() => onCategorySelect(category.id)}
        >
          {category.name}
        </button>
      ))}
    </div>
  );
};

export default CategoryFilter;