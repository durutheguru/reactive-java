package com.julianduru.learning.reactive.combining;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * created by julian on 24/02/2022
 */
public class CombineLatest {


    public static void main(String[] args) {
        Flux
            .combineLatest(
                getString(),
                getNumber(),
                (s, n) -> s + n
            )
            .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }


    private static Flux<String> getString() {
        return Flux.just("A", "B", "C", "D")
            .delayElements(Duration.ofSeconds(1));
    }


    private static Flux<Integer> getNumber() {
        return Flux.just(1, 2, 3)
            .delayElements(Duration.ofSeconds(3));
    }


}
