package com.zzcheah.common_receiver.models;

import lombok.*;

import java.io.InputStream;

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
    private String filename;
    private InputStream dataStream;

    // fields
    private String errMsg;

    // status
    private boolean isDoneBackup;
    private boolean isPublishedKafka;

}
