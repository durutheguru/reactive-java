package com.julianduru.learning.reactive.sub;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

/**
 * created by julian on 14/02/2022
 */
public class NameProducer implements Consumer<FluxSink<String>> {


    FluxSink<String> sink;


    @Override
    public void accept(FluxSink<String> sink) {
        this.sink = sink;
    }


    public void produce() {
        var name = Util.faker().name().fullName();
        var thread = Thread.currentThread().getName();
        this.sink.next(thread + " : " + name);
    }


}

