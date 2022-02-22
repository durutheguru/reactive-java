package com.julianduru.learning.reactive.service;

import com.julianduru.learning.reactive.data.PurchaseOrder;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * created by julian on 17/02/2022
 */
public class OrderService {


    private static Map<Integer, List<PurchaseOrder>> db = Map.of(
        1, Arrays.asList(
            new PurchaseOrder(1),
            new PurchaseOrder(1),
            new PurchaseOrder(1)
        ),
        2, Arrays.asList(
            new PurchaseOrder(2),
            new PurchaseOrder(2)
        )
    );


    public static Flux<PurchaseOrder> getOrder(int userId) {
        return Flux.create(sink -> {
            db.get(userId).forEach(sink::next);
            sink.complete();
        });
    }


}



