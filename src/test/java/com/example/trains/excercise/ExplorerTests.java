package com.example.trains.excercise;

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
    public void testSimpleGraph(){
        AbstractValueGraph<Station, Long> graphModel = mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AC9", "BC3"));

        Route bestRoute = explorerService.findBestRoute(graphModel, new Station("A"), new Station("C"));

        Assert.assertEquals(1, bestRoute.getStations().size());


    }
}
