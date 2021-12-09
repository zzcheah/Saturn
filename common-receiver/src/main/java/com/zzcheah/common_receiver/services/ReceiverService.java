package com.zzcheah.common_receiver.services;

import com.zzcheah.common_receiver.implementations.IReceiverProcessor;
import com.zzcheah.common_receiver.models.ReceiverPipeline;
import org.springframework.stereotype.Service;

@Service
public class ReceiverService {

    private final IReceiverProcessor receiverProcessor;

    public ReceiverService(IReceiverProcessor receiverProcessor) {
        this.receiverProcessor = receiverProcessor;
    }

    public void runFlow(ReceiverPipeline pipeline) {
        receiverProcessor.backupReceivedFile(pipeline);
        receiverProcessor.syncProcess(pipeline);
        receiverProcessor.publishToKafka(pipeline);
    }

}
