package com.zzcheah.receiver_http.services;

import com.zzcheah.common_receiver.models.ReceiverPipeline;
import com.zzcheah.common_receiver.services.ReceiverService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.text.SimpleDateFormat;

@Slf4j
@Service
public class HttpEdiService {

    private final ReceiverService receiverService;

    public HttpEdiService(ReceiverService receiverService) {
        this.receiverService = receiverService;
    }

    public ReceiverPipeline createEdi(String client, String ediType, String filename, InputStream dataIS) {
        if (StringUtils.isEmpty(filename)) {
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
            filename = String.format("%s_%s_%s", timeStamp, client, ediType);
        }
        ReceiverPipeline pipeline = ReceiverPipeline.builder()
                .client(client)
                .ediType(ediType)
                .filename(filename)
                .dataStream(dataIS)
                .traceId(MDC.get("traceId"))
                .build();
        receiverService.runFlow(pipeline);
        return pipeline;
    }

}
