package com.julianduru.learning.reactive.flux;

import com.julianduru.learning.reactive.util.NameGenerator;
import com.julianduru.learning.reactive.util.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

/**
 * created by julian on 13/02/2022
 */
public class RFlux {


    public static void main(String[] args) {
//        multipleSubscribe();
//        fluxSubscription();
        fluxListSampling();
    }


    private static void multipleSubscribe() {
        var flux = Flux.just(1, 2, 3, 4);

        flux
            .filter(i -> i % 2 == 0)
            .subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onCompleted()
            );

        flux.subscribe(
            Util.onNext(),
            Util.onError(),
            Util.onCompleted()
        );
    }


    private static void fluxSubscription() {

        AtomicReference<Subscription> atomicReference = new AtomicReference<>();

        var flux = Flux
            .range(1, 20)
            .log()
            .subscribeWith(new Subscriber<Integer>() {
                @Override
                public void onSubscribe(Subscription subscription) {
                    System.out.println("Received Subscription.." + subscription);
                    atomicReference.set(subscription);
                }

                @Override
                public void onNext(Integer integer) {
                    System.out.println("on Next invoked: " + integer);
                }

                @Override
                public void onError(Throwable throwable) {
                    System.err.println("on Error invoked: " + throwable.getMessage());
                }

                @Override
                public void onComplete() {
                    System.out.println("on Complete");
                }
            });

        Util.sleepSeconds(3);
        atomicReference.get().request(3);
        Util.sleepSeconds(3);
        atomicReference.get().request(3);
        Util.sleepSeconds(5);
        System.out.println("Cancelling Subscription");
        atomicReference.get().cancel();
        Util.sleepSeconds(3);
        atomicReference.get().request(4);
    }


    private static void fluxListSampling() {
//        var names = NameGenerator.getNames(5);
//        System.out.println(names);

        var names = NameGenerator
            .getFluxNames(5)
            .subscribe(System.out::println);
    }


    private static void fluxFromMono() {
        Flux.from(Mono.just("5"));
    }


}


