package com.zzcheah.common_receiver.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ReceiverPipeline {

    // core
    private String client;
    private String ediType;
    private String traceId;

    // fields
    private String errMsg;

    // status
    private boolean isDoneBackup;
    private boolean isPublishedKafka;

}
