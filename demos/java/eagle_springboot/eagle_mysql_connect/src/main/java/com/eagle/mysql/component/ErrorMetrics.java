package com.eagle.mysql.component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ErrorMetrics {
    private final MeterRegistry meterRegistry;

    @Autowired
    public ErrorMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void recordError() {
        Counter errors = meterRegistry.counter("errors");
        errors.increment();
        System.out.println(errors.count());
    }


}
