package com.julianduru.learning.reactive.publisher;

import com.julianduru.learning.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

/**
 * created by julian on 19/02/2022
 */
public class HotPublisher {


    public static void main(String[] args) {
        var flux = Flux.fromStream(HotPublisher::getMovie)
            .delayElements(Duration.ofSeconds(2))
            .share().cache(1);

        flux.subscribe(
            Util.subscriber("samuel")
        );

        Util.sleepSeconds(5);

        flux.subscribe(Util.subscriber("michael"));

        Util.sleepSeconds(5);;
    }


    private static Stream<String> getMovie() {
        System.out.println("Got Movie Streaming Request");
        return Stream.of(
            "Scene 1",
            "Scene 2",
            "Scene 3",
            "Scene 4",
            "Scene 5",
            "Scene 6",
            "Scene 7"
        );
    }



}
