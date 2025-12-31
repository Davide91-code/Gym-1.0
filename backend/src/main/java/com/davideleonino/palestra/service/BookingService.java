package com.davideleonino.palestra.service;

import com.davideleonino.palestra.dto.request.BookingRequest;
import com.davideleonino.palestra.dto.response.BookingInfoDto;
import com.davideleonino.palestra.dto.response.BookingResponse;
import com.davideleonino.palestra.model.*;
import com.davideleonino.palestra.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CourseSessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public BookingResponse bookCourse(String userEmail, BookingRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(userEmail);
        Optional<CourseSession> sessionOpt = sessionRepository.findById(request.getSessionId());

        if (userOpt.isEmpty()) {
            return new BookingResponse(false, "Utente non trovato.");
        }

        if (sessionOpt.isEmpty()) {
            return new BookingResponse(false, "Sessione non valida o inesistente.");
        }

        User user = userOpt.get();
        CourseSession session = sessionOpt.get();

        if (bookingRepository.existsByUserIdAndSessionId(user.getId(), session.getId())) {
            return new BookingResponse(false, "Hai già prenotato questa sessione.");
        }

        Course course = session.getCourse();
        if (course == null) {
            return new BookingResponse(false, "Il corso associato alla sessione non esiste.");
        }

        if (course.getCurrentParticipants() >= course.getMaxParticipants()) {
            return new BookingResponse(false, "Corso al completo.");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setSession(session);
        booking.setBookingDate(LocalDateTime.now());

        bookingRepository.save(booking);

        course.setCurrentParticipants(course.getCurrentParticipants() + 1);

        return new BookingResponse(true, "Prenotazione completata con successo!");
    }

    public List<Booking> getBookingsForUser(String email) {
        return bookingRepository.findAllByUserEmail(email);
    }

    public List<BookingInfoDto> getBookingInfoForUser(String email) {
        List<Booking> bookings = bookingRepository.findAllByUserEmail(email);

        return bookings.stream()
                .filter(b -> b.getSession() != null && b.getSession().getCourse() != null)
                .map(booking -> {
                    CourseSession session = booking.getSession();
                    Course course = session.getCourse();

                    String coachName = course.getCoach() != null ? course.getCoach().getName() : "Coach non assegnato";
                    String timeRange = session.getStartTime() + " - " + session.getEndTime();

                    return new BookingInfoDto(
                            booking.getId(),
                            course.getName(),
                            session.getDayOfWeek().toString(),
                            timeRange,
                            coachName
                    );
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public BookingResponse cancelBooking(String email, Long bookingId) {
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);

        if (bookingOpt.isEmpty()) {
            return new BookingResponse(false, "Prenotazione non trovata.");
        }

        Booking booking = bookingOpt.get();

        if (!booking.getUser().getEmail().equals(email)) {
            return new BookingResponse(false, "Non puoi cancellare questa prenotazione.");
        }

        CourseSession session = booking.getSession();
        if (session == null || session.getCourse() == null) {
            return new BookingResponse(false, "Sessione o corso non valido. Impossibile cancellare.");
        }

        Course course = session.getCourse();
        course.setCurrentParticipants(course.getCurrentParticipants() - 1);

        bookingRepository.delete(booking);

        return new BookingResponse(true, "Prenotazione annullata con successo.");
    }
}