package com.julianduru.learning.reactive.threading;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * created by julian on 22/02/2022
 */
public class RThreading2 {


    public static void main(String[] args) {
        var flux = Flux.create(sink -> {
            printThreadName("create");
            for (int i = 0; i < 20; i++) {
                sink.next(i);
                Util.sleepSeconds(1);
            }

            sink.complete();
        }).doOnNext(i -> printThreadName("next " + i));

        flux
            .subscribeOn(Schedulers.boundedElastic())
            .subscribe(v -> printThreadName("sub " + v));

        flux
            .subscribeOn(Schedulers.parallel())
            .subscribe(v -> printThreadName("sub " + v));


        Util.sleepSeconds(5);
    }


    private static void printThreadName(String message) {
        System.out.println(
            message + "\t\t" + " Thread: " + Thread.currentThread().getName()
        );
    }


}


