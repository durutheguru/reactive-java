package com.julianduru.learning.reactive.combining;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;

/**
 * created by julian on 24/02/2022
 */
public class Zip {

    public static void main(String[] args) {
        var flux = Flux.zip(
            getBody(),
            getEngine(),
            getTires()
        );

        flux.subscribe(Util.subscriber());
    }


    private static Flux<String> getBody() {
        return Flux.range(1, 5)
            .map(i -> "body");
    }


    private static Flux<String> getEngine() {
        return Flux.range(1, 3)
            .map(i -> "engine");
    }


    private static Flux<String> getTires() {
        return Flux.range(1, 6)
            .map(i -> "tires");
    }


}
