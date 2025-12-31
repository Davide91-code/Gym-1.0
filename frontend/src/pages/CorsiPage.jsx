import React, { useEffect, useState } from 'react';
import CourseList from '../components/CourseList';
import { getCorsi } from '../services/corsi';

const CorsiPage = () => {
  const [courses, setCourses] = useState([]);

  useEffect(() => {
    const fetchCourses = async () => {
      try {
        const data = await getCorsi(); // ← chiama API senza parametri
        setCourses(data);
      } catch (error) {
        console.error('Errore nel caricamento dei corsi:', error);
      }
    };

    fetchCourses();
  }, []);

  return (
    <div className="corsi-page">
      <h2>Corsi Disponibili</h2>
      <CourseList courses={courses} />
    </div>
  );
};

export default CorsiPage;