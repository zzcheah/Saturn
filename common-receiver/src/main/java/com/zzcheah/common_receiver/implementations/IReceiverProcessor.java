package com.zzcheah.common_receiver.implementations;

import com.zzcheah.common_receiver.models.ReceiverPipeline;

public interface IReceiverProcessor {

    void backupReceivedFile(ReceiverPipeline pipeline);

    void publishToKafka(ReceiverPipeline pipeline);

    void syncProcess(ReceiverPipeline pipeline);

}
