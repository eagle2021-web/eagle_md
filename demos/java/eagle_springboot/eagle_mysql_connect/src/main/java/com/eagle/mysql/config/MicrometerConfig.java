package com.eagle.mysql.config;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import io.micrometer.core.instrument.logging.LoggingRegistryConfig;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicrometerConfig {

//    @Bean
//    public MeterRegistryCustomizer<MeterRegistry> registryCustomizer(MeterRegistry meterRegistry) {
//        return registry -> {
//            // 在Micrometer Registry中添加Logback的Appender
//            LoggingMeterRegistry loggingMeterRegistry = new LoggingMeterRegistry();
//                  loggingMeterRegistry.re
//                    .logOnlyOnWarnAndError(false)
//                    .register(meterRegistry);
//
//            // 其他的MeterRegistry配置
//            // ...
//        };
//    }

    // 添加其他的MeterRegistry Bean
    // ...
}