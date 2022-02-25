package com.julianduru.learning.reactive.util;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * created by julian on 24/02/2022
 */
public class NameGenerator {


    private List<String> list = new ArrayList<>();

    public Flux<String> getNames() {
        return Flux.generate(
            sink -> {
                System.out.println("generated fresh");
                Util.sleepSeconds(1);

                var name = Util.faker().name().fullName();
                list.add(name);
                sink.next(name);
            })
            .cast(String.class)
            .startWith(getFromCache());
    }


    private Flux<String> getFromCache() {
        return Flux.fromIterable(list);
    }


}
