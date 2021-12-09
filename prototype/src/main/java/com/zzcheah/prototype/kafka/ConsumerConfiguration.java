package com.zzcheah.prototype.kafka;

import com.zzcheah.common_base.ReceiverOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class ConsumerConfiguration {

    @Bean
    public Consumer<ReceiverOutput> consumer() {
        return payload -> {
            System.out.println("do something from kafka");
            log.info(payload.toString());

        };
    }
}
