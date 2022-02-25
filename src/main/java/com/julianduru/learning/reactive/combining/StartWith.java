package com.julianduru.learning.reactive.combining;

import com.julianduru.learning.reactive.util.NameGenerator;
import com.julianduru.learning.reactive.util.NamePublisher;
import com.julianduru.learning.reactive.util.Util;

/**
 * created by julian on 24/02/2022
 */
public class StartWith {

    public static void main(String[] args) {
        var generator = new NameGenerator();

        generator.getNames()
            .take(2)
            .subscribe(Util.subscriber("sam"));

        generator.getNames()
            .take(2)
            .subscribe(Util.subscriber("mike"));

        generator.getNames()
            .take(3)
            .subscribe(Util.subscriber("jake"));
    }


}
