package com.julianduru.learning.reactive.sink;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * created by julian on 27/02/2022
 */
public class SinkThreadSafety {


    public static void main(String[] args) {
        //handle through which we push items
        var sink = Sinks.many().multicast().onBackpressureBuffer();

        // handle through which subscribers will receive items
        var flux = sink.asFlux();
        var list = new ConcurrentLinkedQueue<>();

        flux.subscribe(list::add);
        for (int i = 0; i < 1000; i++) {
            final int j = i;
            CompletableFuture.runAsync(() -> {
//                var emitResult = sink.tryEmitNext(j);
                sink.emitNext(j, (s, e) -> true);
            });
        }

        Util.sleepSeconds(4);

        System.out.println("List Size: " + list.size());

    }

}
