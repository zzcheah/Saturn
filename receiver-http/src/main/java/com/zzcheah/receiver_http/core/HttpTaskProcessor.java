package com.zzcheah.receiver_http.core;

import com.zzcheah.common_receiver.core.IReceiverProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HttpTaskProcessor implements IReceiverProcessor {

    public HttpTaskProcessor() {
      log.info("HttpTaskProcessor created");
    }

    @Override
    public String backupReceivedFile() {
        return "some key";
    }

    @Override
    public String publishToKafka() {
        return "kafka value";
    }

    @Override
    public String syncProcess() {
        return null;
    }
}
