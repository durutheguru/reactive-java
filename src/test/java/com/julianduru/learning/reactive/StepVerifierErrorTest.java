package com.julianduru.learning.reactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;

/**
 * created by julian on 07/03/2022
 */
public class StepVerifierErrorTest {


    @Test
    public void testError() {
        var flux = Flux.just(1, 2, 3);
        var error = Flux.error(new RuntimeException("Oops!!"))
            .cast(Integer.class);

        var concat = Flux.concat(flux, error);

        StepVerifier.create(concat)
            .expectNext(1, 2, 3)
            .verifyErrorMatches(
                t -> t instanceof RuntimeException && t.getMessage().equalsIgnoreCase("oops!!")
            );
    }


}
