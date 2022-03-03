package com.julianduru.learning.reactive.context.helper;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * created by julian on 28/02/2022
 */
public class BookService {

    private static Map<String, Integer> map = new HashMap<>();

    static {
        map.put("std", 2);
        map.put("prime", 3);
    }

    public static Mono<String> getBook() {
        return Mono
            .deferContextual(ctx -> {
                if (ctx.get("allow")) {
                    return Mono.just(Util.faker().book().title());
                }
                else {
                    return Mono.error(new RuntimeException("Not allowed"));
                }
            })
            .contextWrite(rateLimiterContext());
    }


    private static Function<Context, Context> rateLimiterContext() {
        return ctx -> {
            if (ctx.hasKey("category")) {
                var category = ctx.get("category").toString();
                var attempts = map.get(category);

                if (attempts > 0) {
                    map.put(category, attempts - 1);
                    return ctx.put("allow", true);
                }
            }

            return ctx.put("allow", false);
        };
    }


}
