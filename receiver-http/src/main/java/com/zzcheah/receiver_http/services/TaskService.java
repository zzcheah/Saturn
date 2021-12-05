package com.zzcheah.receiver_http.services;

import com.zzcheah.common_receiver.core.IReceiverProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TaskService {

    private final IReceiverProcessor receiverProcessor;

    public TaskService(IReceiverProcessor receiverProcessor) {
        this.receiverProcessor = receiverProcessor;
    }

    public String createTask(String from, String type) {

        log.info(receiverProcessor.backupReceivedFile());
        log.info(receiverProcessor.publishToKafka());
        log.info(receiverProcessor.syncProcess());
        return String.format("Task from %s with type %s created", from, type);
    }

}
