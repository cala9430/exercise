package com.example.trains.excercise.service;

import com.example.trains.excercise.exceptions.StationNotFound;
import com.example.trains.excercise.model.Route;
import com.example.trains.excercise.model.Station;
import com.google.common.graph.AbstractValueGraph;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


@Service
public class ExplorerService {

    private static final Logger log = Logger.getLogger(ExplorerService.class.getName());

    public Route findBestRoute(AbstractValueGraph<Station, Long> graphOfStations, Station from, Station to){

        if(!graphOfStations.nodes().contains(from)){
            throw new StationNotFound(from.getName());
        }

        if(!graphOfStations.nodes().contains(to)){
            throw new StationNotFound(from.getName());
        }

        log.info(String.format("Looking for best route: [%s -> %s]", from.getName(), to.getName()));

        Route route = new Route();
        if(graphOfStations.hasEdgeConnecting(from, to)) {
            route.addStation(from, 0L);
            route.addStation(to, graphOfStations.edgeValue(from, to).get());
        }else{

        }

        return route;
    }

    private List<Route> findBestRoute(AbstractValueGraph<Station, Long> graphOfStations, Station from, Station to, Route actualRoute){
        actualRoute.addStation(from, 0L);
        if(graphOfStations.hasEdgeConnecting(from, to)) {

        }else{
        }

        return null;
    }
}
