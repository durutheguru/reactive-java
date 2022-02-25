package com.julianduru.learning.reactive.batching;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * created by julian on 25/02/2022
 */
public class Buffer {


    public static void main(String[] args) {
        eventStream()
            .buffer(5)
            .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }


    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(300))
            .map(i -> "event " + i);
    }


}
