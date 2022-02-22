package com.julianduru.learning.reactive.service;

import com.julianduru.learning.reactive.data.User;
import reactor.core.publisher.Flux;

/**
 * created by julian on 17/02/2022
 */
public class UserService {

    public static Flux<User> getUsers() {
        return Flux.range(1, 2).map(User::new);
    }

}
