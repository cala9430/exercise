package com.example.trains.excercise.service;

import com.example.trains.excercise.model.Route;
import com.example.trains.excercise.model.Station;
import com.google.common.graph.MutableValueGraph;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExplorerService {

    public List<Route> findBestRoute(MutableValueGraph<Station, Route> graphOfStations, Station from, Station to){
        return new ArrayList<>();
    }
}
