package com.julianduru.learning.reactive.rerun;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * created by julian on 26/02/2022
 */
public class RRetry {



    private static AtomicInteger integer = new AtomicInteger(1);


    public static void main(String[] args) {

        // reconnects with publisher n times after the first complete signal
        // if n is omitted, the signal repeats indefinitely
//        getIntegers()
//            .repeat(2)
//            .subscribe(Util.subscriber());

//        retry with RetrySpec
//        getIntegers()
//            .retryWhen(reactor.util.retry.Retry.fixedDelay(3, Duration.ofSeconds(1)))
//                .subscribe(Util.subscriber());

//        Util.sleepSeconds(10);


        getIntegers()
            .retry(2)
            .subscribe(Util.subscriber());

    }


    private static Flux<Integer> getIntegers() {
        return Flux.range(1, 3)
            .doOnSubscribe(s -> System.out.println("Subscribed"))
            .doOnComplete(() -> System.out.println("--Completed"))
            .map(i -> i / (Util.faker().random().nextInt(1, 5) > 3 ? 0: 1))
            .doOnError(e -> System.err.println("Ex: " + e.getMessage()));
    }


}


