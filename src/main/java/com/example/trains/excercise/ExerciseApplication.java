package com.example.trains.excercise;

import com.example.trains.excercise.model.Route;
import com.example.trains.excercise.model.Station;
import com.example.trains.excercise.service.*;
import com.google.common.graph.AbstractValueGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ExerciseApplication implements CommandLineRunner {

	private final DataLoaderService dataLoaderService;

	private final MapLoaderService mapLoaderService;

	private final RoutePrinterService routePrinterService;

	private final RouteFinderService routeFinderService;

	private final ArgsValidationService argsValidationService;

	@Autowired
	public ExerciseApplication(DataLoaderService dataLoaderService, MapLoaderService mapLoaderService,
							   RoutePrinterService routePrinterService, RouteFinderService routeFinderService,
							   ArgsValidationService argsValidationService) {
		this.dataLoaderService = dataLoaderService;
		this.mapLoaderService = mapLoaderService;
		this.routePrinterService = routePrinterService;
		this.routeFinderService = routeFinderService;
		this.argsValidationService = argsValidationService;
	}

	@Override
	public void run(String... args) throws Exception {
		List<String> desiredRoute = this.argsValidationService.validateAndParseArgs(args);

		List<String> mapFile = this.dataLoaderService.loadMapFile(args[0]);

		AbstractValueGraph<Station, Long> graphMap = this.mapLoaderService.loadMapToGraphModel(mapFile);

		Route route = this.routeFinderService.findRoute(graphMap, desiredRoute);

		this.routePrinterService.printRoute(route);
	}


	public static void main(String[] args) {
		SpringApplication.run(ExerciseApplication.class, args);
	}
}
