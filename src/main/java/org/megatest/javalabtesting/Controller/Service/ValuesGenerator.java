package org.megatest.javalabtesting.Controller.Service;

import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;


@NoArgsConstructor
public class ValuesGenerator {

    private final AtomicInteger intCounter = new AtomicInteger(1);

    private static float myFloat = 0.00F;

    public int generatedId(){
        return intCounter.getAndIncrement();
    }

    public synchronized float incrementedFloat(){
        myFloat += 100.15F;
        return myFloat;
    }



}
