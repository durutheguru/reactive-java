package com.julianduru.learning.reactive.threading;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * created by julian on 22/02/2022
 */
public class RPublishOn {


    public static void main(String[] args) {
        var flux = Flux.create(sink -> {
            printThreadName("create");
            for (int i = 0; i < 20; i++) {
                sink.next(i);
                Util.sleepSeconds(1);
            }

            sink.complete();
        });

        flux
            .publishOn(Schedulers.parallel())
            .doOnNext(i -> printThreadName("next1 " + i))
            .subscribeOn(Schedulers.boundedElastic())
            .doOnNext(i -> printThreadName("next2 " + i))
            .subscribe(v -> printThreadName("sub " + v));


        Util.sleepSeconds(5);
    }


    private static void printThreadName(String message) {
        System.out.println(
            message + "\t\t" + " Thread: " + Thread.currentThread().getName()
        );
    }



}
