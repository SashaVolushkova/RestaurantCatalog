package org.example.restaurant.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Service {
    private final ObjectReader objectReader;
    private final SampleBean sampleBean;

    public Service(ObjectReader objectReader,
                   @Qualifier("SampleBeanA") SampleBean sampleBean) {
        this.objectReader = objectReader;
        this.sampleBean = sampleBean;
    }
}
