package com.example.trains.excercise;

import com.example.trains.excercise.model.Route;
import com.example.trains.excercise.model.Station;
import com.example.trains.excercise.service.RoutePrinterService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoutePrinterTests {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Autowired
    private RoutePrinterService routePrinterService;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void printBestRoute() {
        Route route = new Route();
        route.addStation(new Station("A"), 0L);
        route.addStation(new Station("B"), 9L);
        routePrinterService.printRoute(route);

        Assert.assertEquals(String.format("Distance of best route: 9%n"), outContent.toString());
    }

    @Test
    public void printNoRoute() {
        routePrinterService.printRoute(null);
        Assert.assertEquals(String.format("NO SUCH ROUTE%n"), outContent.toString());
    }
}
