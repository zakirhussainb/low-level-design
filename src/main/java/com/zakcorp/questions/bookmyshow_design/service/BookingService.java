package com.zakcorp.questions.bookmyshow_design.service;

import com.sun.jdi.request.InvalidRequestStateException;
import com.zakcorp.questions.bookmyshow_design.exceptions.NotFoundException;
import com.zakcorp.questions.bookmyshow_design.exceptions.SeatPermanentlyUnavailableException;
import com.zakcorp.questions.bookmyshow_design.model.Booking;
import com.zakcorp.questions.bookmyshow_design.model.Seat;
import com.zakcorp.questions.bookmyshow_design.model.Show;
import com.zakcorp.questions.bookmyshow_design.providers.SeatLockProvider;
import lombok.NonNull;

import java.util.*;
import java.util.stream.Collectors;

public class BookingService {
    Map<String, Booking> bookingStore;
    private final SeatLockProvider seatLockProvider;

    public BookingService(SeatLockProvider seatLockProvider) {
        this.seatLockProvider = seatLockProvider;
        bookingStore = new HashMap<>();
    }

    public Booking createBooking(@NonNull final Show show, @NonNull final List<Seat> seatList,
                                 @NonNull final String userId) {
        if( isAnySeatAlreadyBooked(show, seatList) ) {
            throw new SeatPermanentlyUnavailableException("The seats you are requesting are already booked, " +
                    "Please select the other available seats");
        }

        // Lock the seats that the user has requested.
        seatLockProvider.lockSeats(show, seatList, userId);
        String bookingId = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingId, show, seatList, userId);
        bookingStore.put(bookingId, booking);
        return booking;
    }

    public Booking getBooking(@NonNull final String bookingId) {
        if( !bookingStore.containsKey(bookingId) ) {
            throw new NotFoundException("The requested booking is not found in our database. " +
                    "Please try with a valid booking ID");
        }
        return bookingStore.get(bookingId);
    }

    private List<Booking> getAllBookings(@NonNull final Show show) {
        List<Booking> allBookings = new ArrayList<>();
        for(Booking booking : bookingStore.values()) {
            if( booking.getShow().equals(show) ) {
                allBookings.add(booking);
            }
        }
        return allBookings;
    }

    public List<Seat> getBookedSeats(@NonNull final Show show) {
        return getAllBookings(show).stream()
                .filter(Booking::isConfirmed)
                .map(Booking::getSeatsBooked)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private boolean isAnySeatAlreadyBooked(Show show, List<Seat> seatList) {
        final List<Seat> bookedSeats = getBookedSeats(show);
        for(Seat seat : seatList) {
            if(bookedSeats.contains(seat))
                return true;
        }
        return false;
    }

    public void confirmBooking(@NonNull final Booking booking, @NonNull final String user) {
        if(booking.getUser().equals(user)) {
            throw new InvalidRequestStateException();
        }
        for(Seat seat : booking.getSeatsBooked()) {
            if (!seatLockProvider.validateLoc(booking.getShow(), seat, user)) {
                throw new InvalidRequestStateException();
            }
        }
        booking.confirmBooking();
    }
}
