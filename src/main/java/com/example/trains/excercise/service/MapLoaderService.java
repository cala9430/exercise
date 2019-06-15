package com.example.trains.excercise.service;

import com.example.trains.excercise.exceptions.MalformedMapException;
import com.example.trains.excercise.model.Route;
import com.example.trains.excercise.model.Station;
import com.google.common.graph.AbstractValueGraph;
import com.google.common.graph.ImmutableValueGraph;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapLoaderService {

    public AbstractValueGraph<Station, Long> loadMapToGraphModel(List<String> mapOfStations){
        MutableValueGraph<Station, Long> valueGraph =  ValueGraphBuilder.directed().build();

        mapOfStations.forEach(route -> {
            addRouteToGraph(valueGraph, route);
        });

        return ImmutableValueGraph.copyOf(valueGraph);
    }

    private void addRouteToGraph(MutableValueGraph<Station, Long> valueGraph, String route) {
        if(StringUtils.length(route) != 3){
            throw new MalformedMapException(String.format("Malformed map of cities. Cannot parse %s",route));
        }
        Station stationFrom = new Station(route.substring(0,1));
        Station stationTo = new Station(route.substring(1,2));

        if(valueGraph.hasEdgeConnecting(stationFrom, stationTo)){
            throw new MalformedMapException(String.format("Malformed map of cities. Multiples distances for stations=[%s - %s]",stationFrom.getName(), stationTo.getName()));
        }

        Long distance = Long.parseLong(route.substring(2,3));
        valueGraph.addNode(stationFrom);
        valueGraph.addNode(stationTo);
        valueGraph.putEdgeValue(stationFrom, stationTo, distance);
    }
}
