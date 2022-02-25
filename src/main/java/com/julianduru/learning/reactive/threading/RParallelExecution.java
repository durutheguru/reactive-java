package com.julianduru.learning.reactive.threading;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * created by julian on 22/02/2022
 */
public class RParallelExecution {



    public static void main(String[] args) {
        var list = new ArrayList<Integer>();

        Flux.range(1, 1000)
            .parallel()
            .runOn(Schedulers.parallel())
            .doOnNext(i -> printThreadName("next: " + i))
            .subscribe(list::add);

        Util.sleepSeconds(3);

        System.out.println(list.size());
    }


    private static void printThreadName(String message) {
        System.out.println(
            message + "\t\t" + " Thread: " + Thread.currentThread().getName()
        );
    }



}


