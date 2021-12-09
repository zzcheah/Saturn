package com.zzcheah.common_receiver.models;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Data
@ToString
public class ReceiverPipeline {

    // core
    private final String client;
    private final String ediType;
    private final String traceId;

    // fields
    private String errMsg;

    // status
    private boolean isDoneBackup;
    private boolean isPublishedKafka;

    public ReceiverPipeline(String client, String ediType, String traceId) {
        this.client = client;
        this.ediType = ediType;
        this.traceId = traceId;
    }

    public boolean isSuccessProcessing() {
        if(!StringUtils.isEmpty(errMsg)) return false;
        return isDoneBackup && isPublishedKafka;
    }



}
