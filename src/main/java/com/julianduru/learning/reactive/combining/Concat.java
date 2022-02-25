package com.julianduru.learning.reactive.combining;

import com.julianduru.learning.reactive.util.NameGenerator;
import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;

/**
 * created by julian on 24/02/2022
 */
public class Concat {


    public static void main(String[] args) {
        var flux1 = Flux.just("a", "b");
        var flux2 = Flux.error(new RuntimeException("Oops error happened"));
        var flux3 = Flux.just("c", "d", "e");

//        var flux = Flux.concat(flux1, flux2, flux3); // will stop publishing data if
//        error is encountered from one of the fluxes
        var flux = Flux.concatDelayError(flux1, flux2, flux3);

        flux.subscribe(Util.subscriber());
    }

}
