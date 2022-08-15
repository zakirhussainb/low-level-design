package com.zakcorp.questions.bookmyshow_design.providers;

import com.zakcorp.questions.bookmyshow_design.exceptions.SeatTemporaryUnavailableException;
import com.zakcorp.questions.bookmyshow_design.model.Seat;
import com.zakcorp.questions.bookmyshow_design.model.Show;

import java.util.List;

public class InMemorySeatLockProvider implements SeatLockProvider {

    @Override
    synchronized public void lockSeats(Show show, List<Seat> seats, String user) {
        for(Seat seat : seats) {
            if( isSeatLocked(show, seat) ) {
                throw new SeatTemporaryUnavailableException();
            }
        }
        for(Seat seat : seats) {
            lockSeat(show, seat, user, lockTimeout);
        }
    }

    @Override
    public void unlockSeats(Show show, List<Seat> seat, String user) {

    }

    @Override
    public boolean validateLoc(Show show, Seat set, String user) {
        return false;
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        return null;
    }

    private boolean isSeatLocked(Show show, Seat seat) {

    }
}
