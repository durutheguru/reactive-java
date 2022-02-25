package com.julianduru.learning.reactive.overflow;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;

/**
 * created by julian on 23/02/2022
 */
public class Drop {


    public static void main(String[] args) {

        var list = new ArrayList<>();

        Flux
            .create(sink -> {
                for (int i = 0; i < 599; i++) {
                    sink.next(i);
                    System.out.println("Pushed " + i);
                    Util.sleepMillis(1);
                }

                sink.complete();
            })
            .onBackpressureDrop(list::add)
            .publishOn(Schedulers.boundedElastic())
            .doOnNext(i -> Util.sleepMillis(10))
            .subscribe(Util.subscriber());

        Util.sleepSeconds(15);
    }


}

