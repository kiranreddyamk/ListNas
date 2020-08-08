package com.kiran.perf.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiran.perf.controller.PathWriterService;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class PathBuilderService {

    @Autowired
    PathWriterService pathWriterService;

    List<String[]> dataLines = new ArrayList<>();
    
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public void startTracingFiles(String path) {
        File currentDir = new File(path); // current directory
        displayDirectoryContents(currentDir);
        pathWriterService.pathWriter(dataLines);
    }

    private void displayDirectoryContents(File dir) {

        try {
            File[] files = dir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return !name.equals(".DS_Store");
                }
            });

            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("directory:" + file.getCanonicalPath());
                    displayDirectoryContents(file);
                } else {
                    Path path = Paths.get(file.getCanonicalPath());
                    long bytes = (Files.size(path)/1024);
                    dataLines.add(new String[]{file.getName(), file.getCanonicalPath(), bytes + "", sdf.format(file.lastModified())});
                    System.out.println("     file:" + file.getCanonicalPath());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

