package com.zakcorp.questions.bookmyshow_design.service;

import com.zakcorp.questions.bookmyshow_design.exceptions.NotFoundException;
import com.zakcorp.questions.bookmyshow_design.exceptions.ScreenAlreadyOccupiedException;
import com.zakcorp.questions.bookmyshow_design.model.Movie;
import com.zakcorp.questions.bookmyshow_design.model.Screen;
import com.zakcorp.questions.bookmyshow_design.model.Show;
import lombok.NonNull;

import java.util.*;

public class ShowService {

    private final Map<String, Show> showStore;

    public ShowService() {
        this.showStore = new HashMap<>();
    }

    public Show getShow(@NonNull final String showId) {
        if( !showStore.containsKey(showId) ) {
            throw new NotFoundException("The requested show is not found in our database. " +
                    "Please try with a valid show ID");
        }
        return showStore.get(showId);
    }

    public Show createShow(@NonNull final Movie movie, @NonNull final Screen screen, @NonNull final Date startTime,
                           @NonNull final Integer durationInSeconds) {
        if ( !checkIfShowCreationAllowed(screen, startTime, durationInSeconds ) ) {
            throw new ScreenAlreadyOccupiedException();
        }
        String showId = UUID.randomUUID().toString();
        final Show show = new Show(showId, movie, screen, startTime, durationInSeconds);
        this.showStore.put(showId, show);
        return show;
    }

    private List<Show> getShowsForScreen(final Screen screen) {
        final List<Show> response = new ArrayList<>();
        for(Show show : showStore.values()) {
            if ( show.getScreen().equals(screen) ) {
                response.add(show);
            }
        }
        return response;
    }
    private boolean checkIfShowCreationAllowed(final Screen screen, final Date startTime, final Integer durationInSeconds) {
        /* TODO: Implement this. This method will return whether the screen is free at a particular time for
        specific duration. This function will be helpful in checking whether the show can be scheduled in that slot
        or not. */
        return true;
    }
}
