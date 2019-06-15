package com.example.trains.excercise.service;

import com.example.trains.excercise.model.Route;
import org.springframework.stereotype.Service;

@Service
public class RoutePrinterService {

    public void printRoute(Route route){
        if(route == null){
            System.out.printf("NO SUCH ROUTE%n");
        }else{
            System.out.printf("Distance of best route: %d%n", route.getDistance());
        }
    }
}
