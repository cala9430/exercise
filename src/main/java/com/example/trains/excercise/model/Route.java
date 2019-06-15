package com.example.trains.excercise.model;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private List<Station> stations = new ArrayList<>();

    private Long distance = 0L;

    public List<Station> getStations() {
        return stations;
    }

    public Long getDistance() {
        return distance;
    }

    public void addStation(Station s, Long distance) {
        this.stations.add(s);
        this.distance += distance;

    }
}
