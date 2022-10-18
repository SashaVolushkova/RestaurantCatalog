package org.example.restaurant.abc;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class A {
    public static AtomicInteger postConstructCount = new AtomicInteger(0);
    public static AtomicInteger preDestroyCount = new AtomicInteger(0);

    @PostConstruct
    public void postConstruct() {
        postConstructCount.incrementAndGet();
    }

    @PreDestroy
    public void preDestroy() {
        preDestroyCount.incrementAndGet();
    }
}
