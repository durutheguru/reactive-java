package com.julianduru.learning.reactive.util;

import com.github.javafaker.Faker;
import lombok.extern.java.Log;

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
        try{
            Thread.sleep(seconds * 1000);
        }
        catch (InterruptedException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }
    }

}

