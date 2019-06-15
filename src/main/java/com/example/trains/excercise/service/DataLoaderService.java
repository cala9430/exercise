package com.example.trains.excercise.service;

import com.example.trains.excercise.exceptions.MapFileEmptyException;
import com.example.trains.excercise.exceptions.MapFileNotFoundException;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataLoaderService {

    public List<String> loadMapFile(String path) throws IOException {

        FileReader filereader;
        try{
            filereader = new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new MapFileNotFoundException(e.getMessage());
        }

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(true)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(filereader)
                .withSkipLines(0)
                .withCSVParser(parser)
                .build();

        List<String> result = new ArrayList<>();

        List<String[]> lines = csvReader.readAll();

        if(CollectionUtils.isEmpty(lines)){
            throw new MapFileEmptyException(String.format("Malformed map file: %s. Cannot load empty file.", path));
        }


        lines.forEach(l -> {
            for (String s : l) {
                if(StringUtils.isNotEmpty(s)){
                    result.add(s.trim());
                }
            }
        });

        return result;
    }
}
