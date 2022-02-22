package com.julianduru.learning.reactive.data;

import com.julianduru.learning.reactive.util.Util;

/**
 * created by julian on 17/02/2022
 */
public record PurchaseOrder(
    String item,
    String price,
    int userId
) {

    public PurchaseOrder(int userId) {
        this(
            Util.faker().commerce().productName(),
            Util.faker().commerce().price(),
            userId
        );
    }


}


