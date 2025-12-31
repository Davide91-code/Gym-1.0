package com.davideleonino.palestra.repository;

import com.davideleonino.palestra.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Long userId);

    List<Booking> findBySessionId(Long sessionId);

    boolean existsByUserIdAndSessionId(Long userId, Long sessionId);

    List<Booking> findAllByUserEmail(String email);
}