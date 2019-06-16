package com.example.trains.excercise.model;

import java.util.HashSet;
import java.util.Set;

public class Route {

    private Set<Station> stations = new HashSet<>();

    private Long distance = 0L;

    public Set<Station> getStations() {
        return stations;
    }

    public Long getDistance() {
        return distance;
    }

    public Route() {
    }

    public Route(Route other) {
        this.stations = new HashSet<>(other.getStations());
        this.distance = other.distance;
    }

    public void addStation(Station s, Long distance) {
        this.stations.add(s);
        this.distance += distance;

    }

    public boolean contains(Station station) {
        return this.stations.contains(station);
    }

    @Override
    public String toString() {
        return "Route{" +
                "stations=" + stations +
                ", distance=" + distance +
                '}';
    }
}
