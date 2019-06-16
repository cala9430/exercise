package com.example.trains.excercise;

import com.example.trains.excercise.model.Route;
import com.example.trains.excercise.model.Station;
import com.example.trains.excercise.service.MapLoaderService;
import com.example.trains.excercise.service.RouteFinderService;
import com.google.common.graph.AbstractValueGraph;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

public class RouteFinderTests {

    private RouteFinderService routeFinderService = new RouteFinderService();

    private MapLoaderService mapLoaderService = new MapLoaderService();

    @Test
    public void findSimplePathTest(){
        AbstractValueGraph<Station, Long> graphModel = mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AC9", "BC3"));

        Route route = this.routeFinderService.findRoute(graphModel, Arrays.asList("A", "B"));

        Assert.assertEquals(2, route.getStations().size());

        Assert.assertEquals(5L, route.getDistance().longValue());

    }

    @Test
    public void findTwoStepsPathTest(){
        AbstractValueGraph<Station, Long> graphModel = mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AC9", "BC3"));

        Route route = this.routeFinderService.findRoute(graphModel, Arrays.asList("A", "B", "C"));

        Assert.assertEquals(3, route.getStations().size());

        Assert.assertEquals(8L, route.getDistance().longValue());

    }

    @Test
    public void findExerciseExamplesPathTest(){
        AbstractValueGraph<Station, Long> graphModel =
                mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"));

        Route route = this.routeFinderService.findRoute(graphModel, Arrays.asList("A", "B", "C"));
        Assert.assertEquals(3, route.getStations().size());
        Assert.assertEquals(9L, route.getDistance().longValue());

        route = this.routeFinderService.findRoute(graphModel, Arrays.asList("A", "D"));
        Assert.assertEquals(2, route.getStations().size());
        Assert.assertEquals(5L, route.getDistance().longValue());

        route = this.routeFinderService.findRoute(graphModel, Arrays.asList("A", "D", "C"));
        Assert.assertEquals(3, route.getStations().size());
        Assert.assertEquals(13L, route.getDistance().longValue());

        route = this.routeFinderService.findRoute(graphModel, Arrays.asList("A", "E", "B", "C", "D"));
        Assert.assertEquals(5, route.getStations().size());
        Assert.assertEquals(22L, route.getDistance().longValue());

        route = this.routeFinderService.findRoute(graphModel, Arrays.asList("A", "E", "D"));
        Assert.assertNull(route);

    }

}
