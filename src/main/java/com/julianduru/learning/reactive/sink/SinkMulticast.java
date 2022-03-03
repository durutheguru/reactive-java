package com.julianduru.learning.reactive.sink;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Sinks;

/**
 * created by julian on 27/02/2022
 */
public class SinkMulticast {

    public static void main(String[] args) {
        //handle through which we push items
        var sink = Sinks.many().multicast().onBackpressureBuffer();

        // handle through which subscribers will receive items
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

        sink.tryEmitNext("Hi");
        sink.tryEmitNext("How are you");
        sink.tryEmitNext("?");
        sink.tryEmitComplete();
    }

}
