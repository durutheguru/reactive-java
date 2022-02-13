package com.julianduru.learning.reactive.stream;

import lombok.extern.slf4j.Slf4j;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * created by julian on 12/02/2022
 */
public class RStream {

    private static Logger log = Logger.getLogger(RStream.class.getName());

    public static void main(String[] args) {
        var stream = Stream.of(1, 2, 3)
                .map(i -> {
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                        log.log(Level.SEVERE, e.getMessage(), e);
                    }

                    return i * 2;
                });

        stream.forEach(System.out::println);
    }

}
