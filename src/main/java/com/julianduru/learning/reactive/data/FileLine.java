package com.julianduru.learning.reactive.data;

import java.math.BigDecimal;

/**
 * created by julian on 12/02/2022
 */
public record FileLine(
    String firstName,
    String lastName,
    char sex,
    int age,
    BigDecimal netWorth
) {
}
