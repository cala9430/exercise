package com.example.trains.excercise.service;

import com.example.trains.excercise.model.Route;
import com.example.trains.excercise.model.Station;
import com.google.common.graph.AbstractValueGraph;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouteFinderService {

    /**
     * Finds if there is a route to visit all and only the stations in stations list. If additional stops are possible,
     * this can be used with PathFinderService to use them.
     * @param graphMap      Graph representation of stations map
     * @param stationNames  List of stations to be visited
     * @return Route with a set of stations and the distance of the complete route
     */
    public Route findRoute(AbstractValueGraph<Station, Long> graphMap, List<String> stationNames){
        Route result = new Route();

        List<Station> stations = stationNames.stream().map(Station::new).collect(Collectors.toList());

        result.addStation(stations.get(0), 0L);
        for (int i = 0; i < stations.size() - 1; i++) {

            Optional<Long> optionalDistance = graphMap.edgeValue(stations.get(i), stations.get(i+1));

            if(optionalDistance.isPresent()){
                result.addStation(stations.get(i+1), optionalDistance.get());
            } else {
                return null;
            }
        }

        return result;
    }
}
