package com.example.trains.excercise.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ArgsValidationService {

    private static final Logger log = Logger.getLogger(ArgsValidationService.class.getName());

    /**
     * Validates program arguments as: pathToMap desiredRoute
     * @param args  Program arguments
     * @return      List of stations names in desired route
     */
    public List<String> validateAndParseArgs(String... args){
        if(args.length != 2){
            throw new IllegalArgumentException("Usage: <pathToMap> <desiredRoute>");
        }

        log.info(String.format("Path: %s", args[0]));
        log.info(String.format("Route: %s", args[1]));

        String[] stations = args[1].split("-");

        if(stations.length < 2){
            throw new IllegalArgumentException("Route must be more than one stations separated by '-'");
        }

        for (int i = 0; i < stations.length - 1; i++) {
            if(stations[i].equals(stations[i+1])){
                throw new IllegalArgumentException(String.format("Cannot travel from station %s to same station", stations[i]));
            }
        }


        return Arrays.asList(stations);
    }

}
