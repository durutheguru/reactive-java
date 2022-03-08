package com.julianduru.learning.reactive;

import com.julianduru.learning.reactive.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

import java.time.Duration;

/**
 * created by julian on 07/03/2022
 */
public class StepVerifierVirtualTimeTest {


    @Test
    public void test() {
        StepVerifier.withVirtualTime(this::timeConsumingFlux)
            .thenAwait(Duration.ofSeconds(30))
            .expectNext("1a", "2a", "3a", "4a")
            .verifyComplete();
    }



    @Test
    public void test2() {
        StepVerifier.withVirtualTime(this::timeConsumingFlux)
            .expectSubscription()
            .expectNoEvent(Duration.ofSeconds(4))
            .thenAwait(Duration.ofSeconds(30))
            .expectNext("1a", "2a", "3a", "4a")
            .verifyComplete();
    }



    private Flux<String> timeConsumingFlux() {
        return Flux.range(1, 4)
            .delayElements(Duration.ofSeconds(5))
            .map(i -> i + "a");
    }


}
