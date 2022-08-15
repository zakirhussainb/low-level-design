package com.zakcorp.questions.bookmyshow_design.service;

import com.zakcorp.questions.bookmyshow_design.exceptions.NotFoundException;
import com.zakcorp.questions.bookmyshow_design.model.Screen;
import com.zakcorp.questions.bookmyshow_design.model.Seat;
import com.zakcorp.questions.bookmyshow_design.model.Theatre;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TheatreService {
    private Map<String, Theatre> theatreStore;
    private Map<String, Screen> screenStore;
    private Map<String, Seat> seatStore;

    public TheatreService() {
        theatreStore = new HashMap<>();
        screenStore = new HashMap<>();
        seatStore = new HashMap<>();
    }

    public Theatre getTheatre(@NonNull final String theatreId) {
        if( !theatreStore.containsKey(theatreId) ) {
            throw new NotFoundException("The requested theatre is not found in our database. " +
                    "Please try with a valid theatre ID");
        }
        return theatreStore.get(theatreId);
    }

    public Screen getScreen(@NonNull final String screenId) {
        if ( !screenStore.containsKey(screenId) ) {
            throw new NotFoundException("The requested screen is not found in our database. " +
                    "Please try with a valid screen ID");
        }
        return screenStore.get(screenId);
    }

    public Seat getSeat(@NonNull final String seatId) {
        if ( !seatStore.containsKey(seatId) ) {
            throw new NotFoundException("The requested seat is not found in our database. " +
                    "Please try with a valid seat ID");
        }
        return seatStore.get(seatId);
    }

    public Theatre createTheatre(@NonNull final String theatreName) {
        String theatreId = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(theatreId, theatreName);
        theatreStore.put(theatreId, theatre);
        return theatre;
    }


    public Screen createScreenInTheatre(@NonNull final String screenName, @NonNull final Theatre theatre) {
        String screenId = UUID.randomUUID().toString();
        Screen screen = new Screen(screenId, screenName, theatre);
        screenStore.put(screenId, screen);
        theatre.addScreen(screen); // You need to perform this step, because initially the theatre will have no screens
        return screen;
    }

    public Seat createSeatInScreen(@NonNull final int rowNo, @NonNull final int seatNo, @NonNull final Screen screen) {
        String seatId = UUID.randomUUID().toString();
        Seat seat = new Seat(seatId, rowNo, seatNo);
        seatStore.put(seatId, seat);
        screen.addSeat(seat);
        return seat;
    }

}
