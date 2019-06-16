package com.example.trains.excercise;

import com.example.trains.excercise.model.Route;
import com.example.trains.excercise.model.Station;
import com.example.trains.excercise.service.MapLoaderService;
import com.example.trains.excercise.service.RouteFinderService;
import com.google.common.graph.AbstractValueGraph;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteFinderTests {

    @Autowired
    private RouteFinderService routeFinderService;

    @Autowired
    private MapLoaderService mapLoaderService;

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

        Assert.assertEquals(17L, route.getDistance().longValue());

    }

}
