package com.julianduru.learning.reactive.context.helper;

import reactor.util.context.Context;

import java.util.Map;
import java.util.function.Function;

/**
 * created by julian on 28/02/2022
 */
public class UserService {

    private static final Map<String, String> MAP = Map.of(
        "sam", "std",
        "mike", "prime"
    );

    public static Function<Context, Context> userCategoryContext() {
        return ctx -> {
            var user = ctx.get("user").toString();
            var category = MAP.get(user);

            return ctx.put("category", category);
        };
    }

}
