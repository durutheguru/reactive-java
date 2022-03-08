package com.julianduru.learning.reactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

/**
 * created by julian on 07/03/2022
 */
public class StepVerifierDemoTest {


    @Test
    public void testExpectNextAndComplete() {

        var options = StepVerifierOptions.create()
            .scenarioName("Scenario Test");

        var flux = Flux.just(1, 2, 4);

        StepVerifier.create(flux, options)
            .expectNext(1)
            .expectNext(2)
            .expectNext(3)
            .expectComplete()
            .verify();
    }


    @Test
    public void testExpectNextShortAndComplete() {
        var flux = Flux.just(1, 2, 3);

        StepVerifier.create(flux)
            .expectNext(1, 2, 3)
            .expectComplete()
            .verify();
    }


    @Test
    public void testRangeComplete() {
        var flux = Flux.range(1, 50);

        StepVerifier.create(flux)
            .expectNextCount(50)
            .expectComplete()
            .verify();
    }


    @Test
    public void testRangeConditionComplete() {
        var flux = Flux.range(1, 55);

        StepVerifier.create(flux)
            .thenConsumeWhile(i -> i < 51)
            .expectComplete()
            .verify();
    }


}
