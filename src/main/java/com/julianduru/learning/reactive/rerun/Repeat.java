package com.julianduru.learning.reactive.rerun;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * created by julian on 26/02/2022
 */
public class Repeat {


    private static AtomicInteger integer = new AtomicInteger(1);


    public static void main(String[] args) {

        // reconnects with publisher n times after the first complete signal
        // if n is omitted, the signal repeats indefinitely
//        getIntegers()
//            .repeat(2)
//            .subscribe(Util.subscriber());


        getIntegers()
            .repeat(() -> integer.get() < 13)
            .subscribe(Util.subscriber());

    }


    private static Flux<Integer> getIntegers() {
        return Flux.range(1, 3)
            .doOnSubscribe(s -> System.out.println("Subscribed"))
            .doOnComplete(() -> System.out.println("--Completed"))
            .map(i -> integer.getAndIncrement());
    }


}


