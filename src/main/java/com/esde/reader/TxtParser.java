package com.esde.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import java.net.URISyntaxException;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TxtParser {
    private static final Logger logger = LogManager.getLogger();

    public static List<String> loadConfig(String file) {
        List<String> parsedData = new ArrayList<>();
        URL filePath = TxtParser.class.getResource(file);
        if (filePath == null){
            throw new RuntimeException("Invalid file path");
        }

        try (Stream<String> lines = Files.lines(Paths.get(filePath.toURI()))) {
            lines.forEach(parsedData::add);
        } catch (IOException | URISyntaxException e) {
            logger.error(e);
            throw new RuntimeException("Error parsing file", e);
        }
        return parsedData;
    }
}

