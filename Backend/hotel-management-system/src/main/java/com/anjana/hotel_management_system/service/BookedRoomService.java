package com.anjana.hotel_management_system.service;

import com.anjana.hotel_management_system.model.BookedRoom;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookedRoomService {
    List<BookedRoom> getAllBookingsByRoomId(Long roomId);

    List<BookedRoom> getAllBookings();

    String saveBooking(Long roomId, BookedRoom bookingRequest);

    BookedRoom  findByBookingConfirmationCode(String confirmationCode);

    void cancelBooking(Long bookingId);
}
