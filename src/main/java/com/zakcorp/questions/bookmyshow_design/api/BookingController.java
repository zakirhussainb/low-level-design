package com.zakcorp.questions.bookmyshow_design.api;

import com.zakcorp.questions.bookmyshow_design.model.Booking;
import com.zakcorp.questions.bookmyshow_design.model.Seat;
import com.zakcorp.questions.bookmyshow_design.model.Show;
import com.zakcorp.questions.bookmyshow_design.service.BookingService;
import com.zakcorp.questions.bookmyshow_design.service.ShowService;
import com.zakcorp.questions.bookmyshow_design.service.TheatreService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BookingController {

    private final ShowService showService;
    private final BookingService bookingService;
    private final TheatreService theatreService;

    public String createBooking(@NonNull final String userId, @NonNull final String showId,
                                 @NonNull final List<String> seatsIds) {
        final Show show = showService.getShow(showId);
        final List<Seat> seats = seatsIds.stream().map(theatreService::getSeat).collect(Collectors.toList());
        return bookingService.createBooking(show, seats, userId).getId();
    }

}
