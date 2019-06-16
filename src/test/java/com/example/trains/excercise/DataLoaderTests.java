package com.example.trains.excercise;

import com.example.trains.excercise.exceptions.MapFileEmptyException;
import com.example.trains.excercise.exceptions.MapFileNotFoundException;
import com.example.trains.excercise.service.DataLoaderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataLoaderTests {

    private DataLoaderService dataLoaderService = new DataLoaderService();

    @Test
    public void loadMapTest() throws IOException {
        File mapFile = ResourceUtils.getFile("classpath:map1.csv");

        List<String> map = dataLoaderService.loadMapFile(mapFile.getAbsolutePath());

        Assert.assertEquals(9, map.size());
    }

    @Test
    public void loadMultilineMapTest() throws IOException {
        File mapFile = ResourceUtils.getFile("classpath:map2.csv");

        List<String> map = dataLoaderService.loadMapFile(mapFile.getAbsolutePath());

        Assert.assertEquals(9, map.size());
    }

    @Test(expected = MapFileEmptyException.class)
    public void loadEmptyMapTest() throws IOException {
        File mapFile = ResourceUtils.getFile("classpath:map3.csv");

        dataLoaderService.loadMapFile(mapFile.getAbsolutePath());
    }


    @Test(expected = MapFileNotFoundException.class)
    public void loadMapNotFoundTest() throws IOException {
        List<String> map = dataLoaderService.loadMapFile("map9.csv");

        Assert.assertEquals(9, map.size());
    }
}
