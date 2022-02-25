package com.julianduru.learning.reactive.util;

import com.github.javafaker.Faker;
import com.julianduru.learning.reactive.sub.DefaultSubscriber;
import lombok.extern.java.Log;
import org.reactivestreams.Subscriber;

import java.util.function.Consumer;
import java.util.logging.Level;

/**
 * created by julian on 12/02/2022
 */
@Log
public class Util {


    private static final Faker FAKER = Faker.instance();


    public static Consumer<Object> onNext() {
        return o -> System.out.println("Received: " + o);
    }


    public static Consumer<Throwable> onError() {
        return e -> log.log(Level.SEVERE, e.getMessage(), e);
    }


    public static Runnable onCompleted() {
        return () -> System.out.println("Completed");
    }


    public static Faker faker() {
        return FAKER;
    }


    public static void sleepSeconds(int seconds) {
        sleepMillis(seconds * 1000L);
    }


    public static void sleepMillis(long millis) {
        try{
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }
    }


    public static Subscriber<Object> subscriber() {
        return new DefaultSubscriber();
    }


    public static Subscriber<Object> subscriber(String name) {
        return new DefaultSubscriber(name);
    }


    public static Subscriber<Object> subscriber(Long delay) {
        return new DefaultSubscriber(delay);
    }


    public static Subscriber<Object> subscriber(String name, Long delay) {
        return new DefaultSubscriber(name, delay);
    }


}

