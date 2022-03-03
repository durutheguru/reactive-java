package com.julianduru.learning.reactive.batching;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * created by julian on 25/02/2022
 */
public class Window {


    private static AtomicInteger integer = new AtomicInteger(1);

    public static void main(String[] args) {

        eventStream()
            .window(5)
            .flatMap(Window::saveEvents)
            .subscribe(Util.subscriber());


        Util.sleepSeconds(60);
    }


    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
            .map(i -> "event " + i);
    }


    private static Mono<Integer> saveEvents(Flux<String> flux) {
        return flux.doOnNext(i -> System.out.println("Saving. " + i))
            .doOnComplete(() -> {
                System.out.println("Saved this batch..");
                System.out.println("...................");
            })
            .then(Mono.just(integer.getAndIncrement()));
    }


}
