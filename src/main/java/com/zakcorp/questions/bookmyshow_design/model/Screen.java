package com.zakcorp.questions.bookmyshow_design.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.*;

@Getter
public class Screen {
    private final String id;
    private final String name;
    private final Theatre theatre;
    private final List<Seat> seatList;

    public Screen(@NonNull final String id, @NonNull final String name, @NonNull final Theatre theatre) {
        this.id = id;
        this.name = name;
        this.theatre = theatre;
        this.seatList = new ArrayList<>();
    }

    public void addSeat(@NonNull final Seat seat) {
        this.seatList.add(seat);
    }
}
