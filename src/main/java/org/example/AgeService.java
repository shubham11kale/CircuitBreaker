package org.example;

public interface AgeService {
    public int getAge();
    public int getAgeFallback(Throwable t);

    void setName(String product);
}
