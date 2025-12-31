import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

import { getCorsoById } from '../services/corsi';
import { getSessionsForCourse } from '../services/sessions';
import { bookSession } from '../services/bookings';

import '../styles/CourseDetails.css';

const CourseDetails = () => {
  const { id } = useParams();
  const [course, setCourse] = useState(null);
  const [sessions, setSessions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [bookingMessage, setBookingMessage] = useState('');
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchCourseAndSessions = async () => {
      try {
        const corso = await getCorsoById(id);
        const sessioni = await getSessionsForCourse(id);

        setCourse(corso);
        setSessions(sessioni);
      } catch (err) {
        console.error('Errore nel recupero:', err);
        setError('Impossibile caricare il corso o le sessioni.');
      } finally {
        setLoading(false);
      }
    };

    fetchCourseAndSessions();
  }, [id]);

  const handleBooking = async (sessionId) => {
    try {
      const response = await bookSession(sessionId);
      setBookingMessage(response.message || 'Prenotazione effettuata!');
    } catch (err) {
      console.error('Errore prenotazione:', err);
      setBookingMessage('Errore durante la prenotazione.');
    }
  };

  const getCourseImage = (courseName) => {
    const normalized = courseName.toLowerCase().replace(/[\s\-]/g, '');
    return `/images/${normalized}.jpg`;
  };

  if (loading) return <p>Caricamento...</p>;
  if (error) return <p>{error}</p>;
  if (!course) return <p>Corso non trovato.</p>;

  return (
    <div className="course-details">
      <h2>{course.name}</h2>
      <img
        src={getCourseImage(course.name)}
        alt={course.name}
        className="course-image"
        onError={(e) => (e.target.src = '/images/corsi/placeholder.jpg')}
      />
      <p><strong>Categoria:</strong> {course.categoryName}</p>
      <p><strong>Coach:</strong> {course.coachName}</p>
      <p className="description">{course.description}</p>

      <h3>Sessioni disponibili</h3>
      {sessions.length === 0 ? (
        <p>Nessuna sessione disponibile per questo corso.</p>
      ) : (
        <ul className="session-list">
          {sessions.map((session) => (
            <li key={session.id} className="session-item">
              <span><strong>{session.dayOfWeek}</strong> – {session.startTime} - {session.endTime}</span>
              <button onClick={() => handleBooking(session.id)}>Prenota</button>
            </li>
          ))}
        </ul>
      )}

      {bookingMessage && <p className="booking-message">{bookingMessage}</p>}
    </div>
  );
};

export default CourseDetails;