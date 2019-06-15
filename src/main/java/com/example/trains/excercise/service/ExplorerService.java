package com.example.trains.excercise.service;

import com.example.trains.excercise.model.Route;
import com.example.trains.excercise.model.Station;
import com.google.common.graph.AbstractValueGraph;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExplorerService {

    public Route findBestRoute(AbstractValueGraph<Station, Long> graphOfStations, Station from, Station to){

        Route route = new Route();
        if(graphOfStations.hasEdgeConnecting(from, to)) {
            route.addStation(from, 0L);
            route.addStation(to, graphOfStations.edgeValue(from, to).get());
        }else{

        }

        return route;
    }

    private List<Route> findBestRoute(AbstractValueGraph<Station, Long> graphOfStations, Station from, Station to, Route actualRoute){
        return null;
    }
}
