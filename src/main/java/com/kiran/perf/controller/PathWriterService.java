package com.kiran.perf.controller;


import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PathWriterService {

    File csvOutputFile = new File("details.csv");

    public void pathWriter(List<String[]>  dataLines){
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public String convertToCSV(String[] data) {
        return Stream.of(data).collect(Collectors.joining(","));
    }
}
