package com.julianduru.learning.reactive.flux;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * created by julian on 23/02/2022
 */
public class RFluxInterval {


    public static void main(String[] args) {
        Flux.interval(Duration.ofSeconds(1))
            .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }


}

