package com.example.trains.excercise.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DataLoaderService {

    public List<String> loadMapFile(String path) throws IOException {

        FileReader filereader = new FileReader(path);

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(true)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(filereader)
                .withSkipLines(0)
                .withCSVParser(parser)
                .build();

        List<String> result = new ArrayList<>();

        csvReader.readAll().forEach(l -> result.addAll(Arrays.asList(l)));

        return result;
    }
}
