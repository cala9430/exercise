package com.example.trains.excercise;

import com.example.trains.excercise.exceptions.StationNotFound;
import com.example.trains.excercise.model.Route;
import com.example.trains.excercise.model.Station;
import com.example.trains.excercise.service.ExplorerService;
import com.example.trains.excercise.service.MapLoaderService;
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
public class ExplorerTests {

    @Autowired
    private ExplorerService explorerService;

    @Autowired
    private MapLoaderService mapLoaderService;

    @Test
    public void testSimpleStepGraph(){
        AbstractValueGraph<Station, Long> graphModel = mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AC9", "BC3"));

        Route bestRoute = explorerService.findBestRoute(graphModel, new Station("A"), new Station("C"));

        Assert.assertEquals(1, bestRoute.getStations().size());

        long distance = bestRoute.getDistance();
        Assert.assertEquals(9L, distance);
    }

    @Test
    public void testTwoStepsGraph(){
        AbstractValueGraph<Station, Long> graphModel = mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AC9", "BC3", "CD1"));

        Route bestRoute = explorerService.findBestRoute(graphModel, new Station("A"), new Station("D"));

        Assert.assertEquals(2, bestRoute.getStations().size());

        long distance = bestRoute.getDistance();
        Assert.assertEquals(10L, distance);
    }

    @Test(expected = Exception.class)
    public void testNoRouteGraph(){
        AbstractValueGraph<Station, Long> graphModel = mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AC9", "BC3","CD1"));

        explorerService.findBestRoute(graphModel, new Station("B"), new Station("D"));
    }

    @Test(expected = StationNotFound.class)
    public void testStationFromNotFound(){
        AbstractValueGraph<Station, Long> graphModel = mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AC9", "BC3","CD1"));

        explorerService.findBestRoute(graphModel, new Station("F"), new Station("A"));
    }

    @Test(expected = StationNotFound.class)
    public void testStationToNotFound(){
        AbstractValueGraph<Station, Long> graphModel = mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AC9", "BC3","CD1"));

        explorerService.findBestRoute(graphModel, new Station("A"), new Station("F"));
    }
}
