package com.julianduru.learning.reactive.mono;

import com.github.javafaker.Faker;
import com.julianduru.learning.reactive.util.Util;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

/**
 * created by julian on 12/02/2022
 */
@Log
public class RMono {


    public static void main(String[] args) {
        monoApiTest();
//        monoFromSupplier();
//        monoFromMonoName();
//        monoFromFuture();
    }


    private static void monoApiTest() {
        var mono = userRepository(1);

        mono.subscribe(
            Util.onNext(),
            Util.onError(),
            Util.onCompleted()
        );

        mono.subscribe(
            Util.onNext(),
            Util.onError(),
            Util.onCompleted()
        );
    }


    private static void monoFromSupplier() {
        var mono = Mono.fromSupplier(RMono::getName);

        mono.subscribe(
            Util.onNext(),
            Util.onError(),
            Util.onCompleted()
        );
    }


    private static void monoFromMonoName() {
        var mono = getMonoName();

        mono.subscribe(
            Util.onNext(),
            Util.onError(),
            Util.onCompleted()
        );
    }


    private static void monoFromFuture() {
        var mono = Mono.fromFuture(RMono::getFutureName);
        mono.subscribe(
            Util.onNext(),
            Util.onError(),
            Util.onCompleted()
        );
    }


    private static String getName() {
        System.out.println("Generating name...");
        return Util.faker().name().fullName();
    }


    private static CompletableFuture<String> getFutureName() {
        return CompletableFuture.supplyAsync(() -> Util.faker().name().fullName());
    }


    private static final Mono<String> getMonoName() {
        System.out.println("Generating Mono name...");
        return Mono
            .fromSupplier(() -> {
                Util.sleepSeconds(3);
                return Util.faker().name().fullName();
            })
            .map(String::toUpperCase);
    }


    private static Mono<String> userRepository(int userId) {
        if (userId == 1) {
            return Mono.fromSupplier(() -> Util.faker().name().fullName());
        }
        else if (userId == 2) {
            return Mono.empty();
        }
        else {
            return Mono.error(new RuntimeException("Not in allowed range"));
        }
    }


}

