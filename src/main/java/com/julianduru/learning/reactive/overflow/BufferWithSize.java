package com.julianduru.learning.reactive.overflow;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * created by julian on 23/02/2022
 */
public class BufferWithSize {


    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        Flux
            .create(sink -> {
                for (int i = 0; i < 201 && !sink.isCancelled(); i++) {
                    sink.next(i);
                    System.out.println("Pushed " + i);
                    Util.sleepMillis(1L);
                }

                sink.complete();
            })
            .onBackpressureBuffer(14)
            .publishOn(Schedulers.boundedElastic())
            .doOnNext(i -> Util.sleepMillis(1000))
            .subscribe(
                Util.subscriber()::onNext,
                Util.onError()
            );

        Util.sleepSeconds(20);
    }


}
