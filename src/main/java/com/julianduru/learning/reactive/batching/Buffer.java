package com.julianduru.learning.reactive.batching;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * created by julian on 25/02/2022
 */
public class Buffer {


    public static void main(String[] args) {

        // Buffer by size
//        eventStream()
//            .buffer(5)
//            .subscribe(Util.subscriber());

        // Buffer by duration
//        eventStream()
//            .buffer(Duration.ofSeconds(2))
//            .subscribe(Util.subscriber());

        // Buffer timeout with size limit
        eventStream()
            .bufferTimeout(5, Duration.ofSeconds(2))
            .subscribe(Util.subscriber());

        // Buffer will keep last three items. new items will remove the oldest (skipped) item
        // if skipped items exceeds maxSize, we have a dropping buffer else, we have an overflowing
        // buffer
        eventStream()
            .buffer(3, 1)
            .subscribe(Util.subscriber());


        Util.sleepSeconds(60);
    }


    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(10))
            .map(i -> "event " + i);
    }


}
