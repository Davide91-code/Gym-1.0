package com.davideleonino.palestra.controller;

import com.davideleonino.palestra.dto.request.BookingRequest;
import com.davideleonino.palestra.dto.response.BookingResponse;
import com.davideleonino.palestra.dto.response.BookingInfoDto;
import com.davideleonino.palestra.model.Booking;
import com.davideleonino.palestra.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(
            @Valid @RequestBody BookingRequest request,
            Authentication authentication
    ) {
        String email = authentication.getName();
        BookingResponse response = bookingService.bookCourse(email, request);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Endpoint legacy: restituisce oggetti Booking "grezzi"
    @GetMapping("/mine")
    public ResponseEntity<List<Booking>> getMyBookings(Authentication authentication) {
        String email = authentication.getName();
        List<Booking> bookings = bookingService.getBookingsForUser(email);
        return ResponseEntity.ok(bookings);
    }

    // Nuovo endpoint: restituisce BookingInfoDto (DTO con dati leggibili)
    @GetMapping("/mine/info")
    public ResponseEntity<List<BookingInfoDto>> getMyBookingInfo(Authentication authentication) {
        String email = authentication.getName();
        List<BookingInfoDto> bookingInfoList = bookingService.getBookingInfoForUser(email);
        return ResponseEntity.ok(bookingInfoList);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<BookingResponse> cancelBooking(
            @PathVariable Long bookingId,
            Authentication authentication
    ) {
        String email = authentication.getName();
        BookingResponse response = bookingService.cancelBooking(email, bookingId);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}