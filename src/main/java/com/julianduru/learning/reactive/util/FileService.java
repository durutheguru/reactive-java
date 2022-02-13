package com.julianduru.learning.reactive.util;

import com.julianduru.learning.reactive.data.FileLine;
import lombok.extern.java.Log;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;

/**
 * created by julian on 12/02/2022
 */
@Log
public class FileService {


    public static void main(String[] args) {
        var mono = readFileContents(
            "file:///Users/dduru/IdeaProjects/Private/Learning/reactive-java/src/main/resources/sample.txt"
        );

        mono.subscribe(
            s -> log.info("FileData: " + s.toString()),
            e -> log.log(Level.SEVERE, e.getMessage(), e),
            () -> log.info("Completed Processing...")
        );
    }


    private static Mono<FileLine> readFileContents(String filePath) {
        return Mono
            .fromSupplier(() -> FileService.readFileData(filePath))
            .map(line -> {
                var tokens = line.split("\\s+");
                return new FileLine(
                    tokens[0],
                    tokens[1],
                    tokens[2].charAt(0),
                    Integer.parseInt(tokens[3]),
                    BigDecimal.valueOf(Double.parseDouble(tokens[4]))
                );
            });
    }


    private static String readFileData(String filePath) {
        try {
            var path = Path.of(URI.create(filePath));
            return Files.readString(path);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}


