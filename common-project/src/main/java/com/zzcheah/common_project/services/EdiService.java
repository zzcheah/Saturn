package com.zzcheah.common_project.services;

import com.zzcheah.common_base.models.kafka_exchange.ReceiverOutput;
import com.zzcheah.common_project.interfaces.IIncomingEdiProcessor;
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

    private final IIncomingEdiProcessor incomingProcessor;
    private final FirebaseService firebaseService;

    public void runIncomingFlow(EdiPipeline pipeline) {
        //todo: fallback actions for each step
        incomingProcessor.configure(pipeline);
        incomingProcessor.validate(pipeline);
        incomingProcessor.map(pipeline);
        incomingProcessor.upload(pipeline);
        incomingProcessor.notify(pipeline);
    }

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
                this.runIncomingFlow(pipeline);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

}
