package com.zakcorp.questions.bookmyshow_design.model;

import com.sun.jdi.request.InvalidRequestStateException;
import lombok.Getter;
import lombok.NonNull;
import java.util.List;

@Getter
public class Booking {
    private final String id;
    private final Show show;
    private final List<Seat> seatsBooked;
    private final String user;
    private BookingStatus bookingStatus;

    public Booking(@NonNull final String id, @NonNull final Show show, @NonNull final List<Seat> seatsBooked,
                   @NonNull final String user) {
        this.id = id;
        this.show = show;
        this.seatsBooked = seatsBooked;
        this.user = user;
        this.bookingStatus = BookingStatus.Created;
    }

    public boolean isConfirmed() {
        return this.bookingStatus == BookingStatus.Confirmed;
    }

    public void confirmBooking() {
        if(this.bookingStatus != BookingStatus.Created)
            throw new InvalidRequestStateException();
        this.bookingStatus = BookingStatus.Confirmed;
    }

    public void expireBooking() {
        if(this.bookingStatus != BookingStatus.Created)
            throw new InvalidRequestStateException();
        this.bookingStatus = BookingStatus.Expired;
    }
}
