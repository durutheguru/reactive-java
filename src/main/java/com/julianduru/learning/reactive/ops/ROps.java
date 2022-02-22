package com.julianduru.learning.reactive.ops;

import com.julianduru.learning.reactive.data.Person;
import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Function;

/**
 * created by julian on 17/02/2022
 */
public class ROps {


    public static void main(String[] args) {
//        handles();
//        rateLimit();
        transform();
    }


    private static void handles() {
        Flux.range(1, 20)
            .handle((value, sink) -> {
                if (value % 2 == 0) {
                    sink.next(value);
                }
            })
            .subscribe(Util.subscriber());
    }


    public static void rateLimit() {
        Flux.range(1, 1000)
            .log()
            .limitRate(100)
            .subscribe(Util.subscriber());
    }


    public static void delay() {
        Flux.range(1, 10)
            .log()
            .delayElements(Duration.ofSeconds(1))
            .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }


    public static void transform() {
        getPerson()
            .transform(applyFilterMap())
            .subscribe(Util.subscriber());
    }


    private static Flux<Person> getPerson() {
        return Flux.range(1, 10)
            .map(i -> new Person());
    }


    private static Function<Flux<Person>, Flux<Person>> applyFilterMap() {
        return flux -> flux.filter(p -> p.age() > 50)
            .map(p -> new Person(p.name().toUpperCase(), p.age()));
    }


}


