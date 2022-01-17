package com.zzcheah.common_receiver.interfaces;

import com.zzcheah.common_receiver.models.ReceiverPipeline;

public interface IReceiverProcessor {

    void backupReceivedFile(ReceiverPipeline pipeline);
    void publishToKafka(ReceiverPipeline pipeline);

}
