package org.example;

import java.util.Random;

public class AgeServiceImpl implements AgeService {

    private String name;
    public int getAge(){
        System.out.println("Inside getAge method for Person: "+this.getName());
        if(getName().equalsIgnoreCase("MK")){
            return new Random().nextInt(50);
        } else if(getName().equalsIgnoreCase("GK")){
            return new Random().nextInt(50);
        } else{
            throw new RuntimeException("Person not available");
        }
    }

    public int getAgeFallback(Throwable t){
        System.out.println("Inside getAgeFallback method for Person: "+this.getName());

        if(getName().equalsIgnoreCase("MK")){
            return 10;
        } else if(getName().equalsIgnoreCase("GK")){
            return 10;
        } else{
            return 0;
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
