package com.julianduru.learning.reactive.combining;

import com.julianduru.learning.reactive.util.Util;
import com.julianduru.learning.reactive.util.flights.AmericanAirlines;
import com.julianduru.learning.reactive.util.flights.EmiratesFlights;
import com.julianduru.learning.reactive.util.flights.QatarFlights;
import reactor.core.publisher.Flux;

/**
 * created by julian on 24/02/2022
 */
public class Merge {


    public static void main(String[] args) {
        var flux = Flux.merge(
            QatarFlights.getFlights(),
            EmiratesFlights.getFlights(),
            AmericanAirlines.getFlights()
        );

        flux.subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }




}
