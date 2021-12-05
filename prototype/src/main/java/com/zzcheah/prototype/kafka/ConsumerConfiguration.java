package com.zzcheah.prototype.kafka;

import com.zzcheah.common_base.CommonBaseClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class ConsumerConfiguration {

    @Bean
    public Consumer<CommonBaseClass> consumer() {
        return payload -> {
            System.out.println("do something from kafka");
            payload.doSomething();
        };
    }
}
