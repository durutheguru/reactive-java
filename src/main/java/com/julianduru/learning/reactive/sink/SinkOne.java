package com.julianduru.learning.reactive.sink;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * created by julian on 27/02/2022
 */
public class SinkOne {


    public static void main(String[] args) {
        var sink = Sinks.one();
        var mono = sink.asMono();

        mono.subscribe(Util.subscriber("sam"));

        sink.tryEmitValue("Hi");
    }



}
