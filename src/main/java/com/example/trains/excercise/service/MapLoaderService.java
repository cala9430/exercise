package com.example.trains.excercise.service;

import com.example.trains.excercise.model.Route;
import com.example.trains.excercise.model.Station;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapLoaderService {

    public MutableValueGraph<Station, Route> loadMapToGraphModel(List<String> mapOfStations){
        MutableValueGraph<Station, Route> routes = ValueGraphBuilder.directed().build();


        return routes;
    }
}
