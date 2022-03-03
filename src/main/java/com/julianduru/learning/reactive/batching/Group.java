package com.julianduru.learning.reactive.batching;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * created by julian on 26/02/2022
 */
public class Group {


    public static void main(String[] args) {

        Flux.range(1, 20)
            .delayElements(Duration.ofSeconds(1))
            .groupBy(i -> i % 2)
            .subscribe(gf -> process(gf, gf.key()));

        Util.sleepSeconds(50);
    }


    private static void process(Flux<Integer> flux, int key) {
        flux.subscribe(i -> System.out.println("Key: " + key + " Item: " + i));
    }


}
