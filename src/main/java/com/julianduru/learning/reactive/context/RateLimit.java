package com.julianduru.learning.reactive.context;

import com.julianduru.learning.reactive.context.helper.BookService;
import com.julianduru.learning.reactive.context.helper.UserService;
import com.julianduru.learning.reactive.util.Util;
import reactor.util.context.Context;

/**
 * created by julian on 28/02/2022
 */
public class RateLimit {

    public static void main(String[] args) {
        BookService.getBook()
            .repeat(3)
            .contextWrite(UserService.userCategoryContext())
            .contextWrite(Context.of("user", "mike"))
            .subscribe(Util.subscriber());
    }

}
