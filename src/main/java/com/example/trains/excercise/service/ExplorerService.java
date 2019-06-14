package com.example.trains.excercise.service;

import com.example.trains.excercise.model.Route;
import com.example.trains.excercise.model.Station;
import com.google.common.graph.AbstractValueGraph;
import org.springframework.stereotype.Service;


@Service
public class ExplorerService {

    public Route findBestRoute(AbstractValueGraph<Station, Long> graphOfStations, Station from, Station to){
        return null;
    }
}
