package com.julianduru.learning.reactive.util.flights;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * created by julian on 24/02/2022
 */
public class EmiratesFlights {


    public static Flux<String> getFlights() {
        return Flux.range(1, Util.faker().random().nextInt(1, 10))
            .delayElements(Duration.ofSeconds(1))
            .map(i -> "Emirates " + Util.faker().random().nextInt(100, 999))
            .filter(i -> Util.faker().random().nextBoolean());
    }


}
