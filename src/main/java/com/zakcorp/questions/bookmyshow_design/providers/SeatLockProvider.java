package com.zakcorp.questions.bookmyshow_design.providers;

import com.zakcorp.questions.bookmyshow_design.model.Seat;
import com.zakcorp.questions.bookmyshow_design.model.Show;

import java.util.List;

public interface SeatLockProvider {
    void lockSeats(Show show, List<Seat> seats, String user);
    void unlockSeats(Show show, List<Seat> seats, String user);
    boolean validateLoc(Show show, Seat seat, String user);

    List<Seat> getLockedSeats(Show show);

}
