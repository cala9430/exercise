package com.example.trains.excercise;

import com.example.trains.excercise.model.Route;
import com.example.trains.excercise.model.Station;
import com.example.trains.excercise.service.DataLoaderService;
import com.example.trains.excercise.service.ExplorerService;
import com.example.trains.excercise.service.MapLoaderService;
import com.example.trains.excercise.service.RoutePrinterService;
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

	private final ExplorerService explorerService;

	private final RoutePrinterService routePrinterService;

	@Autowired
	public ExerciseApplication(DataLoaderService dataLoaderService, MapLoaderService mapLoaderService,
							   ExplorerService explorerService, RoutePrinterService routePrinterService) {
		this.dataLoaderService = dataLoaderService;
		this.mapLoaderService = mapLoaderService;
		this.explorerService = explorerService;
		this.routePrinterService = routePrinterService;
	}

	@Override
	public void run(String... args) throws Exception {
		List<String> mapFile = this.dataLoaderService.loadMapFile(args[0]);
		AbstractValueGraph<Station, Long> graphMap = this.mapLoaderService.loadMapToGraphModel(mapFile);
		Route bestRoute = this.explorerService.findBestRoute(graphMap, new Station(args[1]), new Station(args[2]));
		this.routePrinterService.printRoute(bestRoute);
	}

	public static void main(String[] args) {
		SpringApplication.run(ExerciseApplication.class, args);
	}
}
