package com.julianduru.learning.reactive.data;

import com.julianduru.learning.reactive.util.Util;

/**
 * created by julian on 17/02/2022
 */
public record User(
    int userId,
    String name
) {

    public User(int userId) {
        this(
            userId,
            Util.faker().name().fullName()
        );
    }

}
