package com.esde.utill;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import java.net.URISyntaxException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TxtParser {
    private static final Logger logger = LogManager.getLogger();

    public static List<String> loadConfig(String file) {
        List<String> parsedData = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(TxtParser.class.getResource(file).toURI()))) {
            lines.forEach(parsedData::add);
        } catch (IOException | URISyntaxException | NullPointerException e) {
            logger.error(e);
        }
        return parsedData;
    }
}

