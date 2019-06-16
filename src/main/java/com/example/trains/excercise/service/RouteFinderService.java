package com.example.trains.excercise.service;

import com.example.trains.excercise.model.Route;
import com.example.trains.excercise.model.Station;
import com.google.common.graph.AbstractValueGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteFinderService {

    private final PathFinderService pathFinderService;

    @Autowired
    public RouteFinderService(PathFinderService pathFinderService) {
        this.pathFinderService = pathFinderService;
    }

    public Route findRoute(AbstractValueGraph<Station, Long> graphMap, List<String> stations){
        Route result = new Route();

        return result;
    }
}
