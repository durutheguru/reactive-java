package com.julianduru.learning.reactive;

import com.github.javafaker.Book;
import com.julianduru.learning.reactive.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

/**
 * created by julian on 07/03/2022
 */
public class StepVerifierDelayTest {


    @Test
    public void testDelay() {
        var mono = Mono
            .fromSupplier(() -> Util.faker().book())
            .delayElement(Duration.ofSeconds(3));

        StepVerifier.create(mono)
            .assertNext(b -> Assertions.assertNotNull(b.title()))
            .expectComplete()
            .verify(Duration.ofSeconds(4));
    }


}
