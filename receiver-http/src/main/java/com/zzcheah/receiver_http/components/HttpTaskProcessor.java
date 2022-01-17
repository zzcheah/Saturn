package com.zzcheah.receiver_http.components;

import com.zzcheah.common_base.models.kafka_exchange.ReceiverOutput;
import com.zzcheah.common_receiver.interfaces.IReceiverProcessor;
import com.zzcheah.common_receiver.models.ReceiverPipeline;
import com.zzcheah.common_receiver.services.FirebaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class HttpTaskProcessor implements IReceiverProcessor {

    private final StreamBridge streamBridge;
    private final FirebaseService firebaseService;

    @Override
    public void backupReceivedFile(ReceiverPipeline pipeline) {
        String path = pipeline.getClient() + "/" + pipeline.getEdiType() + "/";
        firebaseService.uploadFile(path, pipeline.getFilename(), pipeline.getDataStream());
        pipeline.setFileKey(path + pipeline.getFilename());
        pipeline.setDoneBackup(true);
    }

    @Override
    public void publishToKafka(ReceiverPipeline pipeline) {
        String clientTopic = pipeline.getClient() + "-" + "topic";
        streamBridge.send(clientTopic, new ReceiverOutput(pipeline.getFileKey(), pipeline.getClient(), pipeline.getEdiType()));
        pipeline.setPublishedKafka(true);
    }


}
