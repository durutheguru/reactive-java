package com.julianduru.learning.reactive.threading;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * created by julian on 22/02/2022
 */
public class RThreading {


    public static void main(String[] args) {
        var flux = Flux.create(sink -> {
            printThreadName("create");
            sink.next(1);
        })
            .subscribeOn(Schedulers.newParallel("reactives"))
            .doOnNext(i -> printThreadName("next " + i));

        var scheduler = Schedulers.boundedElastic();

        Runnable runnable = () -> flux
            .doFirst(() -> printThreadName("first2"))
            .subscribeOn(scheduler)
            .doFirst(() -> printThreadName("first1"))
            .subscribe(v -> printThreadName("sub " + v));

        for (int i = 0; i < 2; i++) {
            new Thread(runnable).start();
        }

        Util.sleepSeconds(5);
    }


    private static void printThreadName(String message) {
        System.out.println(
            message + "\t\t" + " Thread: " + Thread.currentThread().getName()
        );
    }


}
