package com.julianduru.learning.reactive.rerun;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

/**
 * created by julian on 26/02/2022
 */
public class RetryWhenAdvanced {


    public static void main(String[] args) {
        orderService(Util.faker().business().creditCardNumber())
            .doOnError(err -> System.err.println(err.getMessage()))
            .retry(5)
            .subscribe(Util.subscriber());

        orderService(Util.faker().business().creditCardNumber())
            .retryWhen(Retry.from(
                flux -> flux.doOnNext(rs -> {
                    System.out.println("retry signal: " + rs.totalRetries());
                    System.out.println(rs.failure());
                })
                    .handle((retrySignal, synchronousSink) -> {
                        if (retrySignal.failure().getMessage().equalsIgnoreCase("500")) {
                            synchronousSink.next(1);
                        }
                        else {
                            synchronousSink.error(retrySignal.failure());
                        }
                    })
                    .delayElements(Duration.ofSeconds(1))
            ))
            .subscribe(Util.subscriber());


        Util.sleepSeconds(60);
    }


    private static Mono<String> orderService(String ccNumber) {
        return Mono.fromSupplier(() -> {
            processPayment(ccNumber);
            return Util.faker().idNumber().valid();
        });
    }


    private static void processPayment(String ccNumber) {
        int random = Util.faker().random().nextInt(1, 10);
        System.out.println("Generated Random: " + random);
        if (random < 8) {
            throw new RuntimeException("500");
        }
        else if (random < 10) {
            throw new RuntimeException("404");
        }
    }


}

