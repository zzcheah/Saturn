package com.zzcheah.receiver_http.components;

import com.zzcheah.common_base.models.kafka_exchange.ReceiverOutput;
import com.zzcheah.common_receiver.interfaces.IReceiverProcessor;
import com.zzcheah.common_receiver.models.ReceiverPipeline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HttpTaskProcessor implements IReceiverProcessor {

    private final StreamBridge streamBridge;

    public HttpTaskProcessor(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
        log.info("HttpTaskProcessor created");
    }

    @Override
    public void backupReceivedFile(ReceiverPipeline pipeline) {
        pipeline.setDoneBackup(true);
    }

    @Override
    public void publishToKafka(ReceiverPipeline pipeline) {
        streamBridge.send("testTopic", new ReceiverOutput("asd", pipeline.getClient(), pipeline.getEdiType()));
        pipeline.setPublishedKafka(true);
    }

    @Override
    public void syncProcess(ReceiverPipeline pipeline) {

    }

}
