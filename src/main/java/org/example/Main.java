package org.example;

import io.vavr.control.Try;


import java.util.List;
import java.util.function.Supplier;

public class Main{

    public static void main(String[] args) {
        List<String> listOfPeople = List.of("MK", "GK","Brook","SK","PK","DK", "TK", "BK", "CK", "LK", "SK", "SK");
        AgeService resAgeService = new AgeServiceImpl();
        FooService fooService = new FooService(resAgeService);

        Supplier<Integer> supplier = fooService.getSupplier();
        //Call the method
        for(String person:listOfPeople){
            resAgeService.setName(person);
            Try.ofSupplier(supplier).recover(resAgeService::getAgeFallback);
        }
    }

}