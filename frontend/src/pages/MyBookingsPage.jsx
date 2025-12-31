import React, { useEffect, useState } from 'react';
import { getMyBookings, deleteBooking } from '../services/bookings';

const MyBookingsPage = () => {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);

  const loadBookings = async () => {
    try {
      const data = await getMyBookings();
      setBookings(data);
    } catch (err) {
      console.error('Errore nel caricamento prenotazioni', err);
    } finally {
      setLoading(false);
    }
  };

  const handleCancel = async (id) => {
    if (window.confirm('Annullare questa prenotazione?')) {
      try {
        await deleteBooking(id);
        await loadBookings();
      } catch (err) {
        console.error('Errore nella cancellazione', err);
      }
    }
  };

  useEffect(() => {
    loadBookings();
  }, []);

  if (loading) return <p>Caricamento...</p>;

  const translateDay = (dayOfWeek) => {
  const days = {
    MONDAY: 'Lunedì',
    TUESDAY: 'Martedì',
    WEDNESDAY: 'Mercoledì',
    THURSDAY: 'Giovedì',
    FRIDAY: 'Venerdì',
    SATURDAY: 'Sabato',
    SUNDAY: 'Domenica',
  };
  return days[dayOfWeek] || dayOfWeek;
};

  return (
    <div className="my-bookings-page">
      <h2>Le mie prenotazioni</h2>
      {bookings.length === 0 ? (
        <p>Non hai ancora prenotazioni.</p>
      ) : (
        <ul>
          {bookings.map((b) => (
            <li key={b.bookingId}>
  <strong>{b.courseName}</strong><br />
  {translateDay(b.dayOfWeek)}, {b.timeRange}<br />
  <em>Coach: {b.coachName}</em>
  <br />
  <button onClick={() => handleCancel(b.bookingId)} style={{ marginTop: '5px' }}>
    Annulla
  </button>
</li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default MyBookingsPage;