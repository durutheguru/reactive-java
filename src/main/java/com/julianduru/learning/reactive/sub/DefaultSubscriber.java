package com.julianduru.learning.reactive.sub;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * created by julian on 14/02/2022
 */
public record DefaultSubscriber(String name) implements Subscriber<Object> {


    public DefaultSubscriber {
        if (name.length() < 5) {
            throw new IllegalArgumentException("Name cannot be less than 5 characters");
        }
    }


    public DefaultSubscriber() {
        this ("Subscriber");
    }


    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }


    @Override
    public void onNext(Object o) {
        System.out.printf("%s Received %s%n", name(), o.toString());
    }


    @Override
    public void onError(Throwable throwable) {
        System.err.printf("%s ERROR %s%n", name(), throwable.getMessage());
    }


    @Override
    public void onComplete() {
        System.out.printf("%s Completed%n", name());
    }


}


