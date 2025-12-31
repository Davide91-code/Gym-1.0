import React, { useEffect, useState } from 'react';
import { getCorsi } from '../services/corsi';
import CategoryFilter from '../components/CategoryFilter';
import CourseList from '../components/CourseList';

const HomePage = ({ selectedCategoryId, setSelectedCategoryId }) => {
  const [courses, setCourses] = useState([]);

  useEffect(() => {
    const fetchCourses = async () => {
      try {
        const data = await getCorsi(selectedCategoryId);
        setCourses(data);
      } catch (error) {
        console.error('Errore nel caricamento dei corsi:', error);
      }
    };

    fetchCourses();
  }, [selectedCategoryId]);

  return (
    <>
      <section className="hero-section">
        <img
          src="/images/MainPage.jpg"
          alt="Palestra Hero"
          className="hero-image"
        />
      </section>

      <CategoryFilter
        selectedCategoryId={selectedCategoryId}
        onCategorySelect={setSelectedCategoryId}
      />

      <CourseList courses={courses} />
    </>
  );
};

export default HomePage;