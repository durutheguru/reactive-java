package com.julianduru.learning.reactive.data;

import com.julianduru.learning.reactive.util.Util;

/**
 * created by julian on 17/02/2022
 */
public record Person(
    String name,
    int age
) {


    public Person() {
        this(
            Util.faker().name().fullName(),
            Util.faker().random().nextInt(100)
        );
    }


}
