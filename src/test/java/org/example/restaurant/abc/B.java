package org.example.restaurant.abc;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class B {
    public static AtomicInteger postConstructCount = new AtomicInteger(0);
    public static AtomicInteger preDestroyCount = new AtomicInteger(0);

    @Getter
    private final A a;

    public B(A a) {
        this.a = a;
    }

    @PostConstruct
    public void postConstruct() {
        postConstructCount.incrementAndGet();
    }

    @PreDestroy
    public void preDestroy() {
        preDestroyCount.incrementAndGet();
        System.out.println("in predestroy B " + preDestroyCount);
    }
}
