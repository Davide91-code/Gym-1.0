import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/CourseList.css';

const getImageForCourse = (courseName) => {
  const normalized = courseName.toLowerCase().replace(/[\s\-]/g, '');
  
  console.log("Course name processed:", normalized); // Debug

  const imageMap = {
    yoga: '/images/yoga.jpg',
    pilates: '/images/pilates.jpg',
    kravmaga: '/images/kravmaga.jpg',
    kickboxing: '/images/kickboxing.jpg',
    judo: '/images/judo.jpg',
    risvegliomuscolare: '/images/risvegliomuscolare.jpg',
    aerobica: '/images/aerobica.jpg',
    brazilianjiujitsu: '/images/brazilianjiujitsu.jpg',
  };

  return imageMap[normalized] || '/images/corsi/placeholder.jpg';
};

const CourseList = ({ courses }) => {
  return (
    <div className="course-list">
      {courses.map(course => (
        <Link to={`/corsi/${course.id}`} key={course.id} className="course-card">
          <div
            className="course-image"
            style={{
              backgroundImage: `url(${getImageForCourse(course.name)})`,
            }}
          />
          <div className="course-footer">
            <h3>{course.name}</h3>
          </div>
        </Link>
      ))}
    </div>
  );
};

export default CourseList;