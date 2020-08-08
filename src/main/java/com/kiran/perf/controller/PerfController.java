package com.kiran.perf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kiran.perf.services.PathBuilderService;

@RestController
public class PerfController {

    @Autowired
    PathBuilderService pathBuilderService;

    @GetMapping("/trace-path")
    ResponseEntity<String> startTracing(){
    	pathBuilderService.startTracingFiles("/Users/kiran/Downloads/");
    	//System.out.println(" ctrl");
        return new ResponseEntity<String>("Processing Completed", HttpStatus.OK);
    }
}