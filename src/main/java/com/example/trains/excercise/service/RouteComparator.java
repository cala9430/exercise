package com.example.trains.excercise.service;

import com.example.trains.excercise.model.Route;

import java.util.Comparator;

public class RouteComparator implements Comparator<Route> {

    @Override
    public int compare(Route route1, Route route2) {

        int stations1 = route1.getStations().size();
        int stations2 = route2.getStations().size();
        if(stations1 < stations2){
            return -1;
        }else if(stations1 > stations2){
            return 1;
        }else{
            return route1.getDistance().compareTo(route2.getDistance());
        }
    }
}
