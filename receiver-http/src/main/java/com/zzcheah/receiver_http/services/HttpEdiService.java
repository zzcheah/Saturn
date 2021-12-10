package com.zzcheah.receiver_http.services;

import com.zzcheah.common_receiver.models.ReceiverPipeline;
import com.zzcheah.common_receiver.services.ReceiverService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HttpEdiService {

    private final ReceiverService receiverService;

    public HttpEdiService(ReceiverService receiverService) {
        this.receiverService = receiverService;
    }

    public ReceiverPipeline createEdi(String client, String ediType) {
        ReceiverPipeline pipeline = ReceiverPipeline.builder()
                .client(client)
                .ediType(ediType)
                .traceId(MDC.get("traceId"))
                .build();
        receiverService.runFlow(pipeline);
        return pipeline;
    }

}
