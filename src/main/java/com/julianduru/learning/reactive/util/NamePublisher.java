package com.julianduru.learning.reactive.util;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * created by julian on 13/02/2022
 */
public class NamePublisher {


    public static List<String> getNames(int count) {
        var list = new ArrayList<String>(count);

        for (int i = 0; i < count; i++) {
            list.add(getName());
        }

        return list;
    }


    public static Flux<String> getFluxNames(int count) {
        return Flux.range(0, count).map(i -> getName());
    }


    private static String getName() {
        Util.sleepSeconds(1);
        return Util.faker().name().fullName();
    }

}
