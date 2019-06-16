package com.example.trains.excercise;

import com.example.trains.excercise.exceptions.MalformedMapException;
import com.example.trains.excercise.model.Station;
import com.example.trains.excercise.service.MapLoaderService;
import com.google.common.graph.AbstractValueGraph;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

public class MapLoaderTests {

    private MapLoaderService mapLoaderService = new MapLoaderService();

    @Test
    public void loadValidMap(){
        AbstractValueGraph<Station, Long> graphModel = mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AC9"));

        Optional<Long> optionalRoute = graphModel.edgeValue(new Station("A"), new Station("B"));
        Assert.assertTrue(optionalRoute.isPresent());

        long distance = optionalRoute.get();
        Assert.assertEquals(5, distance);

        Set<Station> aAdjacents = graphModel.adjacentNodes(new Station("A"));
        Assert.assertEquals(2, aAdjacents.size());
    }

    @Test(expected = MalformedMapException.class)
    public void loadInvalidMap(){
        mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AC5", "9"));
    }

    @Test(expected = MalformedMapException.class)
    public void loadInvalidDistanceMap(){
        mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AC5", "ABC"));
    }

    @Test(expected = RuntimeException.class)
    public void loadDuplicatedDistanceMap(){
        mapLoaderService.loadMapToGraphModel(Arrays.asList("AB5", "AB9"));
    }
}
