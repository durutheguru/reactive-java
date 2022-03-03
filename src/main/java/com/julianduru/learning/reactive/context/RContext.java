package com.julianduru.learning.reactive.context;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/**
 * created by julian on 28/02/2022
 */
public class RContext {


    public static void main(String[] args) {
        getWelcomeMessageContextual()
            .contextWrite(ctx -> ctx.put("user", ctx.get("user").toString().toUpperCase()))
            .contextWrite(Context.of("user", "jake"))
            .subscribe(Util.subscriber());
    }


    private static Mono<String> getWelcomeMessage() {
        return Mono.fromSupplier(() -> "Welcome");
    }


    private static Mono<String> getWelcomeMessageContextual() {
        return Mono.deferContextual(ctx -> {
            if (ctx.hasKey("user")) {
                return Mono.just("Welcome " + ctx.get("user"));
            }
            else {
                return Mono.error(new RuntimeException("Unauthorized"));
            }
        });
    }


}
