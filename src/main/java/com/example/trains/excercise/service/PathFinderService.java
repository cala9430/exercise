package com.example.trains.excercise.service;

import com.example.trains.excercise.exceptions.StationNotFound;
import com.example.trains.excercise.model.Route;
import com.example.trains.excercise.model.Station;
import com.google.common.graph.AbstractValueGraph;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

/**
 * This service can be used to find best routes including additional stops.
 */
@Service
public class PathFinderService {

    private static final Logger log = Logger.getLogger(PathFinderService.class.getName());

    private Comparator<Route> routeComparator;

    public PathFinderService() {
        this.routeComparator = new RouteComparator();
    }

    /**
     * Finds all routes from - to and returns the best based on RouteComparator logic
     * @param graphOfStations   Graph representation of stations map
     * @param from              Departure station
     * @param to                Arriving station
     * @return                  Route containing a set of Stations and the total distance
     */
    public Route findBestRoute(AbstractValueGraph<Station, Long> graphOfStations, Station from, Station to, Route currentRoute){

        validateInput(graphOfStations, from, to);
        log.info(String.format("Looking for best route: [%s -> %s]", from.getName(), to.getName()));

        List<Route> possibleRoutes = this.findAllRoutes(graphOfStations, from, to, currentRoute);

        log.info(String.format("Found %d routes from %s to %s", possibleRoutes.size(), from.getName(), to.getName()));

        possibleRoutes.sort(this.routeComparator);

        Route bestRoute = possibleRoutes.get(0);

        log.info(String.format("Found best route %s", bestRoute));
        return bestRoute;
    }

    private List<Route> findAllRoutes(AbstractValueGraph<Station, Long> graphOfStations, Station from, Station to, Route currentRoute){
        List<Route> possibleRoutes = new ArrayList<>();

        if(currentRoute.getStations().isEmpty()){
            currentRoute.addStation(from, 0L);
        }

        this.recursive(graphOfStations, from, to, possibleRoutes, currentRoute);

        return possibleRoutes;

    }

    /**
     * Recursive function to move through the map of stations
     * @param graphOfStations   Graph representation of stations map
     * @param current           Current station
     * @param to                Arriving station
     * @param possibleRoutes    List of complete routes
     * @param route             Already visited stations
     */
    private void recursive(AbstractValueGraph<Station, Long> graphOfStations, Station current, Station to,
                           List<Route> possibleRoutes, Route route) {

        // Arrived to target Station. Add this as possible route
        if(current.equals(to)){
            possibleRoutes.add(route);
            return;
        }

        // Find next stations available
        final Set<Station> successors  = graphOfStations.successors(current);

        for (Station station : successors) {

            // Avoid cycles
            if (!route.contains(station)) {
                Route newPossibleRoute = new Route(route);
                newPossibleRoute.addStation(station, graphOfStations.edgeValue(current, station).get());
                recursive (graphOfStations, station, to, possibleRoutes, newPossibleRoute);
            }
        }
    }


    /**
     * Validate input params:
     * Graph, Station form and Station to cannot be null.
     * Both stations must be in the Graph
     * From Station cannot be Equals to To Station
     * @param graphOfStations   Graph representation of stations map
     * @param from              Departure station
     * @param to                Arriving station
     */
    private void validateInput(AbstractValueGraph<Station, Long> graphOfStations, Station from, Station to) {
        if(graphOfStations == null || from == null || to == null){
            throw new IllegalArgumentException("Graph and Stations cannot be null");
        }

        if(from.equals(to)){
            throw new IllegalArgumentException("Stations [From] and [To] can not be the same");
        }

        if(!graphOfStations.nodes().contains(from)){
            throw new StationNotFound(from.getName());
        }

        if(!graphOfStations.nodes().contains(to)){
            throw new StationNotFound(from.getName());
        }
    }
}
