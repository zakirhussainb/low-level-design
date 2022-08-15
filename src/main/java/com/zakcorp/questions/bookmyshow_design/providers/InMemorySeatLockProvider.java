package com.zakcorp.questions.bookmyshow_design.providers;

import com.zakcorp.questions.bookmyshow_design.exceptions.SeatTemporaryUnavailableException;
import com.zakcorp.questions.bookmyshow_design.model.Seat;
import com.zakcorp.questions.bookmyshow_design.model.SeatLock;
import com.zakcorp.questions.bookmyshow_design.model.Show;
import lombok.NonNull;

import java.util.*;

public class InMemorySeatLockProvider implements SeatLockProvider {

    private final Integer lockTimeout; // Bonus feature
    private final Map<Show, Map<Seat, SeatLock>> locks;

    public InMemorySeatLockProvider(@NonNull final Integer lockTimeout) {
        this.locks = new HashMap<>();
        this.lockTimeout = lockTimeout;
    }

    @Override
    synchronized public void lockSeats(Show show, List<Seat> seats, String user) {
        for(Seat seat : seats) {
            if( isSeatLocked(show, seat) ) {
                throw new SeatTemporaryUnavailableException("The seats you are requesting are temporary unavailable, " +
                        "Please select other seats or wait for sometime till these get available");
            }
        }
        for(Seat seat : seats) {
            lockSeat(show, seat, user, lockTimeout);
        }
    }

    @Override
    public void unlockSeats(Show show, List<Seat> seats, String user) {
        for(Seat seat : seats) {
            if(validateLoc(show, seat, user)) {
                unlockSeat(show, seat);
            }
        }
    }

    @Override
    public boolean validateLoc(Show show, Seat seat, String user) {
        return isSeatLocked(show, seat) && locks.get(show).get(seat).getLockedBy().equals(user);
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        List<Seat> lockedSeats = new ArrayList<>();
        if( !locks.containsKey(show) )
            return lockedSeats;
        for(Seat seat : locks.get(show).keySet()) {
            if( isSeatLocked(show, seat) ) {
                lockedSeats.add(seat);
            }
        }
        return lockedSeats;
    }

    private boolean isSeatLocked(Show show, Seat seat) {
        return locks.containsKey(show) && locks.get(show).containsKey(seat) &&
                !locks.get(show).get(seat).isLockExpired();
    }

    private void lockSeat(final Show show, final Seat seat, final String user, final Integer timeoutInSeconds) {
        if ( !locks.containsKey(show) ) {
            locks.put(show, new HashMap<>());
        }
        final SeatLock lock = new SeatLock(seat, show, timeoutInSeconds, new Date(), user);
        locks.get(show).put(seat, lock);
    }

    private void unlockSeat(Show show, Seat seat) {
        if( !locks.containsKey(show) )
            return;
        if( !locks.get(show).containsKey(seat) ) {
            return;
        }
        locks.get(show).remove(seat);
    }
}
