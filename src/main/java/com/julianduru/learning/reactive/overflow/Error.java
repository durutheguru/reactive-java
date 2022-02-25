package com.julianduru.learning.reactive.overflow;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;

/**
 * created by julian on 23/02/2022
 */
public class Error {


    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        var list = new ArrayList<>();

        Flux
            .create(sink -> {
                for (int i = 0; i < 1099; i++) {
                    sink.next(i);
                    System.out.println("Pushed " + i);
                    Util.sleepMillis(1);
                }

                sink.complete();
            })
            .publishOn(Schedulers.boundedElastic())
            .onBackpressureError()
            .subscribe(
                Util.subscriber(100L)::onNext,
                Util.onError()
            );

        Util.sleepSeconds(15);
    }


}
