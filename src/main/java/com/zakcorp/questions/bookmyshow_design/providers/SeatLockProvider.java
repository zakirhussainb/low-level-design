package com.zakcorp.questions.bookmyshow_design.providers;

import com.zakcorp.questions.bookmyshow_design.model.Seat;
import com.zakcorp.questions.bookmyshow_design.model.Show;

import java.util.List;

public interface SeatLockProvider {
    void lockSeats(Show show, List<Seat> seat, String user);
    void unlockSeats(Show show, List<Seat> seat, String user);
    boolean validateLoc(Show show, Seat set, String user);

    List<Seat> getLockedSeats(Show show);

}
