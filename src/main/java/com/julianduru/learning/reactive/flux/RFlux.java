package com.julianduru.learning.reactive.flux;

import com.julianduru.learning.reactive.service.OrderService;
import com.julianduru.learning.reactive.service.UserService;
import com.julianduru.learning.reactive.sub.NameProducer;
import com.julianduru.learning.reactive.util.NamePublisher;
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
//        fluxListSampling();
//        fluxCreate();
//        fluxCreateFix();
//        fluxGenerate();
        flatMap();
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

        var names = NamePublisher
            .getFluxNames(5)
            .subscribe(System.out::println);
    }


    private static void fluxFromMono() {
        Flux.from(Mono.just("5"));
    }


    private static void fluxCreate() {
        var producer = new NameProducer();

        Flux.create(producer)
            .subscribe(Util.subscriber("Juney"));

        Runnable runnable = producer::produce;

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }


    private static void fluxCreateFix() {
        Flux
            .create(sink -> {
                String country;

                do {
                    country = Util.faker().country().name();
                    System.out.println("Emitting: " + country);
                    sink.next(country);
                } while (
                    !sink.isCancelled() && !country.equalsIgnoreCase("canada")
                );

                sink.complete();
            })
            .take(3)
            .subscribe(Util.subscriber());
    }


    private static void fluxGenerate() {
        Flux.generate(
            sink -> {
                System.out.println("Emitting....");
                sink.next(Util.faker().country().name());
            }
        ).take(10).subscribe(Util.subscriber());
    }


    private static void flatMap() {
        UserService.getUsers()
            .flatMap(user -> OrderService.getOrder(user.userId()))
            .subscribe(Util.subscriber());
    }


}


