package com.example.trains.excercise;

import com.example.trains.excercise.exceptions.StationNotFound;
import com.example.trains.excercise.model.Route;
import com.example.trains.excercise.model.Station;
import com.example.trains.excercise.service.PathFinderService;
import com.example.trains.excercise.service.MapLoaderService;
import com.google.common.graph.AbstractValueGraph;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class PathFinderTests {

    private PathFinderService pathFinderService = new PathFinderService();

    private MapLoaderService mapLoaderService = new MapLoaderService();

    @Test
    public void testSimpleStepGraph(){
        AbstractValueGraph<Station, Long> graphModel = mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AC9", "BC3"));

        Route bestRoute = pathFinderService.findBestRoute(graphModel, new Station("A"), new Station("C"), new Route());

        Assert.assertEquals(2, bestRoute.getStations().size());

        long distance = bestRoute.getDistance();
        Assert.assertEquals(9L, distance);
    }

    @Test
    public void testTwoStepsGraph(){
        AbstractValueGraph<Station, Long> graphModel = mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AC9", "BC3", "CD1"));


        Route bestRoute = pathFinderService.findBestRoute(graphModel, new Station("A"), new Station("D"), new Route());

        Assert.assertEquals(3, bestRoute.getStations().size());

        long distance = bestRoute.getDistance();
        Assert.assertEquals(10L, distance);
    }

    @Test
    public void testCycledGraph(){
        AbstractValueGraph<Station, Long> graphModel = mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "BC9", "CA3", "CD1"));

        Route bestRoute = pathFinderService.findBestRoute(graphModel, new Station("A"), new Station("D"), new Route());

        Assert.assertEquals(4, bestRoute.getStations().size());

        long distance = bestRoute.getDistance();
        Assert.assertEquals(15L, distance);
    }

    @Test(expected = Exception.class)
    public void testNoRouteGraph(){
        AbstractValueGraph<Station, Long> graphModel = mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AC9", "BC3","AD1"));

        pathFinderService.findBestRoute(graphModel, new Station("B"), new Station("D"), new Route());
    }

    @Test(expected = StationNotFound.class)
    public void testStationFromNotFound(){
        AbstractValueGraph<Station, Long> graphModel = mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AC9", "BC3","CD1"));

        pathFinderService.findBestRoute(graphModel, new Station("F"), new Station("A"), new Route());
    }

    @Test(expected = StationNotFound.class)
    public void testStationToNotFound(){
        AbstractValueGraph<Station, Long> graphModel = mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AC9", "BC3","CD1"));

        pathFinderService.findBestRoute(graphModel, new Station("A"), new Station("F"), new Route());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSameFromTo(){
        AbstractValueGraph<Station, Long> graphModel = mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AC9", "BC3","CD1"));

        pathFinderService.findBestRoute(graphModel, new Station("A"), new Station("A"), new Route());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullFrom(){
        AbstractValueGraph<Station, Long> graphModel = mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AC9", "BC3","CD1"));

        pathFinderService.findBestRoute(graphModel, null, new Station("A"), new Route());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullTo(){
        AbstractValueGraph<Station, Long> graphModel = mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AC9", "BC3","CD1"));

        pathFinderService.findBestRoute(graphModel, new Station("A"), null , new Route());
    }
}
