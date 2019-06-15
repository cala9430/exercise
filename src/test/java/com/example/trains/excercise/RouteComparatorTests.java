package com.example.trains.excercise;

import com.example.trains.excercise.model.Route;
import com.example.trains.excercise.model.Station;
import com.example.trains.excercise.service.RouteComparator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteComparatorTests {

    private Comparator<Route> routeComparator = new RouteComparator();

    @Test
    public void testFirstBestByDistance(){
        Route r1  = new Route();
        r1.addStation(new Station("A"), 0L);
        r1.addStation(new Station("B"), 5L);
        r1.addStation(new Station("C"), 6L);

        Route r2  = new Route();
        r2.addStation(new Station("A"), 0L);
        r2.addStation(new Station("D"), 7L);
        r2.addStation(new Station("C"), 6L);

        Assert.assertEquals(-1, routeComparator.compare(r1,r2));
    }

    @Test
    public void testSecondBestByDistance(){
        Route r1  = new Route();
        r1.addStation(new Station("A"), 0L);
        r1.addStation(new Station("B"), 5L);
        r1.addStation(new Station("C"), 6L);

        Route r2  = new Route();
        r2.addStation(new Station("A"), 0L);
        r2.addStation(new Station("D"), 7L);
        r2.addStation(new Station("C"), 6L);

        Assert.assertEquals(1, routeComparator.compare(r2,r1));
    }

    @Test
    public void testAreEqual(){
        Route r1  = new Route();
        r1.addStation(new Station("A"), 0L);
        r1.addStation(new Station("B"), 5L);
        r1.addStation(new Station("C"), 6L);

        Route r2  = new Route();
        r2.addStation(new Station("A"), 0L);
        r2.addStation(new Station("D"), 5L);
        r2.addStation(new Station("C"), 6L);

        Assert.assertEquals(0, routeComparator.compare(r2,r1));
    }

    @Test
    public void testFirstBestByStations(){
        Route r1  = new Route();
        r1.addStation(new Station("A"), 0L);
        r1.addStation(new Station("C"), 6L);

        Route r2  = new Route();
        r2.addStation(new Station("A"), 0L);
        r2.addStation(new Station("D"), 7L);
        r2.addStation(new Station("C"), 6L);

        Assert.assertEquals(-1, routeComparator.compare(r1,r2));
    }

    @Test
    public void testSecondBestByStations(){
        Route r1  = new Route();
        r1.addStation(new Station("A"), 0L);
        r1.addStation(new Station("B"), 5L);
        r1.addStation(new Station("C"), 6L);

        Route r2  = new Route();
        r2.addStation(new Station("A"), 0L);
        r2.addStation(new Station("C"), 6L);

        Assert.assertEquals(1, routeComparator.compare(r1,r2));
    }
}
