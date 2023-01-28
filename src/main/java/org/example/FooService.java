package org.example;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

import java.time.Duration;
import java.util.function.Supplier;

public class FooService {
    private AgeService ageService;
    public FooService(AgeService ageService) {
        this.ageService = ageService;
    }

    public Supplier<Integer> getSupplier(){
        //CircuitBreaker configuration
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .slidingWindow(10, 5, CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .failureRateThreshold(30)
                .permittedNumberOfCallsInHalfOpenState(3)
                .waitDurationInOpenState(Duration.ofSeconds(10))
                .build();
        //CircuitBreaker registry
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.custom()
                .withCircuitBreakerConfig(config)
                .build();
        //CircuitBreaker
        CircuitBreaker circuitBreaker = registry.circuitBreaker("myCustomCircuitBreaker");
        //Supplier
        Supplier<Integer> supplier = CircuitBreaker.decorateSupplier(circuitBreaker, ageService::getAge);
        return supplier;
    }

}
