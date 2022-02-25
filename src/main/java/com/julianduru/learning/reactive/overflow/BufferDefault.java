package com.julianduru.learning.reactive.overflow;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * created by julian on 23/02/2022
 */
public class BufferDefault {


    public static void main(String[] args) {
        Flux
            .create(sink -> {
                for (int i = 0; i < 500; i++) {
                    sink.next(i);
                    System.out.println("Pushed " + i);
                }

                sink.complete();
            })
            .publishOn(Schedulers.boundedElastic())
            .doOnNext(i -> Util.sleepMillis(10))
            .subscribe(Util.subscriber());

        Util.sleepSeconds(15);
    }


}


