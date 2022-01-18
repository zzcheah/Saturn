package com.zzcheah.common_project.services;

import com.zzcheah.common_base.models.kafka_exchange.ReceiverOutput;
import com.zzcheah.common_project.models.AbstractIncomingEdiProcessor;
import com.zzcheah.common_project.models.EdiPipeline;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@AllArgsConstructor
@Slf4j
public class EdiService {

    private final AbstractIncomingEdiProcessor incomingProcessor;
    private final FirebaseService firebaseService;

    @Bean
    public Consumer<ReceiverOutput> receiveTask() {
        return payload -> {
            try {
                EdiPipeline pipeline = EdiPipeline.builder()
                        .client(payload.getClient())
                        .ediType(payload.getEdiType())
                        .traceId(MDC.get("traceId"))
                        .filename(FilenameUtils.getName(payload.getKey()))
                        .dataStream(firebaseService.retrieveFile(payload.getKey()))
                        .build();
                incomingProcessor.runIncomingFlow(pipeline);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

}
