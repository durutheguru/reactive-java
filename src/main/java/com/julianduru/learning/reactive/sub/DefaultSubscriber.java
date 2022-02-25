package com.julianduru.learning.reactive.sub;

import com.julianduru.learning.reactive.util.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * created by julian on 14/02/2022
 */
public class DefaultSubscriber implements Subscriber<Object> {


    private final String name;


    private final Long SUBSCRIPTION_TIMEOUT_MILLIS;


    public DefaultSubscriber(String name) {
        this(name, -1L);
    }


    public DefaultSubscriber(Long timeout) {
        this("", timeout);
    }


    public DefaultSubscriber(String name, Long timeout) {
        this.name = name;
        this.SUBSCRIPTION_TIMEOUT_MILLIS = timeout;
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
        if (SUBSCRIPTION_TIMEOUT_MILLIS > 0) {
            Util.sleepMillis(SUBSCRIPTION_TIMEOUT_MILLIS);
        }

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


    private String name() {
        return this.name;
    }


}


