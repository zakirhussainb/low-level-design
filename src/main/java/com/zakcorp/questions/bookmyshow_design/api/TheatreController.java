package com.zakcorp.questions.bookmyshow_design.api;

import com.zakcorp.questions.bookmyshow_design.model.Screen;
import com.zakcorp.questions.bookmyshow_design.model.Theatre;
import com.zakcorp.questions.bookmyshow_design.service.TheatreService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class TheatreController {
    final private TheatreService theatreService;

    public String createTheatre(@NonNull final String theatreName) {
        return theatreService.createTheatre(theatreName).getId();
    }

    public String createScreenInTheatre(@NonNull final String screenName, @NonNull final String theatreId) {
        final Theatre theatre = theatreService.getTheatre(theatreId);
        return theatreService.createScreenInTheatre(screenName, theatre).getId();
    }

    public String createSeatInScreen(@NonNull final Integer rowNo, @NonNull final Integer seatNo, @NonNull final String screenId) {
        final Screen screen = theatreService.getScreen(screenId);
        return theatreService.createSeatInScreen(rowNo, seatNo, screen).getId();
    }
}
